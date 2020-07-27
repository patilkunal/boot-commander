import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { StorageService } from '../services/storage.service';
import { AuthConstants } from '../constants/auth-constants';

@Injectable({
  providedIn: 'root'
})
export class PublicGuard implements CanActivate {

  constructor(private storageService: StorageService, private router: Router ) {}

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

    return new Promise(resolve => {
      // do not activate public page (return false) if user is already authenticated, redirect to secured pages      
      this.storageService.get(AuthConstants.TOKEN).then( val => {
        if(val) {
          resolve(false);
          this.router.navigate(['secured']);
        } else {
          //no auth token found, so keep user in public area
          resolve(true);
        }
      }).catch(err => {
        resolve(false);
      })
    });

  }
  
}
