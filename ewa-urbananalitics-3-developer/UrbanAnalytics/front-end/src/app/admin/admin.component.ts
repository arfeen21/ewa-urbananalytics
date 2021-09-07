import { Component, OnInit } from '@angular/core';
import { GroupService } from "../services/group/group.service";
import { HttpClient, HttpResponse } from '@angular/common/http';
import { share } from 'rxjs/operators';
import { DomSanitizer } from '@angular/platform-browser';
import { group } from '@angular/animations';
import { MyGroupsModelComponent } from '../models/group/my-groups.model';
import { UserModelComponent } from '../models/user/user.model';
import { UserService } from '../services/user/user.service';
import { RequestService } from '../services/request.service';

@Component({
  selector: 'admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss']
})
export class AdminComponent implements OnInit {

  allGroups: {};
  selectedGroup: {};
  createGroupPopup: boolean = false;
  //
  allUsers: UserModelComponent[];
  thisgroup: GroupService[];
  selectedUser: {};
  myImgUrl: string = "https://elysator.com/wp-content/uploads/blank-profile-picture-973460_1280-e1523978675847.png";




  constructor(private groupService: GroupService, private httpClient: HttpClient, private sanitizer: DomSanitizer,
    private req: RequestService) {


    this.getAllUsers();


  }

  ngOnInit() {
    this.getGroups();
  }

  getAllUsers() {
    this.req.getRequest("user/user/all", (data) => {
      this.allUsers = data;
    })
  }

  getSingleUser() {
    this.req.getRequest("user/user/{id}", (data) => {
    });
  }

  getSelectedGroupUsers() {
    this.req.getRequest("group/member/all", (data) => {
    });
  }

  toggleGroupPopup = () => {
    this.getGroups();
    this.createGroupPopup = !this.createGroupPopup;
  };

  getGroups() {
    this.groupService.getAllGroups((data: any[]) => {
      let temp = [];
      data.forEach(group => {
        group.groupImgUrl = this.getImg(group.groupImg);
        group.dateString = this.getDate(group.createdOn);
        temp.push(group)
      });

      this.allGroups = temp;
    })
  }

  getImg(byteMap) {
    if (byteMap) {
      let TYPED_ARRAY = new Uint8Array(byteMap);
      const STRING_CHAR = TYPED_ARRAY.reduce((data, byte) => {
        return data + String.fromCharCode(byte);
      }, '');
      let base64String = btoa(STRING_CHAR);
      return this.sanitizer.bypassSecurityTrustUrl('data: image / jpg; base64,' + base64String);
    }
    else {
      return "../../../assets/img/no_profile.png";
    }
  }

  getDate(date: Date) {
    return date ? new Date(date).toDateString() : null;
  }

  loadUsers() {
    this.req.getRequest("group/member/all", (data) => {
    });
  }

  deleteUser(data: any) {



    // this.req.getRequest("user/group/{id}", data => {
    //   //console.log(data);
    // })
  }

  deleteGroup(data: any) {
  }


  sortUserOnGroup() {
    this.req.getRequest("group/member/all", (data) => {
      //console.log(data);
    });
  }


}
