import { Component, OnInit } from '@angular/core';
import { GroupService } from "../services/group/group.service";
import { HttpClient, HttpResponse } from '@angular/common/http';
import { share } from 'rxjs/operators';
import { DomSanitizer } from '@angular/platform-browser';
import { group } from '@angular/animations';
import { MyGroupsModelComponent } from '../models/group/my-groups.model';

@Component({
  selector: 'app-my-groups',
  templateUrl: './my-groups.component.html',
  styleUrls: ['./my-groups.component.scss']
})

export class MyGroupsComponent implements OnInit {

  allGroups: {};
  selectedGroup: {};
  createGroupPopup: boolean = false;

  constructor(private groupService: GroupService, private httpClient: HttpClient, private sanitizer: DomSanitizer) { }

  ngOnInit() {
    this.getGroups();
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
}
