import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { StorageService } from '../services/storage.service';
import { AuthConstants } from '../constants/auth-constants';

@Injectable({
  providedIn: 'root'
})
export class SecuredGuard implements CanActivate {

  constructor(private storageService: StorageService, private router: Router ) {}

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    
      return new Promise(resolve => {      
        // if we have TOKEN means user is still authenticated else redirect to login
        this.storageService.get(AuthConstants.TOKEN).then( val => {
          if(val) {
            resolve(true);
          } else {
            resolve(false);
            this.router.navigate(['/login']);
          }
        }).catch(err => {
          resolve(false);
        })
      });
  
  }
  
}
