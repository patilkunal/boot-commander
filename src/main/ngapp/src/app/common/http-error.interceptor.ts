import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';

export class HttpErrorInterceptor implements HttpInterceptor {

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
                    window.alert(errorMessage);
                    return throwError(_error);
                })
            );
    }
    
}
