import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { NgForm } from '@angular/forms';
import { UserModelComponent } from 'src/app/models/user/user.model';
import { HttpResponse } from '@angular/common/http';
import { AuthService } from 'src/app/services/auth/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  @ViewChild("form", { static: false }) form: NgForm;
  @ViewChild("errorMessage", { static: false }) errorMessage: ElementRef;

  constructor(private authService: AuthService, private router: Router) { }

  register() {
    event.preventDefault();
    let formValue = this.form.form.value;

    if (formValue.password === formValue.rePassword) {
      let newUser = new UserModelComponent(formValue.firstName, formValue.lastName, formValue.email, formValue.password);

      this.authService.registerUser(newUser, (data: any) => {
        if (data.error.text) {
          this.errorMessage.nativeElement.classList.remove("failure");
          this.errorMessage.nativeElement.classList.add("success");

          this.errorMessage.nativeElement.innerHTML = data.error.text;

          //small time out so the user is able to see that registering was succesfull
          setTimeout(() => {
            this.router.navigate(["/auth/login"]);
          }, 2000);  //2s
        }
        else {
          this.errorMessage.nativeElement.classList.remove("success");
          this.errorMessage.nativeElement.classList.add("failure");

          this.errorMessage.nativeElement.innerHTML = data.error;

        }
      })
    }
    else {
      this.errorMessage.nativeElement.classList.remove("success");
      this.errorMessage.nativeElement.classList.add("failure");

      this.errorMessage.nativeElement.innerHTML = "Please enter matching passwords.";
    }
  }

  ngOnInit() {
  }


}
