import { Injectable } from '@angular/core';
import { DatasetModelComponent } from "../../models/dataset/dataset.model";
import { HttpClient, HttpErrorResponse, HttpResponse, HttpHeaders } from '@angular/common/http';
import { share } from 'rxjs/operators';
import { RequestService } from '../request.service';

@Injectable({
  providedIn: 'root'
})
export class DatasetService {
  public datasets;

  constructor(private httpClient: HttpClient, private requestService: RequestService) { }


  getMyItems(callback: Function): void {
    this.httpClient.get("http://localhost:8080/dataset/all").pipe(share()).subscribe(
      (data: any) => {
        callback(data)
      },
      (err: any) => {
        callback(err)
      }
    );
  }

  createDataset(callback: Function, dataset: DatasetModelComponent) {
    this.requestService.postRequest("http://localhost:8080/dataset/add/", dataset, (data) => {
      callback(data);
    });
  }
}
