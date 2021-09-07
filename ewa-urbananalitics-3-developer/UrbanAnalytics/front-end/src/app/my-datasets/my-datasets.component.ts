
import { Component, OnInit } from '@angular/core';
import { DatasetModelComponent } from "../models/dataset/dataset.model";
import { DatasetService } from "../services/dataset/dataset.service";
import { MyGroupsModelComponent } from "../models/group/my-groups.model";
import { Region } from '../models/dataset/region.enum';
import { GroupService } from '../services/group/group.service';



@Component({
  selector: 'app-my-datasets',
  templateUrl: './my-datasets.component.html',
  styleUrls: ['./my-datasets.component.scss']
})

export class MyDatasetsComponent implements OnInit {
  allDatasets = [];
  usedDatasets = [];
  allGroups = [];
  usedGroups = [];
  selectedSort: string = "region";
  selectedDataset: DatasetModelComponent;
  enums = Object.keys(Region);
  filter = "";
  sortBy = "region";

  constructor(private datasetService: DatasetService, private groupService: GroupService) { }

  ngOnInit() {
    this.datasetService.getMyItems((res) => {
      this.allDatasets = res;
      this.usedDatasets = res;
    })

    this.groupService.getAllGroups((res: any) => {
      this.allGroups = res;
      this.usedGroups = res;
    });
  }

  filterChange(event: any) {
    //console.log(event);

    if (this.sortBy == "region") {
      this.usedDatasets = [];

      this.allDatasets.forEach((dataset: any) => {
        let name: string = dataset.name;

        if (name.toLowerCase().includes(event.toLowerCase())) {
          this.usedDatasets.push(dataset);
        }
      })
    }

    if (this.sortBy == "group") {
      this.usedGroups = [];

      this.allGroups.forEach((group: any) => {
        let usableDatasets = [];

        group.datasets.forEach((dataset: any) => {
          let name: string = dataset.dataset.name;

          if (name.toLowerCase().includes(event.toLowerCase()) || event == "") {
            usableDatasets.push(dataset);

          }
        })
        if (usableDatasets.length > 0) {
          let groupCopy = Object.assign(group);

          groupCopy.datasets = usableDatasets;
          this.usedGroups.push(groupCopy);
        }
      });
    }

    this.updateValues();
  }

  sortByUpdate(event: any) {
    this.sortBy = event;
    this.filterChange(this.filter);
  }

  updateValues() {
    this.datasetService.getMyItems((res) => {
      if (res != this.allDatasets) {
        this.allDatasets = res;
      }
    })

    this.groupService.getAllGroups((res: any) => {
      if (res != this.allGroups) {
        this.allGroups = res;
      }
    });
  }
}
