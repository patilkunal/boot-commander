import {HttpInterceptor, HttpRequest, HttpHandler, HttpSentEvent, HttpHeaderResponse, HttpProgressEvent,
    HttpResponse, HttpUserEvent, HttpErrorResponse, HttpEvent} from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { TokenStorage } from '../shared/TokenStorage';
import { Router } from '@angular/router';
import { Injectable } from '@angular/core';

const AUTHORIZATION_KEY = 'Authorization';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

    constructor(private tokenStorage: TokenStorage, private router: Router) {}

    intercept(req: HttpRequest<any>, nextHandler: HttpHandler):
// tslint:disable-next-line: max-line-length
    Observable<HttpEvent<any>> {
        let authReq = req;
        if (this.tokenStorage.getAuthToken() != null) {
            authReq = req.clone({
                headers: req.headers.set(AUTHORIZATION_KEY, this.tokenStorage.getAuthToken()).set('Accept', 'application/json')
            });
        }

        // continue handling the HTTP request 
        return nextHandler.handle(authReq) ;
        /*
        .pipe(
            catchError( (err: HttpErrorResponse) => {
                if (err.status === 200) {
                    const res = new HttpResponse<any>({
                        body: null,
                        headers: err.headers,
                        status: err.status,
                        statusText: err.statusText,
                        url: err.url
                    });
                    return of(res);
                }
            }));
        
        rethandler.subscribe((event: HttpEvent<any>) => {}, (err: any) => {
            if (err instanceof HttpErrorResponse) {
                // if we get 401, redirect to login screen 
                if (err.status === 401) {
                    this.router.navigate(['login']);
                }
            }
        });
        */
        // return rethandler;
    }
    
}