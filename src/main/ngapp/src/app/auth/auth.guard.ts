import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { TokenStorage } from '../shared/TokenStorage';
import { Injectable } from '@angular/core';

@Injectable()
export class AuthGuard implements CanActivate {

    constructor(private router: Router, private tokenStorage: TokenStorage) { }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        console.log('Checking if can activate the route');
        if (this.tokenStorage.getAuthToken() != null) {
            // logged in so return true
            console.log('Allowed to switch to route');
            return true;
        }

        console.log('Not allowed to switch to route. Redirecting to login screen');
        // not logged in so redirect to login page with the return url
        this.router.navigate(['login']);
        return false;
    }
}