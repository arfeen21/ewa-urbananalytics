import { Injectable } from '@angular/core';
import { UserModelComponent } from 'src/app/models/user/user.model';
import { HttpClient } from '@angular/common/http';
import { share } from 'rxjs/operators';
import { Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { RequestService } from '../request.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  //UID as behavior subject so it can be made into an observable
  public uid: BehaviorSubject<number>;

  constructor(private httpClient: HttpClient, private router: Router, private requestService: RequestService) {
    this.uid = new BehaviorSubject(null);
  }

  //Gets the Uid as observable
  //
  //return the users id as observable
  getUid() {
    return this.uid.asObservable();
  }

  getId() {
    return this.uid.getValue();
  }

  registerUser(user: UserModelComponent, callback: Function): void {
    this.requestService.postRequest("/auth/register", user, (data) => {
      callback(data)
    });
  }

  login(user: UserModelComponent, callback: Function): void {
    this.requestService.postRequest("/auth/login", user, (data) => {
      callback(data)
    });
  }

  /*tries to log in with given credentials
  return: true if login is succesfull false if not */
  logOut(redirect: boolean) {
    //setting current login uid to null
    sessionStorage.removeItem("jwtToken");
    this.uid.next(null);
    //If redirect is true redirect back to loginpage
    if (redirect) this.router.navigate([""]);
  }

  //returns user that is currently logged in
  getLoggedInUser(callback: Function): any {
    this.requestService.getRequest("user/user", (data: any) => {
      callback(data);
    });
  };


  //boolean if current session is a logged in session
  //
  //return: true if logged in false if not
  isLoggedIn(): boolean {
    if (sessionStorage.getItem("jwtToken")) {
      this.requestService.getRequest("user/user", (data: any) => {
        if (data.userId) {
          if (this.uid.getValue() != data.userId) {
            this.uid.next(data.userId)
          }

          return true;
        }
        else {
          sessionStorage.removeItem("jwtToken");
          if (this.uid.getValue() != null) {
            this.uid.next(null);
          }
          this.router.navigate(["/auth/login"]);
          return false;
        }
      })
    }
    else {
      return false;
    }
  }
}
