import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { NavbarService } from '../services/navbar/navbar.service';
import { AuthService } from '../services/auth/auth.service';
import { NotificationMessage } from '../models/notification/notification.model';
import { UserService } from '../services/user/user.service';
import { RequestService } from '../services/request.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss', './hamburger.scss', './notifications.scss']
})
export class HeaderComponent {

  navExtended: boolean;
  clicks: number = 0;
  isLoggedIn: boolean = false;
  recievedNotifications: NotificationMessage[];
  amountOfNotifications: number = 0;

  constructor(
    private navBarService: NavbarService,
    private authService: AuthService,
    private userService: UserService,
    private requestService: RequestService

  ) {
    this.navBarService.getIsExtended().subscribe(val => {
      this.navExtended = val;
    });

    this.authService.getUid().subscribe(isLoggedIn => {
      this.authService.isLoggedIn();
      isLoggedIn ? this.isLoggedIn = true : this.isLoggedIn = false;

    })

    this.setNotifications();
  }

  updateNotifications() {
    if (this.recievedNotifications === null) {
      this.amountOfNotifications = 0;
      return;
    }
    this.amountOfNotifications = this.recievedNotifications.length;
  }

  /**
   * Gets the notifications en sets them to be displayed on the DOM
   */
  setNotifications() {
    this.authService.getLoggedInUser((data: any) => {
      this.recievedNotifications = data.recievedNotifications;
      if (this.recievedNotifications) {
        let counter = 0;
        for (const key in data.recievedNotifications) {
          if (!data.recievedNotifications[key].read) {
            this.recievedNotifications.push(data.recievedNotifications[key]);
            counter++;
          }
        }
        if (this.recievedNotifications) {
          this.recievedNotifications.reverse();
        }
        this.amountOfNotifications = counter;
      }
    });
  }

  /**
   * Sets all the notifications on read
   */
  setNotificationsRead() {
    this.amountOfNotifications = 0;
    this.recievedNotifications = null;
    this.requestService.postRequest("user/notifications/read", null, (data: any) => {
      this.setNotifications();
    });
  }

  logout() {
    this.authService.logOut(true);
  }

  toggleNav() {
    this.navBarService.toggleNavBar();
  }
}
