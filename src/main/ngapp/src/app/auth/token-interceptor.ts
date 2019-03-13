import {HttpInterceptor, HttpRequest, HttpHandler, HttpSentEvent, HttpHeaderResponse, HttpProgressEvent,
    HttpResponse, HttpUserEvent, HttpErrorResponse, HttpEvent} from '@angular/common/http';
import { Observable, of, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { TokenStorage } from '../shared/TokenStorage';
import { Router } from '@angular/router';
import { Injectable } from '@angular/core';
import { ErrorHandlerService } from '../shared/error-handler/error-handler.service';

const AUTHORIZATION_KEY = 'Authorization';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

    constructor(private tokenStorage: TokenStorage, private router: Router, private errorHandler: ErrorHandlerService) {}

    intercept(req: HttpRequest<any>, nextHandler: HttpHandler):
// tslint:disable-next-line: max-line-length
    Observable<HttpEvent<any>> {
        let authReq = req;
        console.log('TokenInterceptor: HTTP Call Intercepted');
        if (this.tokenStorage.getAuthToken() != null) {
            console.log('TokenInterceptor: Authentication token found');
            authReq = req.clone({
                headers: req.headers.set(AUTHORIZATION_KEY, this.tokenStorage.getAuthToken()).set('Accept', 'application/json')
            });
        } else {
            console.log('TokenInterceptor: No authentication token found');
        }

        // continue handling the HTTP request 
        return nextHandler.handle(authReq);
        /*
        .pipe(
            catchError( (err) => { this.errorHandler.handleHttpError(err); return throwError(err);}
            ));
        */
        /*    
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