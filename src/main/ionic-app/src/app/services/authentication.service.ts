import { Injectable } from '@angular/core';
import { HttpService } from './http.service';
import { StorageService } from './storage.service';
import { Router } from '@angular/router';
import { Observable, BehaviorSubject } from 'rxjs';
import { AuthConstants } from '../constants/auth-constants';
import { HttpResponse } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  userToken$ = new BehaviorSubject<any>('');

  constructor(private httpService: HttpService, 
        private storageService: StorageService, 
        private router: Router) {
          console.log("**** AuthenticationService constructor")
          this.getAuthToken();
  }

  login(postData: any): Observable<HttpResponse<any>> {
    return this.httpService.postWithResponse('/login', postData, {observe: 'response'});
  }

  signup(postData: any): Observable<any> {
    return this.httpService.post('/signup', postData);
  }

  logout(): Promise<void> {
    //clear token and all storage data
    return this.storageService.clear().then(() => {
        this.userToken$.next(''); //clear out from behaviour too
    });
  }

  getAuthToken() {
    this.storageService.get(AuthConstants.TOKEN).then(resp => {
      //console.log('auth service getAuthToken: ' + resp);
      this.userToken$.next(resp);
    })
  }

}
