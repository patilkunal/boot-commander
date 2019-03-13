import { Injectable } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import { Router } from '@angular/router';
import { TokenStorage } from '../TokenStorage';


@Injectable()
export class ErrorHandlerService {

    constructor(private router: Router, private tokenStorage: TokenStorage) {}

    public handleHttpError(err: HttpErrorResponse) {
        if (err.error instanceof Error) {
            // A client-side or network error occurred. Handle it accordingly.
            console.log('An error occurred:', err.error.message);
        } else {
            // The backend returned an unsuccessful response code.
            // The response body may contain clues as to what went wrong,
            console.log(`Backend returned code ${err.status}, body was: ${err.error}`);
            
            if (err.status === 403 || err.status === 401) {
                this.tokenStorage.logout();
                this.router.navigate(['login']);
            }
            
        }
    }

    public consoleLog(err: HttpErrorResponse) {
        console.log(err);
        // return Observable.throw(err.message || "Server Error");
        return throwError(err.message || 'Server Error');
    }
}
