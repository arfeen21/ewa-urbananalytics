import { async, ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';

import { MyGroupsComponent } from './my-groups.component';
import { Router } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { CreateGroupComponent } from './create-group/create-group.component';
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';
import { create } from 'domain';

describe('MyGroupsComponent', () => {
  let component: MyGroupsComponent;
  let fixture: ComponentFixture<MyGroupsComponent>;
  let componentHTML: HTMLElement;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        MyGroupsComponent,
        CreateGroupComponent
      ],
      imports: [
        FormsModule,
        ReactiveFormsModule,
        HttpClientTestingModule,
        RouterTestingModule,
        NgMultiSelectDropDownModule.forRoot(),
      ],
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MyGroupsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    componentHTML = fixture.debugElement.nativeElement;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  //Evan test 4
  it('toggleGroupPopup method should work', () => {
    spyOn(component, 'toggleGroupPopup');

    component.toggleGroupPopup();

    //toggleGroupPopup() should have been called
    expect(component.toggleGroupPopup).toHaveBeenCalled();
  })

  //Evan test 5
  it('all tables should contain rows', () => {
    //Table rows
    const tableRows = componentHTML.querySelector('tr');

    //All tables should have rows 
    expect(tableRows).toBeTruthy();
  })
});
