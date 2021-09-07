import { Component, OnInit, ViewChild, ViewContainerRef, ComponentFactoryResolver, ComponentFactory, Output } from '@angular/core';
import { GraphComponent } from '../graph/graph.component';
import { Router } from '@angular/router';
import { UploadService } from 'src/app/services/dataset/upload/upload.service';
import { DatasetService } from 'src/app/services/dataset/dataset.service';
import { GroupService } from 'src/app/services/group/group.service';
import { AuthService } from 'src/app/services/auth/auth.service';
import { IDropdownSettings } from 'ng-multiselect-dropdown';

@Component({
  selector: 'app-graph-selection',
  templateUrl: './graph-selection.component.html',
  styleUrls: ['./graph-selection.component.scss']
})
export class GraphSelectionComponent implements OnInit {
  private data: {} = this.uploadService.getData();
  private graphElements: ComponentFactory<GraphComponent>[] = [];

  private selectedItems = [];
  private dropdownList: {}[];
  private dropdownSettings: IDropdownSettings = {};

  @ViewChild("graphContainer", { static: false, read: ViewContainerRef }) graphContainer: ViewContainerRef;

  constructor(private authService: AuthService, private componentFactoryResolver: ComponentFactoryResolver, private router: Router, private uploadService: UploadService, private datasetService: DatasetService, private groupService: GroupService) {
    this.dropdownSettings = {
      singleSelection: false,
      idField: 'item_id',
      textField: 'item_text',
      selectAllText: 'Select All',
      unSelectAllText: 'Unselect All',
      itemsShowLimit: 7,
      allowSearchFilter: true
    };
  }



  ngOnInit() {
    if (!this.data) {
      this.router.navigate(["/datasets/create/upload"]);
    }
    else {
      this.authService.getLoggedInUser((user: any) => {
        this.groupService.getAllGroups((groups: any) => {
          let groupList: {}[] = [];

          groups.forEach((group: any) => {
            groupList.push({
              item_id: {
                "group": group.groupId,
                "dataset": null,
                "fromUser": user
              }, item_text: group.name
            })
          });

          this.dropdownList = groupList;
        });
      });
    }
  }

  onItemSelect(item: any) {
    this.selectedItems.push(item);
  }

  onSelectAll(items: any) {
    this.selectedItems.push(items);
  }

  addGraph() {
    let childComponent = this.componentFactoryResolver.resolveComponentFactory(GraphComponent);
    let instance = this.graphContainer.createComponent(childComponent).instance;

    instance.id = this.graphElements.length;

    instance.deleteGrapFromChild.subscribe((val: any) => {
      this.graphElements.splice(val, 1);
      this.graphContainer.remove(val);
    })
    this.graphElements.push(childComponent)

    document.querySelector(".content").scrollTo(0, document.querySelector(".content").scrollHeight);
  }

  goBack() {
    this.router.navigate(['datasets/create/upload']);
  }

  saveDataset() {
    this.uploadService.dataset.setGroups(this.getGroups());

    this.uploadService.saveDataset((resp: any) => {
      this.uploadService.reset();
      this.router.navigate(["datasets/single/" + resp.body.response]);
    });
  }

  getGroups() {
    let groups: {}[] = [];

    this.selectedItems.forEach((aGroup: any) => {
      groups.push(aGroup.item_id);
    });

    return groups
  }

  datasetNameChange(event: any) {
    this.uploadService.setName(event.target.value)
  }

  datasetDescChange(event: any) {
    this.uploadService.setDescription(event.target.value);
  }

  datasetSetRegion(event: any) {
    this.uploadService.setRegion(event.target.value);
  }

  datasetSetAccess(event: any) {
    this.uploadService.setAccess(event.target.value);
  }
}
