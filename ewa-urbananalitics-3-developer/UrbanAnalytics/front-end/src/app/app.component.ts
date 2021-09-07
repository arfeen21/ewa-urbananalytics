import { Component, ViewChild, ElementRef } from '@angular/core';
import { RouterOutlet, Router } from '@angular/router';
import { slideInAnimation } from './animations';
import { AuthService } from './services/auth/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  animations: [
    slideInAnimation
  ]
})

export class AppComponent {
  title = 'UrbanAnalytics';
  isLoggedIn: Boolean;

  constructor(private authServicce: AuthService, private router: Router) { }

  ngOnInit(): void {
    this.authServicce.getUid().subscribe(isLoggedIn => {
      this.authServicce.isLoggedIn();

      this.isLoggedIn = isLoggedIn ? true : false;
    })


  }

  prepareRoute(outlet: RouterOutlet) {
    return outlet && outlet.activatedRouteData && outlet.activatedRouteData['animation'];
    //console.log(sessionStorage.getItem("jwtToken"));

  }
}


