import { Injectable } from '@angular/core';
import { DatasetModelComponent } from 'src/app/models/dataset/dataset.model';
import { ChartModelComponent } from 'src/app/models/dataset/chart.model';
import { Region } from 'src/app/models/dataset/region.enum';
import { RequestService } from '../../request.service';

@Injectable({
  providedIn: 'root'
})
export class UploadService {
  public dataset: DatasetModelComponent = new DatasetModelComponent();

  constructor(private requestService: RequestService) { }

  setAccess(value: any) {
    this.dataset.setIsPublic(value == "public" ? true : false);
  }

  setData(data: {}) {
    this.dataset.setData(data);
  }

  getData() {
    return this.dataset.getData();
  }

  setName = (name: string) => {
    this.dataset.setName(name)
  }

  setDescription = (description: string) => {
    this.dataset.setDescription(description);
  }

  setRegion = (region: Region) => {
    this.dataset.setRegion(region);
  }

  saveChart = (chart: ChartModelComponent, id: number) => {
    this.dataset.addChart(chart, id);
  }

  saveDataset(callback: Function): void {
    let formData = new FormData();
    let datasetInfo = {
      name: this.dataset.getName(),
      summary: this.dataset.getSummary(),
      region: this.dataset.getRegion(),
      public: this.dataset.getIsPublic(),
      data: this.dataset.getData(),
      charts: this.dataset.getCharts(),
      groups: this.dataset.getGroups()
    };


    formData.append("datasetInfo", JSON.stringify(datasetInfo));

    this.requestService.postRequest("dataset/add/", formData, (data: any) => {
      callback(data)
    });
  }

  reset() {
    this.dataset = new DatasetModelComponent();
  }
}
