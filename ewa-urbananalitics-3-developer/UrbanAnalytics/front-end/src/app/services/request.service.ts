import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { share } from 'rxjs/operators';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class RequestService {
  private serverUrl = "http://localhost:8080/";

  constructor(private httpClient: HttpClient, private router: Router) {
  }

  postRequest(url: String, body: any, callback: Function) {
    let headers = {};

    if (sessionStorage.getItem("jwtToken")) headers = { "Authentication": sessionStorage.getItem("jwtToken") };

    //console.log(body);


    let obs = this.httpClient.post(this.serverUrl + url, body, {
      headers: headers,
      observe: "response"
    }).pipe(share());

    obs.subscribe(
      (data: any) => {
        callback(data)
      },
      (err: any) => {
        if (err.message == "JWT signature does not match locally computed signature. JWT validity cannot be asserted and should not be trusted.") {
          sessionStorage.clear();
          this.router.navigate(["/auth/login"]);
        } else {
          callback(err)
        }
      }
    )
  }

  getRequest(url: String, callback: Function) {
    let obs = this.httpClient.get(this.serverUrl + url, {
      headers: {
        "Authentication": sessionStorage.getItem("jwtToken"),
        observe: "body"
      }
    }).pipe(share());

    obs.subscribe(
      (data: any) => {
        callback(data)
      },
      (err: any) => {
        if (err.message == "JWT signature does not match locally computed signature. JWT validity cannot be asserted and should not be trusted.") {
          sessionStorage.clear();
          this.router.navigate(["/auth/login"]);
        } else {
          callback(err)
        }
      }
    )
  }
}
