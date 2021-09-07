import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ErrorRouteComponent } from './error-route.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { ErrorComponent } from '../error.component';
import { DebugElement } from '@angular/core';
import { Router } from '@angular/router';



describe('ErrorRouteComponent', () => {
  let component: ErrorRouteComponent;
  let fixture: ComponentFixture<ErrorRouteComponent>;
  let componentHTML: HTMLElement;
  let de: DebugElement;
  let router: Router;
  let h1: HTMLElement;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        ErrorRouteComponent,
        ErrorComponent],
      imports: [
        HttpClientTestingModule,
        RouterTestingModule,

      ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ErrorRouteComponent);
    component = fixture.componentInstance;
    router = TestBed.get(Router);
    de = fixture.debugElement;
    fixture.detectChanges();
    h1 = fixture.nativeElement.querySelector('h1');
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  //niels
  it('should have the correct pagename', () => {
    expect(component.pagename).toEqual(router.url);
  });

  //niels
  it('should have a h1 to address the error', () => {
    expect(h1.textContent).toContain("bestaat niet");
  });



});
