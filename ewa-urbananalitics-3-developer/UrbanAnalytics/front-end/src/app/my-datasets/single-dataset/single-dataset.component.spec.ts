import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SingleDatasetComponent } from './single-dataset.component';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ChartComponent } from './chart/chart.component';

describe('SingleDatasetComponent', () => {
  let component: SingleDatasetComponent;
  let fixture: ComponentFixture<SingleDatasetComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        SingleDatasetComponent,
        ChartComponent
      ],
      imports: [
        HttpClientTestingModule,
        RouterTestingModule
      ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SingleDatasetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  // Jerry
  // Component should be created
  it('should create', () => {
    expect(component).toBeTruthy();
  });

  // Jerry
  // Back button should work
  it('backbutton should work', () => {
    spyOn(component, 'backClicked');

    component.backClicked();
    expect(component.backClicked).toHaveBeenCalledWith();
  });
});
