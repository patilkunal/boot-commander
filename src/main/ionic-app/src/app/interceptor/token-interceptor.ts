import {HttpInterceptor, HttpRequest, HttpHandler, HttpSentEvent, HttpHeaderResponse, HttpProgressEvent,
    HttpResponse, HttpUserEvent, HttpErrorResponse, HttpEvent} from '@angular/common/http';
import { Observable, of, throwError } from 'rxjs';
import { Router } from '@angular/router';
import { Injectable, OnInit } from '@angular/core';
import { AuthenticationService } from '../services/authentication.service';

const AUTHORIZATION_KEY = 'Authorization';

@Injectable()
export class TokenInterceptor implements HttpInterceptor, OnInit {

    userToken: string;

    constructor(private authService: AuthenticationService, private router: Router) {}

    ngOnInit() {
        this.authService.userToken$.subscribe((resp:any) => {
            this.userToken = resp;
        })
    }

    intercept(req: HttpRequest<any>, nextHandler: HttpHandler): Observable<HttpEvent<any>> {
        let authReq = req;
        console.log('TokenInterceptor: HTTP Call Intercepted');
        if (this.userToken != null) {
            console.log('TokenInterceptor: Authentication token found');
            authReq = req.clone({
                headers: req.headers.set(AUTHORIZATION_KEY, this.userToken).set('Accept', 'application/json')
            });
        } else {
            console.log('TokenInterceptor: No authentication token found');
        }

        // continue handling the HTTP request 
        return nextHandler.handle(authReq);
    }
    
}