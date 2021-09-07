import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddMembersComponent } from './add-members.component';
import { FormsModule } from '@angular/forms';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';

describe('AddMembersComponent', () => {
  let component: AddMembersComponent;
  let fixture: ComponentFixture<AddMembersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [AddMembersComponent],
      imports: [
        FormsModule,
        HttpClientTestingModule,
        RouterTestingModule,
        NgMultiSelectDropDownModule.forRoot(),
      ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddMembersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
