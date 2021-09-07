import { Injectable } from "@angular/core";
import { Region } from './region.enum';
import { formatDate } from "@angular/common";
import { ChartModelComponent } from './chart.model';
import { MyGroupsModelComponent } from '../group/my-groups.model';

@Injectable({
  providedIn: 'root'
})

export class DatasetModelComponent {
  private uploadedOn: Date;
  private id: number;

  constructor(
    private name: string = null,
    private summary: string = null,
    private region: Region = Region.EU,
    private isPublic: boolean = true,
    private data: {} = null,
    private charts: ChartModelComponent[] = [],
    private groups: {}[] = []
  ) {
  }


  getId(): number {
    return this.id;
  }

  thisDate(): any {
    let daf = new Date();
    return formatDate(daf, ' dd-MM-yyyy', 'en-NL');
  }

  addChart(chart: ChartModelComponent, id: number) {
    if (this.charts[id]) {
      this.charts[id] = chart;
    }
    else {
      this.charts.push(chart);
    }
  }

  setName(name: string) {
    this.name = name;
  }

  setDescription(description: string) {
    this.summary = description;
  }

  setRegion(region: Region) {
    this.region = region;
  }

  setData(data: {}) {
    this.data = data;
  }

  getData() {
    return this.data;
  }

  getIsPublic() {
    return this.isPublic;
  }

  getName() {
    return this.name;
  }

  getSummary() {
    return this.summary;
  }

  getCharts() {
    return this.charts;
  }

  getRegion() {
    return this.region;
  }

  setIsPublic(value: boolean) {
    this.isPublic = value;
  }

  getGroups() {
    return this.groups;
  }

  setGroups(groups: {}[]) {
    this.groups = groups;
  }
}
