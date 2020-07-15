import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-secured',
  templateUrl: './secured.page.html',
  styleUrls: ['./secured.page.scss'],
})
export class SecuredPage implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
  }

  logoutAction() {
    this.router.navigate(['/login']);
  }

}
