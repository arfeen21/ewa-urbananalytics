import { async, ComponentFixture, TestBed, inject } from '@angular/core/testing';

import { HeaderComponent } from './header.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { NotificationMessage } from '../models/notification/notification.model';
import { UserModelComponent } from '../models/user/user.model';
import { By } from 'protractor';
import { NavbarService } from '../services/navbar/navbar.service';

describe('HeaderComponent', () => {
  let component: HeaderComponent;
  let fixture: ComponentFixture<HeaderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [HeaderComponent],
      imports: [
        HttpClientTestingModule,
        RouterTestingModule
      ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  /**
   * Danny test 6
   */
  it('should create', () => {
    expect(component).toBeTruthy();
  });

  /**
   * Danny test 7
   */
  it("NavBarService should be injected", inject([NavbarService], (service: NavbarService) => {
    expect(service).toBeTruthy();
  }));

  /**
   * Danny test 8 & 9
   */
  it("Should show recieved notificatiosn", () => {
    component.recievedNotifications = [
      new NotificationMessage("Hallo dit is een bericht!", new UserModelComponent("gebruiker A", "gebruiker B", "email", "pwd"), new UserModelComponent("hi", "hi", "email", "pwd"), true),
      new NotificationMessage("Hallo dit is een 2e bericht!", new UserModelComponent("gebruiker A", "gebruiker B", "email", "pwd"), new UserModelComponent("hi", "hi", "email", "pwd"), true),
      new NotificationMessage("Hallo dit is een 3e bericht!", new UserModelComponent("gebruiker A", "gebruiker B", "email", "pwd"), new UserModelComponent("hi", "hi", "email", "pwd"), true),
      new NotificationMessage("Hallo dit is een 4e bericht!", new UserModelComponent("gebruiker A", "gebruiker B", "email", "pwd"), new UserModelComponent("hi", "hi", "email", "pwd"), true)
    ];

    expect(component.recievedNotifications.length).toBe(4);
    component.updateNotifications();
    expect(component.amountOfNotifications).toBe(4);
  });

  /**
   * Danny test 10 & 11
   */
  it("Should show be able to remove notifications when they're marked as 'read'", () => {
    component.recievedNotifications = [
      new NotificationMessage("Hallo dit is een bericht!", new UserModelComponent("gebruiker A", "gebruiker B", "email", "pwd"), new UserModelComponent("hi", "hi", "email", "pwd"), true),
      new NotificationMessage("Hallo dit is een 2e bericht!", new UserModelComponent("gebruiker A", "gebruiker B", "email", "pwd"), new UserModelComponent("hi", "hi", "email", "pwd"), false),
      new NotificationMessage("Hallo dit is een 3e bericht!", new UserModelComponent("gebruiker A", "gebruiker B", "email", "pwd"), new UserModelComponent("hi", "hi", "email", "pwd"), true),
      new NotificationMessage("Hallo dit is een 4e bericht!", new UserModelComponent("gebruiker A", "gebruiker B", "email", "pwd"), new UserModelComponent("hi", "hi", "email", "pwd"), false)
    ];

    component.setNotificationsRead();
    component.updateNotifications();
    expect(component.recievedNotifications).toBeNull();
    expect(component.amountOfNotifications).toBe(0);
  });

  /**
   * Danny test 12 & 13
   */
  it("Should pass parameters to the navbar service", inject([NavbarService], (service: NavbarService) => {
    let capturedState = component.navExtended;
    component.toggleNav();
    expect(component.navExtended).toEqual(!capturedState);
    component.toggleNav();
    expect(component.navExtended).toEqual(capturedState);
  }));
});
