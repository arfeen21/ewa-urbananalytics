import { async, ComponentFixture, TestBed, inject, fakeAsync } from '@angular/core/testing';

import { LoginComponent } from './login.component';
import { DebugElement } from '@angular/core';
import { FormsModule, ReactiveFormsModule, EmailValidator } from '@angular/forms';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { AuthService } from 'src/app/services/auth/auth.service';
import { Router } from '@angular/router';
import { UserModelComponent } from 'src/app/models/user/user.model';
import { UserService } from 'src/app/services/user/user.service';
import { variable } from '@angular/compiler/src/output/output_ast';
import { RegisterComponent } from '../register/register.component';
import { By } from '@angular/platform-browser';
 
 


describe('login', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let label: HTMLElement;

 
  beforeEach(async(() => {

    TestBed.configureTestingModule({
      declarations: [LoginComponent, RegisterComponent],
      imports: [
        FormsModule,
        ReactiveFormsModule,
        HttpClientTestingModule,
        RouterTestingModule,
        RouterTestingModule.withRoutes(
          [
            {path: 'auth/register' , component: RegisterComponent}
          ]
        )
      ],
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    label = fixture.nativeElement.querySelector('label');
  

    fixture.detectChanges();
  });

   
  //arfeen 
  it('should create', () => {
    expect(component).toBeTruthy();
  });

  //arfeen.test3
  it('loginButton should work' , () => {
      spyOn(component, 'logIn');
      fixture.detectChanges();
      fixture.whenStable().then( ()=> {
          expect(component.logIn).toHaveBeenCalledWith();
      });
  });

  //arfeen, test4
  it('should show error', () => {
    expect(label.textContent).toContain("error");
  });

//arfeen, test5
  it('form invalid when empty' , () => {
      expect(component.formData.valid).toBeFalsy();
  });

  //arfeen, test6 
  it('email field validity' , () => {
      let username = component.formData.controls['username'];
      expect(username.valid).toBeFalsy();

      let errors = {};
      errors = username.errors || {};
      expect(errors['required']).toBeTruthy();
  });

  // arfeen, test 7 should validate the username(email) of the user
  it('should validate userName if empty' , () => {
    let username = component.formData.controls['username'];
    expect(username.valid).toBeFalsy();
  });

  //arfeen, test8 
  it('submitting a form' , () => {
      expect(component.formData.valid).toBeFalsy();
      component.formData.controls['username'].setValue("alex.ernst@sweden.se");
      component.formData.controls['pwd'].setValue("tester123");
      expect(component.formData.valid).toBeTruthy();
  });


//arfeen
  it('should validate password if empty' , () => {
    let username = component.formData.controls['pwd'];
    expect(username.valid).toBeFalsy();
  });

//arfeen, test 10
it('should contain input fields' , () => {
  expect(fixture.nativeElement.querySelector('input')).toBeTruthy();
});






      
  });
 










