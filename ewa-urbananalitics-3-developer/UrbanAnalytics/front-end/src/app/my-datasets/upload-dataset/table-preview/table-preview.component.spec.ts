import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TablePreviewComponent } from './table-preview.component';
import { HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('TablePreviewComponent', () => {
  let component: TablePreviewComponent;
  let fixture: ComponentFixture<TablePreviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [TablePreviewComponent],
      imports: [
        HttpClientTestingModule,
        RouterTestingModule
      ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TablePreviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
