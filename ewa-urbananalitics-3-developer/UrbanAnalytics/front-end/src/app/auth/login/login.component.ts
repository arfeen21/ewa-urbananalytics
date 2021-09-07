import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { FormGroup, FormControl } from '@angular/forms';
import { UserModelComponent } from 'src/app/models/user/user.model';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  onSubmit(arg0: { email: any; password: any; }) {
    throw new Error("Method not implemented.");
  }
  @ViewChild("errorMessage", { static: false }) errorMessage: ElementRef;
  formData: FormGroup;
  submitted: any;

  constructor(private router: Router,
    private authService: AuthService
  ) { }

  ngOnInit() {
    this.formData = new FormGroup({
      username: new FormControl(),
      pwd: new FormControl()
    });
  }

  public signThis(): void {
    this.router.navigate(['auth/register']);
  }

  public logIn(event: Event): void {
    event.preventDefault();
    let user: UserModelComponent = new UserModelComponent("", "", this.formData.value.username, this.formData.value.pwd);

    this.authService.login(user, (data) => {
      if (data.error) {
        if (data.error.text) {
          this.errorMessage.nativeElement.classList.add("success");
          this.errorMessage.nativeElement.classList.remove("failure");
          this.errorMessage.nativeElement.innerText = data.error.text;
        }
        else {
          this.errorMessage.nativeElement.classList.add("failure");
          this.errorMessage.nativeElement.classList.remove("success");
          this.errorMessage.nativeElement.innerText = data.error;
        }
      }
      else {
        this.errorMessage.nativeElement.classList.add("failure");
        this.errorMessage.nativeElement.classList.remove("success");
        this.errorMessage.nativeElement.innerText = "error";
        //console.log(data);

      }

      if (data.status == 200) {
        let jwtToken = data.headers.get("Authorization");

        if (jwtToken) {
          sessionStorage.setItem("jwtToken", jwtToken);
          // Check if user is logged in
          this.authService.isLoggedIn();
          // Setting local jwtToken
          this.router.navigate(["/dashboard"]);
        }
      }
    });
  }
}