import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { RequestService } from '../request.service';

@Injectable({
  providedIn: 'root'
})

export class UserService {
  constructor(private requestService: RequestService, private router: Router) {
  }

  getAllMembers(callback: Function) {
    this.requestService.getRequest("user/user/all", (data) => {
      callback(data);
    });
  }

  getAllNotifications(callback: Function): any {
    this.requestService.getRequest("user/notifications/recieved", (data: any) => {
      callback(data);
    });
  };

  getUserDetails = () => {
    //return this.userDetails;
  };

  getAmountAllMembers() {
    //return this.Allmembers.length;
  }
}
