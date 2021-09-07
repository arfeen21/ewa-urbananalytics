import { TestBed } from '@angular/core/testing';

import { GroupService } from './group.service';
import { FormsModule } from '@angular/forms';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { MyGroupsModelComponent } from 'src/app/models/group/my-groups.model';

describe('GroupService', () => {
  const group = new MyGroupsModelComponent("new group");

  beforeEach(() =>
    TestBed.configureTestingModule({
      imports: [
        FormsModule,
        HttpClientTestingModule,
        RouterTestingModule,
      ],
    }));

  // Jerry
  // Service should be created
  it('should be created', () => {
    const service: GroupService = TestBed.get(GroupService);
    expect(service).toBeTruthy();
  });

  // Jerry
  // Get all groups from back-end
  it('should return all users datasets', () => {
    const service: GroupService = TestBed.get(GroupService);
    spyOn(service, "getAllGroups");

    service.getAllGroups(() => { });

    expect(service.getAllGroups).toHaveBeenCalled();
  })

  // Jerry
  // Get group details
  it('should get group details', () => {
    const service: GroupService = TestBed.get(GroupService);

    spyOn(service, "getGroupDetails");
    service.getGroupDetails(1100, () => { });

    expect(service.getGroupDetails).toHaveBeenCalled();
  })
});
