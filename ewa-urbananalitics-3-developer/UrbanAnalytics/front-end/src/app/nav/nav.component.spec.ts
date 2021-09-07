import { async, ComponentFixture, TestBed, inject } from '@angular/core/testing';

import { NavComponent } from './nav.component';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { NavbarService } from '../services/navbar/navbar.service';

describe('NavComponent', () => {
  let component: NavComponent;
  let fixture: ComponentFixture<NavComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [NavComponent],
      imports: [
        HttpClientTestingModule,
        RouterTestingModule
      ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NavComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  /**
   * Danny test 1
   */
  it("Service should be true", inject([NavbarService], (service: NavbarService) => {
    expect(service).toBeTruthy();
  }));

  it("Nav should be closed when it's closed", inject([NavbarService], (service: NavbarService) => {
    service.closeNavBar();
    let val: boolean;
    service.getIsExtended().subscribe(val => val);
    expect(val).toBeFalsy();
  }));

  it("Nav should toggle when it gets toggled", inject([NavbarService], (service: NavbarService) => {
    service.closeNavBar();
    service.toggleNavBar();

    let val: boolean;
    service.getIsExtended().subscribe(val => val);
    expect(val).toBeFalsy();
  }));

  it("Navbarservice should let the component know the navbarstate", inject([NavbarService], (service: NavbarService) => {
    service.closeNavBar();

    expect(component.navExtended).toBeFalsy();

  }));


});
