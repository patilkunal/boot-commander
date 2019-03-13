import { Component, OnInit } from '@angular/core';
import { TokenStorage } from '../shared/TokenStorage';

@Component({
  selector: 'app-commander-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {

  isNavbarCollapsed = true;
  constructor(private tokenStorage: TokenStorage) { }

  ngOnInit() {
  }

  isLoggedIn(): boolean {
    return (this.tokenStorage.getAuthToken() != null);
  }

}
