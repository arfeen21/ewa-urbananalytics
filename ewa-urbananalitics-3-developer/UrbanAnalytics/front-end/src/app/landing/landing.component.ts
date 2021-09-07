import {Component, OnInit} from '@angular/core';
import {DatasetService} from "../services/dataset/dataset.service";

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.scss']
})
export class LandingComponent implements OnInit{
  allPublicDatasets = [];
  clicked: boolean = false;

  constructor(private datasetService: DatasetService) { }

  ngOnInit() {
    this.datasetService.getMyItems( (res) => {
      this.allPublicDatasets = res;
    })
  }
}
