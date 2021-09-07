import { Component, OnInit } from '@angular/core';
import { DatasetService } from '../services/dataset/dataset.service';
import { debug } from 'util';
import { RequestService } from '../services/request.service';
import { NotificationMessage } from '../models/notification/notification.model';
import { UserModelComponent } from '../models/user/user.model';
import { AuthService } from '../services/auth/auth.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  private allDatasets = [];
  recievedNotifications: NotificationMessage[];
  amountOfNotifications: number = 0;


  constructor(private datasetService: DatasetService, private requestService: RequestService, private authService: AuthService) {
    datasetService.getMyItems((res) => {
      this.allDatasets = res;
    });

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
