import { Component, OnInit, ViewChild, ElementRef, AfterViewInit, Output } from '@angular/core';
import { UploadService } from 'src/app/services/dataset/upload/upload.service';
import * as XLSX from 'xlsx';
import * as Chart from 'chart.js';
import { Router } from '@angular/router';
import { ChartModelComponent } from 'src/app/models/dataset/chart.model';
import { EventEmitter } from 'protractor';
import { Observable, Subject } from 'rxjs';

@Component({
  selector: 'app-graph',
  templateUrl: './graph.component.html',
  styleUrls: ['./graph.component.scss']
})
export class GraphComponent implements AfterViewInit {
  private showChart: Boolean = true;
  private canvas: HTMLCanvasElement;

  public id;
  private data: {} = this.uploadService.getData();
  private header: [] = null;
  private xValue: number;
  private yValue: number;
  private groupByValue: number;
  private typeValue: string = "bar";
  private name: string;
  private chart: Chart;
  private description: string;

  private chartData: ChartModelComponent;

  @ViewChild('graph', { static: false }) myCanvas: ElementRef;

  constructor(private uploadService: UploadService, private router: Router) { }

  ngOnInit() {
    this.header = this.data ? this.data[0] : [];
  }

  ngAfterViewInit() {
    this.canvas = (<HTMLCanvasElement>this.myCanvas.nativeElement);
    this.loadGraph();
  }

  descriptionChange(event: any) {
    this.description = event.target.value;
  }

  nameChange(event: any) {
    this.name = event.target.value;
  }

  xChange(event: any) {
    this.xValue = event.target.value;
    this.loadGraph();
  }

  sortByChange(event: any) {
    this.groupByValue = event.target.value;
    this.loadGraph();
  }

  yChange(event: any) {
    this.yValue = event.target.value;
    this.loadGraph();
  }

  typeChange(event: any) {
    this.typeValue = event.target.value;
    this.loadGraph();
  }

  deleteGrapFromChild = new Subject<number>();
  deleteGraph() {
    this.deleteGrapFromChild.next(this.id);
  }

  loadGraph() {
    if (this.chart) this.chart.destroy();

    Chart.defaults.global.defaultFontColor = 'white';
    this.chart = new Chart(this.canvas.getContext('2d'), {
      // The type of chart we want to create
      type: this.typeValue,

      // The data for our dataset
      data: this.generateChart(),
      // Options for the chart
      options: {
        maintainAspectRatio: false,
        responsive: true,
        legend: {
          display: false,
        },
        scales: {
          yAxes: [{
            ticks: {
              beginAtZero: true
            },
            yAxes: [{
              stacked: true
            }]
          }]
        }
      }
    });

    this.saveData();
  }

  saveData() {
    let dataset = new ChartModelComponent({
      data: this.data,
      header: this.header,
      xValue: this.xValue,
      yValue: this.yValue,
      groupByValue: this.groupByValue,
      typeValue: this.typeValue,
      name: this.name,
      description: this.description
    })

    if (dataset != this.chartData) {
      this.chartData = dataset;
      this.uploadService.saveChart(dataset, this.id);
    }
  }

  generateChart() {
    let xHeaders = [];

    let data = {
      labels: [],
      datasets: []
    };

    for (const rowIndex in this.data) {
      if (rowIndex != "0") {
        const row = this.data[rowIndex];

        if (this.groupByValue) {
          if (!data.labels.includes(row[this.groupByValue])) {
            data.labels.push(row[this.groupByValue]);
          }
        }
        else {
          data.labels.push(row[this.xValue]);
        }

        if (!xHeaders.includes(row[this.xValue])) {
          xHeaders.push(row[this.xValue]);
        }
      }
    }

    let colorToggle = true;

    for (const xHeaderIndex in xHeaders) {
      let xHeader = xHeaders[xHeaderIndex];
      let result = [];

      for (const rowIndex in this.data) {
        let row = this.data[rowIndex]

        if (row[this.xValue] === xHeader) {
          result.push(row[this.yValue]);
        }
      }

      data.datasets.push({
        label: [xHeader, this.header[this.yValue]],
        backgroundColor: this.typeValue === "bar" ? colorToggle ? '#c05e2c' : '#7245b8' : null,
        borderColor: colorToggle ? '#c05e2c' : '#7245b8',
        data: result,
      });

      colorToggle = !colorToggle;
    }

    return data;
  }

  hide(body, header) {
    body.classList.toggle('hidden');
    header.classList.toggle('hidden')
  }

  showTable(graphContainer, tableContainer) {
    tableContainer.classList.remove("hidden");
    graphContainer.classList.add("hidden");
  }

  showGraph(graphContainer, tableContainer) {
    graphContainer.classList.remove("hidden");
    tableContainer.classList.add("hidden");
  }
}
