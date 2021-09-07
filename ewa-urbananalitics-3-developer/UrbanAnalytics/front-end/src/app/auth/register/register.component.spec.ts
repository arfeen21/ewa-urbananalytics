import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {RegisterComponent} from './register.component';
import {FormsModule} from '@angular/forms';
import {HttpClient} from '@angular/common/http';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {RouterTestingModule} from '@angular/router/testing';
import {htmlAstToRender3Ast} from "@angular/compiler/src/render3/r3_template_transform";
import {getErrorMessage} from "codelyzer/templateAccessibilityElementsContentRule";
import {By} from '@angular/platform-browser';

describe('RegisterComponent', () => {
  // const angular = require('angular');
  let component: RegisterComponent;
  let fixture: ComponentFixture<RegisterComponent>;
  let modelObject, httpTaskService, controller, formObject;
  let $scope, $rootScope, $compile, $controller;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [RegisterComponent],
      imports: [
        FormsModule,
        HttpClientTestingModule,
        RouterTestingModule,
      ],
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  //Richard test 1
  it('should load formsmodule', () => {
    expect(FormsModule).toBeTruthy()
  });

  //Richard test 2
  it('should load HttpClientTestModule', () => {
    expect(HttpClientTestingModule).toBeTruthy()
  });

  //Richard test 3
  it('should load RouterTestingModule', () => {
    expect(RouterTestingModule).toBeTruthy()
  });

  //Richard test 4
  it('should have the correct pagename', () => {
    expect(component.form).toBeTruthy()
  });

  //Richard Test 5
  it('Firstname should be empty', () => {
    fixture.detectChanges();
    fixture.whenStable().then(() => {
      let firstName: HTMLInputElement = fixture.debugElement.query(By.css('#firstName')).nativeElement;
    expect(firstName).toContain("")
    })
  });

  //Richard Test 6
  it('Lasstname should be empty', () => {
    fixture.detectChanges();
    fixture.whenStable().then(() => {
      let lastName: HTMLInputElement = fixture.debugElement.query(By.css('#lastName')).nativeElement;
      expect(lastName).toContain("")
    })
  });

  // Richard Test 7
  it('Email should be empty', () => {
    fixture.detectChanges();
    fixture.whenStable().then(() => {
      let email: HTMLInputElement = fixture.debugElement.query(By.css('#email')).nativeElement;
      expect(email).toContain("")
    })
  });

  //Richard Test 8
  it('Firstname should be Jaap', () => {
    fixture.detectChanges();
    fixture.whenStable().then(() => {
      let firstName: HTMLInputElement = fixture.debugElement.query(By.css('#firstName')).nativeElement;
      let input = fixture.debugElement.query(By.css('input'));
      let el = input.nativeElement;
      el.value = 'Jaap';
      el.dispatchEvent(new Event('input'));

      expect(firstName).toContain("Jaap")
    })
  })

  //Richard Test 9
  it('Password should be 123456', () => {
    fixture.detectChanges();
    fixture.whenStable().then(() => {
      let password: HTMLInputElement = fixture.debugElement.query(By.css('#password')).nativeElement;
      let input = fixture.debugElement.query(By.css('input'));
      let el = input.nativeElement;
      el.value = '123456';
      el.dispatchEvent(new Event('input'));

      expect(password).toContain("123456")
    })
  });

  //Richard test 10
  it('Password Confirmation should be 123456', () => {
    fixture.detectChanges();
    fixture.whenStable().then(() => {
      let rePassword: HTMLInputElement = fixture.debugElement.query(By.css('#rePassword')).nativeElement;
      let input = fixture.debugElement.query(By.css('input'));
      let el = input.nativeElement;
      el.value = '123456';
      el.dispatchEvent(new Event('input'));

      expect(rePassword).toContain("123456")
    })
  });


});
