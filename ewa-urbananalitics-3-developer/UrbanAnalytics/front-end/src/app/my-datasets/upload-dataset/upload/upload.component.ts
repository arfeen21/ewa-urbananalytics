import { Component, OnInit } from '@angular/core';
import * as XLSX from 'xlsx';
import { DatasetService } from 'src/app/services/dataset/dataset.service';
import { UploadService } from 'src/app/services/dataset/upload/upload.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.scss']
})
export class UploadComponent implements OnInit {
  constructor(private uploadService: UploadService, private router: Router) { }

  ngOnInit() {
  }

  upload(file: File) {
    //console.log(file);
  }

  setFile(evt: any) {
    const reader: FileReader = new FileReader();
    const target: DataTransfer = <DataTransfer>(evt.target);

    if (target.files.length !== 1) throw new Error('Cannot use multiple files');

    reader.onload = (e: any) => {
      /* read workbook */
      const bstr: string = e.target.result;
      const wb: XLSX.WorkBook = XLSX.read(bstr, { type: 'binary' });

      /* grab first sheet */
      const wsname: string = wb.SheetNames[0];
      const ws: XLSX.WorkSheet = wb.Sheets[wsname];

      /* save data */
      let data = (XLSX.utils.sheet_to_json(ws, { header: 1 }))
      this.uploadService.setData(data);

      this.router.navigate(['datasets/create/graphs']);
    };

    reader.readAsBinaryString(target.files[0]);
  }
}
