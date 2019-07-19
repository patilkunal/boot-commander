import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { AlertService } from './alert/alert.service';
import { Injectable } from '@angular/core';

@Injectable()
export class HttpErrorInterceptor implements HttpInterceptor {

    constructor(private alertService: AlertService)  {}

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return next.handle(req)
            .pipe(
                retry(1),
                catchError((_error: HttpErrorResponse) => {
                    let errorMessage = '';
                    if (_error.error instanceof ErrorEvent) {
                        // client side error
                        errorMessage = `Error: ${_error.error.message}`;
                    } else {
                        // server side error
                        errorMessage = `Error Code: ${_error.status}\nMessage: ${_error.message}`;
                    }
                    // window.alert(errorMessage);
                    if ( ! [200, 403].includes(_error.status) ) {
                        // do not show alert on UI for some HTTP codes, let individual component handle
                        this.alertService.error(errorMessage, false);
                    }
                    return throwError(_error);
                })
            );
    }
    
}
