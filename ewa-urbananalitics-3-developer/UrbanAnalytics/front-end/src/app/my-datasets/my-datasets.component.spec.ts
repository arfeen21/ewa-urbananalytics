import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MyDatasetsComponent } from './my-datasets.component';
import { FormsModule } from '@angular/forms';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';

describe('MyDatasetsComponent', () => {
  let component: MyDatasetsComponent;
  let fixture: ComponentFixture<MyDatasetsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [MyDatasetsComponent],
      imports: [
        FormsModule,
        HttpClientTestingModule,
        RouterTestingModule,
      ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MyDatasetsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
