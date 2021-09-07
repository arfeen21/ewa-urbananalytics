import { Injectable } from "@angular/core";
import { DatasetService } from "../../services/dataset/dataset.service";
import { DatasetModelComponent } from "../dataset/dataset.model";
import { UserModelComponent } from '../user/user.model';
import { SafeUrl } from '@angular/platform-browser';

@Injectable({
  providedIn: 'root'
})

export class MyGroupsModelComponent {
  private groupId: number;
  private groupName: string;
  private createdOn: Date;
  private createdBy: string;
  private groupImg: File;
  private groupImgURL: SafeUrl;

  private groupMembers: any;

  private datasets: DatasetModelComponent[];

  constructor(groupName: string, groupMembers: any = [], groupImg: File = null) {
    this.groupMembers = groupMembers;
    this.groupName = groupName;
    this.groupImg = groupImg;
  }

  public getName() {
    return this.groupName;
  }

  // nieuwe fuctie voor data input;
  push(myItems: DatasetModelComponent[], i: number) {
    this.datasets.push(myItems[0], myItems[1]);
  }

  getImg() {
    return this.groupImg
  }

  setImg(groupImage: File) {
    this.groupImg = groupImage;
  }

  setGroupImgUrl(groupImage: SafeUrl) {
    this.groupImgURL = groupImage;
  }

  getGroupImgUrl() {
    return this.getGroupImgUrl;
  }
}
