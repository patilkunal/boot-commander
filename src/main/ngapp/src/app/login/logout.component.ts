import { Router } from '@angular/router';
import { TokenStorage } from '../shared/TokenStorage';
import { OnInit, Component } from '@angular/core';

@Component({
    template: '<div></div>'
})
export class LogoutComponent implements OnInit {

    constructor(private router: Router, private tokenStorage: TokenStorage) {}

    ngOnInit() {
        this.tokenStorage.logout();
        this.router.navigate(['login']);
    }
}