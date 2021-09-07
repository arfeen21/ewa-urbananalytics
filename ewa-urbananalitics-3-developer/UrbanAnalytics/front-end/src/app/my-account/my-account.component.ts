import { Component, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { AuthService } from '../services/auth/auth.service';
import { UserModelComponent } from '../models/user/user.model';
import { NotificationMessage } from '../models/notification/notification.model';
import { NgForm } from '@angular/forms';
import { UserService } from '../services/user/user.service';
import { RequestService } from '../services/request.service';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

@Component({
  selector: 'app-my-account',
  templateUrl: './my-account.component.html',
  styleUrls: ['./my-account.component.scss']
})
export class MyAccountComponent {
  private user: UserModelComponent;
  private edit: boolean = false;

  constructor(private authService: AuthService,
    private router: Router, private http: HttpClient,
    private requestService: RequestService) {

    if (!authService.isLoggedIn()) this.router.navigate["auth/login"];

    this.authService.getLoggedInUser((user: any) => {
      //console.log(user);
      this.user = user;
    });
  }

  save(form: NgForm) {

    //console.log(form.form.value);

    this.requestService.postRequest("user/update", form.form.value, (data: any) => {
      //console.log(data);
    })

    alert("Account details have been updated!");
    this.edit = false;

  }
}


