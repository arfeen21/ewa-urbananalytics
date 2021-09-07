import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { AuthService } from "../../../services/auth/auth.service";
import { UserService } from "../../../services/user/user.service";
import { GroupService } from "../../../services/group/group.service";
import { IDropdownSettings } from "ng-multiselect-dropdown";
import { ActivatedRoute } from "@angular/router";

@Component({
  selector: 'app-add-members',
  templateUrl: './add-members.component.html',
  styleUrls: ['./add-members.component.scss']
})
export class AddMembersComponent implements OnInit {
  @Output() exit: EventEmitter<{}> = new EventEmitter();
  private allMembers: [];
  private selectedGroup: [];
  private selectedItems: [];
  private dropdownList: {}[];
  private dropdownSettings: IDropdownSettings = {};
  private newUsers: {};


  constructor(private authService: AuthService, private userService: UserService, private groupService: GroupService, private router: ActivatedRoute) {
    this.getAllUsers()
    this.dropdownSettings = {
      singleSelection: false,
      idField: 'item_id',
      textField: 'items_text',
      selectAllText: 'Select All',
      unSelectAllText: 'Unselect All',
      itemsShowLimit: 7,
      allowSearchFilter: true
    }
  }

  ngOnInit() {
    this.router.params.subscribe(params => {
      this.groupService.getGroupDetails(params['id'], (data: any) => {
        //this.selectedGroup = data;

        //console.log(data);
      });
    });

  };

  onItemSelect(item: any){
    // @ts-ignore
    this.selectedItems.push(item)
  }

  onSelectAll(items: any) {
    // @ts-ignore
    this.selectedItems.push(items);
  }

  exitPopup = (event) => {
    if (event.target.className === "cont" || event.target.className === "cancelButton button") {
      this.exit.emit();
    }
  };

  exitPopupOnSuccess = () => {
    this.exit.emit();
  };


  getAllUsers() {
    this.authService.getLoggedInUser((user: any) => {
      this.userService.getAllMembers((allUsers: []) => {
        this.allMembers = allUsers;
        console.log(this.allMembers);
        console.log(allUsers);
        let userList: {}[] = [];
        console.log(userList);

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
        console.log(this.dropdownList)
      })
    })
  }

  getAllSelectedUsers() {
    let users: {}[] = [];

    this.selectedItems.forEach((selected) => {
      //Get selectedId cause it stores the user object
      // users.push(selected.item_id)
    });

    return users;
  }

  addUsers() {
    this.newUsers = this.getAllSelectedUsers();
    this.groupService.addMember(this.newUsers, this.selectedGroup, (data: any) =>
      console.log(data));
  }

}
