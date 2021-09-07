import { EventEmitter, Injectable } from '@angular/core';
import { MyGroupsModelComponent } from 'src/app/models/group/my-groups.model';
import { GroupMembersComponent } from 'src/app/my-groups/single-group/group-members/group-members.component';
import { RequestService } from '../request.service';
import { UserModelComponent } from 'src/app/models/user/user.model';
import { SingleGroupComponent } from 'src/app/my-groups/single-group/single-group.component';

@Injectable({
  providedIn: 'root'
})
export class GroupService {
  private selectedGroupId: number;

  constructor(private requestService: RequestService) { }

  setSelectedGroupId = (id: number) => {
    this.selectedGroupId = id;
  }

  getSelectedGroupId = () => {
    return this.selectedGroupId;
  }

  // Group services
  createGroup(group: FormData, callback: Function) {
    this.requestService.postRequest("group/group/create", group, (data) => {
      callback(data);
    })
  }

  getAllGroups(callback: Function): any {
    this.requestService.getRequest("user/group/all", (data) => {
      callback(data);
    });
  }

  getGroupDetails(id: number, callback: Function): any {
    this.requestService.getRequest("user/group/" + id, (data) => {
      callback(data);
    });
  };

  //Group member services
  addMember(users: {}, group: {}, callback: Function): any {
    this.requestService.postRequest("group/member/add", { users, group }, (data) => {
      callback(data)
    })
  }

  removeMember(user: any, group: any, callback: Function): any {
    console.group(group);

    this.requestService.postRequest("group/member/remove", { user: user.userId, group: group.groupId }, (data) => {
      callback(data);
    });
  }

  removeDatasetFromGroup(datasetId: number, groupID: number, callback: Function): any {
    this.requestService.postRequest("", { datasetId, groupID }, (data) => {
      callback(data);
    });
  }
}
