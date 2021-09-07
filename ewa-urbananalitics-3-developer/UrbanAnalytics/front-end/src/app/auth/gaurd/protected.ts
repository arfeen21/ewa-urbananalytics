import { Directive, Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Location, getLocaleWeekEndRange } from '@angular/common';
import { AuthService } from 'src/app/services/auth/auth.service';

@Injectable()
export class Protected implements CanActivate {

  constructor(private authService: AuthService, private router: Router, private location: Location) { }

  canActivate() {
    this.authService.isLoggedIn()
    return sessionStorage.getItem("jwtToken") ? true : false;
  }
}
