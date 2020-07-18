import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-secured',
  templateUrl: './secured.page.html',
  styleUrls: ['./secured.page.scss'],
})
export class SecuredPage implements OnInit {

  constructor(private router: Router, private authService: AuthenticationService) { }

  ngOnInit() {
  }

  logoutAction() {
    this.authService.logout().then(resp => {
      this.router.navigate(['']);
    });
  }

}
