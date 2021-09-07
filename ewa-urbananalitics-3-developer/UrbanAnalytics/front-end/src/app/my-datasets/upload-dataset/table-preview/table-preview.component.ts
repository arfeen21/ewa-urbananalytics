import { Component, OnInit } from '@angular/core';
import { UploadService } from 'src/app/services/dataset/upload/upload.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-table-preview',
  templateUrl: './table-preview.component.html',
  styleUrls: ['./table-preview.component.scss']
})
export class TablePreviewComponent implements OnInit {
  private data: {};

  constructor(private uploadService: UploadService, private router: Router) { }

  ngOnInit() {
    this.data = this.uploadService.getData();

    if (!this.data) {
      this.router.navigate(["/datasets/create/upload"]);
    }
  }
}
