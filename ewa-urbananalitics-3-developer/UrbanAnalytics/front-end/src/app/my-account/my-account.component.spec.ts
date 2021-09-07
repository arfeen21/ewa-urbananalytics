import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MyAccountComponent } from './my-account.component';
import { FormsModule } from '@angular/forms';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';
import { NgSwitcheryModule } from 'angular-switchery-ios';
import { InvitesComponent } from './invites/invites.component';
import { DebugElement } from '@angular/core';

describe('MyAccountComponent', () => {
  let component: MyAccountComponent;
  let fixture: ComponentFixture<MyAccountComponent>;
  let compiled: DebugElement;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        MyAccountComponent,
        InvitesComponent
      ],
      imports: [
        FormsModule,
        HttpClientTestingModule,
        RouterTestingModule,
        NgMultiSelectDropDownModule.forRoot(),
        NgSwitcheryModule
      ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MyAccountComponent);
    component = fixture.componentInstance;
    compiled = fixture.nativeElement.DebugElement;

    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  //niels
  it('should contain input fields', () => {
    expect(fixture.nativeElement.querySelector('input')).toBeTruthy()
  })



});












