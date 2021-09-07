import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-error-route',
  templateUrl: './error-route.component.html',
  styleUrls: ['./error-route.component.scss']
})
export class ErrorRouteComponent implements OnInit {
  pagename: string;

  constructor(private router: Router) { }

  ngOnInit() {
    this.pagename = this.router.url;
  }

}
