import {Component, OnInit, Output, EventEmitter, ViewChild, ElementRef} from '@angular/core';
import {IDropdownSettings} from 'ng-multiselect-dropdown';
import {NgForm} from '@angular/forms';
import {count, share} from "rxjs/operators";
import {HttpClient} from '@angular/common/http';
import {UserService} from 'src/app/services/user/user.service';
import {GroupService} from 'src/app/services/group/group.service';
import {MyGroupsModelComponent} from 'src/app/models/group/my-groups.model';
import {UserModelComponent} from 'src/app/models/user/user.model';
import {AuthService} from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-create-group',
  templateUrl: './create-group.component.html',
  styleUrls: ['./create-group.component.scss']
})
export class CreateGroupComponent {
  @Output() exit: EventEmitter<{}> = new EventEmitter();
  private selectedItems = [];
  private dropdownList: {}[];
  private dropdownSettings: IDropdownSettings = {};
  private groupName: string;
  private allMembers: [];
  private groupImg: File;

  @ViewChild("errorMessage", {static: false}) errorMessage: ElementRef;

  constructor(private groupService: GroupService, private userService: UserService, private authService: AuthService) {
    this.dropdownSettings = {
      singleSelection: false,
      idField: 'item_id',
      textField: 'item_text',
      selectAllText: 'Select All',
      unSelectAllText: 'Unselect All',
      itemsShowLimit: 7,
      allowSearchFilter: true
    };

    this.getAllUsers();
  }

  setGroupName(event) {
    this.groupName = event.target.value;
  }

  setFile(event) {
    this.groupImg = event.target.files[0];
  }

  onItemSelect(item: any) {
    this.selectedItems.push(item);
  }

  onSelectAll(items: any) {
    this.selectedItems.push(items);
  }

  // Send exit request to parent component
  exitPopup = (event) => {
    if (event.target.className === "cont" || event.target.className === "cancelButton button") {
      this.exit.emit();
    }
  };

  exitPopupOnSuccess = () => {
    this.exit.emit();
  };

  // Creates a new Group from the form inputs
  createGroup = () => {
    let formData = new FormData();

    if (this.groupImg) formData.append("groupImg", this.groupImg);
    formData.append("groupName", this.groupName);
    formData.append("groupMembers", JSON.stringify(this.getAllSelectedUsers()));

    //Call groupService to createGroup in the backend
    this.groupService.createGroup(formData, (data) => {
      // Close popup when createGroup is done
      if (data.error) {
        if (data.error.text) {
          this.errorMessage.nativeElement.classList.add("success-msg");
          this.errorMessage.nativeElement.classList.remove("error-msg");
          this.errorMessage.nativeElement.innerText = data.error.text;
        } else {
          this.errorMessage.nativeElement.classList.add("error-msg");
          this.errorMessage.nativeElement.classList.remove("success-msg");
          this.errorMessage.nativeElement.innerText = data.error.text;
        }
      } else {
        this.errorMessage.nativeElement.classList.add("error-msg");
        this.errorMessage.nativeElement.classList.remove("success-msg");
        this.errorMessage.nativeElement.innerText = "error";
        //console.log(data);
      }

      if (data.status == 200) {
        setTimeout(this.exitPopupOnSuccess, 2500)
      }
    });
  };

  // Gets all users exept this user for the dropdownList
  getAllUsers() {
    this.authService.getLoggedInUser((user: any) => {
      this.userService.getAllMembers((allUsers: []) => {
        this.allMembers = allUsers;
        let userList: {}[] = [];

        allUsers.forEach((aUser: any) => {
          if (user.email != aUser.email) {
            userList.push({
              item_id: {
                "user": aUser,
                "group": null
              }, item_text: aUser.email
            });
          }
        });

        this.dropdownList = userList;
      })
    })
  }

  // Get correctFormat of this.selectedItems for backend
  getAllSelectedUsers() {
    let users: {}[] = [];

    this.selectedItems.forEach((selected) => {
      // Get selectedId cause it stores the user object
      users.push(selected.item_id)
    });

    return users;
  }
}
