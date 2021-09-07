import { Injectable } from '@angular/core';
import { RequestService } from '../request.service';
import { Router } from '@angular/router';


@Injectable({
  providedIn: 'root'
})

export class UserService {
  constructor(private requestService: RequestService, private router: Router) {



  }

  getAllNotifications(callback: Function): any {
    this.requestService.getRequest("user/notifications/recieved", (data: any) => {
      callback(data);
    });
  };

}
