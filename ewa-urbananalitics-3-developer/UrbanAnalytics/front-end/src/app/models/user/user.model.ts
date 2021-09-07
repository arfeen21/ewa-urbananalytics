import { Injectable } from "@angular/core";
import { NotificationMessage } from '../notification/notification.model';

@Injectable({
  providedIn: 'root'
})

export class UserModelComponent {
  private userId: number;
  private location: string;
  private schoolInfo: string;
  private aboutMe: string;
  private jobDesc: string;
  private tel: string;
  private baan: string;
  private foto: string;
  private website: string = "Not set";
  private sendNotifications: NotificationMessage[];
  private recievedNotifications: NotificationMessage[];

  constructor(private firstName: string = null, private lastName: string = null, private email: string, private password: string) { }


  getEmail() {
    return this.email;
  }

  getId() {
    return this.userId;
  }

  getSendNotifications() {
    return this.sendNotifications;
  }

  getRecievedNotifications() {
    return this.recievedNotifications;
  }
}
