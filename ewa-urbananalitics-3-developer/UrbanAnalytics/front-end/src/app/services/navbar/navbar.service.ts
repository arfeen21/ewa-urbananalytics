import { Injectable } from '@angular/core';
import { Observable, BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class NavbarService {

  //The obserable boolean that corosponds with the extension of the navbar
  public isExtended: BehaviorSubject<boolean>;

  //Constructer
  constructor() {
    //Sets inital navbar state to false - navbar is not extended on pageload 
    this.isExtended = new BehaviorSubject<boolean>(false);
  }

  //Toggles the navbar status
  toggleNavBar() {
    this.isExtended.next(!this.isExtended.value)
  }

  //Closes navbar
  closeNavBar() {
    this.isExtended.next(false);
  }

  //Gets the obserable navbarextended boolean
  //    (Mainly used for the navbar component to check wheater to extend or not)
  getIsExtended(): Observable<boolean> {
    return this.isExtended.asObservable();
  }
}
