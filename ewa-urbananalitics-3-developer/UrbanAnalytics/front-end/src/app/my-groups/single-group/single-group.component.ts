import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { GroupService } from 'src/app/services/group/group.service';
import { UserModelComponent } from 'src/app/models/user/user.model';
import { DatasetModelComponent } from 'src/app/models/dataset/dataset.model';
import { MyGroupsModelComponent } from 'src/app/models/group/my-groups.model';
import { Router, ActivatedRoute } from '@angular/router';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-single-group',
  templateUrl: './single-group.component.html',
  styleUrls: ['./single-group.component.scss']
})
export class SingleGroupComponent implements OnInit {

  private selectedUser: UserModelComponent;
  private selectedGroup: any;
  private datasets: {}[];
  private groupId: number;
  private groupMembers: {};
  private addMembersPopup: boolean = false;
  private isHidden: boolean = true;
  private delDatasetID: number;
  private myImgUrl: string = "https://elysator.com/wp-content/uploads/blank-profile-picture-973460_1280-e1523978675847.png";

  constructor(private groupService: GroupService, private router: ActivatedRoute, private sanitizer: DomSanitizer, private route: Router) {
  }

  //Create the right date format
  getDate(date: Date) {
    return date ? new Date(date).toDateString() : null;
  }

  //open the modal to delete a user out of the group
  openModal(user: UserModelComponent) {
    this.selectedUser = user
    this.isHidden = false;
  }

  //open the modal to delete the dataset out of the group
  openModalDataset(id: number) {
    this.delDatasetID = id;
    console.log(this.delDatasetID);
    this.isHidden = false;
  }

  //confirm the dataset delete button
  confirmDatasetDelete() {
    this.groupService.removeDatasetFromGroup(this.delDatasetID, this.groupId, (data: any) => {
    });
  }

  //confirm the user delte button
  confirmDelete() {
    this.groupService.removeMember(this.selectedUser, this.selectedGroup, (data: any) => {
      this.groupService.getGroupDetails(this.groupId, (group) => {
        this.selectedGroup.groupMembers = group.groupMembers;
      })
    });
    this.isHidden = true;
  }

  //close the modal
  exitPopup() {
    if (!this.isHidden) {
      this.isHidden = true;
    } else {
      this.isHidden = false;
    }
  }

  // Retrieve a group from the backend
  getGroup(groupNumber: number) {

    this.groupService.getGroupDetails(groupNumber, (data: any) => {
      this.selectedGroup = data;
      this.groupId = groupNumber;
      this.datasets = data.datasets

      return true;
    });

  }

  toggleaddMembersPopup = () => {
    this.selectedGroup;
    this.addMembersPopup = !this.addMembersPopup;
  };

  ngOnInit() {
    this.router.params.subscribe(params => {
      this.getGroup(params['id']);

    });
  }

  //Fuction that wil convert the bytemap thats comes back from the backend and make it into an image
  getImg(byteMap) {
    if (byteMap) {
      let TYPED_ARRAY = new Uint8Array(byteMap);
      const STRING_CHAR = TYPED_ARRAY.reduce((data, byte) => {
        return data + String.fromCharCode(byte);
      }, '');
      let base64String = btoa(STRING_CHAR);
      return this.sanitizer.bypassSecurityTrustUrl('data: image / jpg; base64,' + base64String);
    } else {
      return "../../../assets/img/no_profile.png";
    }
  }

}
