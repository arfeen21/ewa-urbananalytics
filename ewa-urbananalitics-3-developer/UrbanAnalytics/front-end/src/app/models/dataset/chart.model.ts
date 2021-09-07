import { Injectable } from "@angular/core";

@Injectable({
    providedIn: 'root'
})

export class ChartModelComponent {
    private data: {};
    private header;
    private xValue: Number;
    private yValue: Number;
    private groupByValue: Number;
    private typeValue: String;
    private name: String;
    private description: String;

    constructor(data: {
        data: {},
        header: [],
        name: String,
        typeValue: String,
        xValue: Number,
        yValue: Number,
        groupByValue: Number,
        description: String
    }) {
        this.data = data.data;
        this.header = data.header;
        this.name = data.name;
        this.typeValue = data.typeValue;
        this.yValue = data.yValue;
        this.xValue = data.xValue;
        this.groupByValue = data.groupByValue;
        this.description = data.description;
    }

    getTypeValue() {
        return this.typeValue;
    }

    getData() {
        return this.data;
    }
}