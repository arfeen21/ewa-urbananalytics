import { Directive, Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Location, getLocaleWeekEndRange } from '@angular/common';
import { AuthService } from 'src/app/services/auth/auth.service';

@Injectable()
export class Authentication implements CanActivate {

    constructor(private authService: AuthService, private router: Router, private location: Location) { }

    canActivate() {
        this.authService.isLoggedIn()
        if (sessionStorage.getItem("jwtToken")) {
            this.router.navigate(["/dashboard"]);
            return false;
        }
        else {
            return true;
        }
    }
}
