import { Injectable } from '@angular/core';
import { HttpService } from './http.service';
import { StorageService } from './storage.service';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthConstants } from '../constants/auth-constants';
import { HttpResponse } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private httpService: HttpService, 
        private storageService: StorageService, 
        private router: Router) { }

  login(postData: any): Observable<HttpResponse<any>> {
    return this.httpService.postWithResponse('/login', postData, {observe: 'response'});
  }

  signup(postData: any): Observable<any> {
    return this.httpService.post('/signup', postData);
  }

  logout() {
    this.storageService.clear().then(resp => {
        this.router.navigate(['']);
    });
  }

}
