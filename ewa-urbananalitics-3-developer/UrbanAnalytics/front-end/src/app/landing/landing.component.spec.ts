import { async, ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';

import { LandingComponent } from './landing.component';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

describe('LandingComponent', () => {
  let fixture: ComponentFixture<LandingComponent>;
  let component: LandingComponent;
  let debugEl: DebugElement;
  let componentHTML: HTMLElement;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [LandingComponent],
      imports: [
        RouterTestingModule,
        HttpClientTestingModule
      ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LandingComponent);
    component = fixture.componentInstance;
    debugEl = fixture.debugElement;
    componentHTML = debugEl.nativeElement;
    fixture.detectChanges();
  });

  it('should create the landing component', () => {
    expect(component).toBeTruthy();
  });

  //Evan test 1
  it('should show the public data sets <div> once clicked is set to true', () => {
    //Public data sets div
    const publicDatasetsDiv = debugEl.nativeElement.querySelector('#publicDatasets');

    //Set clicked to true
    component.clicked = true;

    //Public data sets div should be visible when clicked is true
    expect(publicDatasetsDiv.isHidden).toBeFalsy();
  });

  //Evan test 2
  it('public data set <div> should contain an H1 title with inner text "All public datasets"', () => {
    //Public data set div's h1
    const publicDatasetsh1 = debugEl.nativeElement.querySelector('#publicDatasetTitle');

    //Public data set div should have All public datasets as an <h1>
    expect(publicDatasetsh1.textContent).toContain('All public datasets');
  });

  //Evan test 3
  it('should set clicked to false when the exit button is clicked on the public data sets <div>', fakeAsync(() => {
    //Exit button
    const exitBtn = debugEl.nativeElement.querySelector('#publicDatasetsExitBtn');

    fixture.detectChanges();

    //Set clicked to true
    component.clicked = true;

    //Click the exit button
    exitBtn.click();

    tick();

    fixture.detectChanges();

    //Clicked should be false
    expect(component.clicked).toBeFalsy();
  }))
});
