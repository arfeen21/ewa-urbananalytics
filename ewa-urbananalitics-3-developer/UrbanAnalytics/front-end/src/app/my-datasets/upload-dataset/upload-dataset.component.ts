import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DatasetService } from 'src/app/services/dataset/dataset.service';
import { UploadService } from 'src/app/services/dataset/upload/upload.service';

@Component({
  selector: 'app-upload-dataset',
  templateUrl: './upload-dataset.component.html',
  styleUrls: ['./upload-dataset.component.scss']
})
export class UploadDatasetComponent implements OnInit {

  constructor(private router: Router, private uploadService: UploadService) { }

  ngOnInit() {
    //this.router.navigate(['datasets/create/upload']);
  }

}
