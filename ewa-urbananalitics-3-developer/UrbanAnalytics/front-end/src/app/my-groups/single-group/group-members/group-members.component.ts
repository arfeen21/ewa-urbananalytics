import { Component, Injectable, OnInit } from '@angular/core';

@Injectable({
  providedIn: 'root'
})

@Component({
  selector: 'app-group-members',
  templateUrl: './group-members.component.html',
  styleUrls: ['./group-members.component.scss']
})
export class GroupMembersComponent implements OnInit {
  members = [];
  img;
  imgHeight: number;

  constructor() {
    this.img = document.getElementById('profilePic');
  }


  ngOnInit() {
  }

  public getImgheight() {
    this.imgHeight = this.img.height;
  }
}
