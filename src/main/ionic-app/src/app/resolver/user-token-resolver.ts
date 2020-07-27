import { AuthenticationService } from '../services/authentication.service';
import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Category } from '../models/category';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class UserTokenResolver implements Resolve<any> {

    constructor(private authService: AuthenticationService) {}

    resolve() {
        console.log('UserTokenResolver called');
        return this.authService.getAuthToken();
    }

}
