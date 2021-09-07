import { Component, OnInit, Input, ViewChild, ElementRef, AfterViewInit } from '@angular/core';
import { ChartModelComponent } from 'src/app/models/dataset/chart.model';
import * as Chart from 'chart.js';

@Component({
  selector: 'app-chart',
  templateUrl: './chart.component.html',
  styleUrls: ['./chart.component.scss']
})
export class ChartComponent implements AfterViewInit {
  @Input() chartData: any;
  @ViewChild('chart', { static: false }) myCanvas: ElementRef;

  private chart: Chart;
  private canvas: HTMLCanvasElement;

  constructor() { }

  ngAfterViewInit() {
    this.canvas = (<HTMLCanvasElement>this.myCanvas.nativeElement);

    if (this.chart) this.chart.destroy();

    if (this.chartData) {
      this.chart = new Chart(this.canvas.getContext('2d'), {
        // The type of chart we want to create
        type: this.chartData.typeValue,

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
    }
  }

  generateChart() {
    let xHeaders = [];

    let data = {
      labels: [],
      datasets: []
    };

    for (const rowIndex in this.chartData.data) {
      if (rowIndex != "0") {
        const row = this.chartData.data[rowIndex];

        if (this.chartData.groupByValue) {
          if (!data.labels.includes(row[this.chartData.groupByValue])) {
            data.labels.push(row[this.chartData.groupByValue]);
          }
        }
        else {
          data.labels.push(row[this.chartData.xValue]);
        }

        if (!xHeaders.includes(row[this.chartData.xValue])) {
          xHeaders.push(row[this.chartData.xValue]);
        }
      }
    }

    let colorToggle = true;

    for (const xHeaderIndex in xHeaders) {
      let xHeader = xHeaders[xHeaderIndex];
      let result = [];

      for (const rowIndex in this.chartData.data) {
        let row = this.chartData.data[rowIndex]

        if (row[this.chartData.xValue] === xHeader) {
          result.push(row[this.chartData.yValue]);
        }
      }

      data.datasets.push({
        label: [xHeader, this.chartData.header[this.chartData.yValue]],
        backgroundColor: this.chartData.typeValue === "bar" ? colorToggle ? '#c05e2c' : '#7245b8' : null,
        borderColor: colorToggle ? '#c05e2c' : '#7245b8',
        data: result,
      });

      colorToggle = !colorToggle;
    }

    return data;
  }
}
