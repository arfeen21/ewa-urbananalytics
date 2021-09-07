import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GraphSelectionComponent } from './graph-selection.component';
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';
import { FormsModule } from '@angular/forms';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';

describe('GraphSelectionComponent', () => {
  let component: GraphSelectionComponent;
  let fixture: ComponentFixture<GraphSelectionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [GraphSelectionComponent],
      imports: [
        NgMultiSelectDropDownModule.forRoot(),
        FormsModule,
        HttpClientTestingModule,
        RouterTestingModule,
      ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GraphSelectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
