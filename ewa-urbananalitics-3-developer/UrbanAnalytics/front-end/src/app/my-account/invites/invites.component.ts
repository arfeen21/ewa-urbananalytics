import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth/auth.service';
import { NotificationMessage } from 'src/app/models/notification/notification.model';

@Component({
  selector: 'app-invites',
  templateUrl: './invites.component.html',
  styleUrls: ['./invites.component.scss']
})
export class InvitesComponent implements OnInit {

  recievedNotifications: NotificationMessage[];
  amountOfNotifications: number = 0;

  constructor(
    private authService: AuthService
  ) {

    this.authService.getLoggedInUser((data: any) => {
      this.recievedNotifications = data.recievedNotifications;
      this.recievedNotifications.reverse();
      if (this.recievedNotifications) {
        this.amountOfNotifications = this.recievedNotifications.length;
      }
    });
  }

  ngOnInit() {
  }



}
