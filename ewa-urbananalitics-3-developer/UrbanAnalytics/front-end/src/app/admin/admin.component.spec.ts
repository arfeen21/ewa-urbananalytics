import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import {
  HttpClientTestingModule,
  HttpTestingController
} from '@angular/common/http/testing';
import { AdminComponent } from './admin.component';
import { GroupService } from '../services/group/group.service';
import { RouterModule } from '@angular/router';
import { UserService } from '../services/user/user.service';
import { group } from '@angular/animations';
import { RouterTestingModule } from '@angular/router/testing';

describe('AdminComponent', () => {
  let component: AdminComponent;
  let fixture: ComponentFixture<AdminComponent>;
  let componentHTML: HTMLElement;
  let service: GroupService;


  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [AdminComponent],
      imports: [
        HttpClientTestingModule,
        RouterTestingModule
      ],
      providers: [GroupService]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminComponent);
    component = fixture.componentInstance;
    componentHTML = fixture.nativeElement;
    fixture.detectChanges();

  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  //arfeen test1,  load all the images 
  it('should load all images', () => {
    componentHTML.querySelectorAll('img').forEach(
      (img: HTMLImageElement) => {
        expect(img).toBeTruthy();
        expect(img.complete).toBeTruthy;
      });
  });

  //arfeen test2, loads all the groups in the component and gets al the groups
  it('should get all groups', () => {
    expect(component.getGroups()).toBeTruthy;
    componentHTML.querySelectorAll('group').forEach(
      (group: HTMLAnchorElement) => {
        expect(group).toBeTruthy;
      }
    )
  });
  
//Arfeen 
  it('should have h1 where the groupname comes', () => {
    expect(fixture.nativeElement.querySelector('h1')).toBeTruthy();
  })




});
