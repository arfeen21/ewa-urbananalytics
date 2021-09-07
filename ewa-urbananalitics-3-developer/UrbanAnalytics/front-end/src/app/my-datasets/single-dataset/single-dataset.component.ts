import { Component, OnInit } from '@angular/core';
import { RequestService } from 'src/app/services/request.service';
import { map, tap } from 'rxjs/operators';
import { Router, ActivatedRoute } from '@angular/router';
import { DatasetModelComponent } from 'src/app/models/dataset/dataset.model';
import { Location } from '@angular/common';

@Component({
  selector: 'app-single-dataset',
  templateUrl: './single-dataset.component.html',
  styleUrls: ['./single-dataset.component.scss']
})
export class SingleDatasetComponent implements OnInit {
  private dataset: DatasetModelComponent;

  constructor(private requestService: RequestService, private route: ActivatedRoute, public _location: Location) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.requestService.getRequest("dataset/" + params['id'], (resp: any) => {
        this.dataset = resp;
      });
    });
  }

  backClicked() {
    this._location.back();
  }
}
