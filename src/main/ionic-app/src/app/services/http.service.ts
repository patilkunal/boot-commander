import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpErrorResponse, HttpResponse} from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { throwError, Observable } from 'rxjs';
import { catchError } from 'rxjs/operators'; 

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  options = { headers: null };

  constructor(private http: HttpClient) {
    this.options.headers = new HttpHeaders();
   }

  private handleError(error: HttpErrorResponse) {
    let message = '';
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      message = error.error.message;
      console.error('An error occurred:', error.error.message);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong.
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
      message = `Server error [code: ${error.status}, message: ${error.statusText}]`;
    }
    // Return an observable with a user-facing error message.
    return throwError(
      'Error occured in HTTP request: ' + message);
  }

  postWithResponse(uri: string, data: any, optionsParam?: any): Observable<HttpResponse<any>> {

    const headers = new HttpHeaders();

    const url = environment.baseURL + uri;

    return this.http.post(url, JSON.stringify(data), {withCredentials: false, observe: 'response'});

  }

  post(uri: string, data: any): Observable<any> {

    const headers = new HttpHeaders();

    const url = environment.baseURL + uri;

    return this.http.post(url, JSON.stringify(data), this.options)
          .pipe(catchError(this.handleError));

  }

  put(uri: string, data: any): Observable<any> {

    const headers = new HttpHeaders();
    const options = {header: headers, withCredentials: false};

    const url = environment.baseURL + uri;

    return this.http.put(url, JSON.stringify(data), options)
          .pipe(catchError(this.handleError));

  }

  get(uri: string): Observable<any> {
    let url = environment.baseURL + uri;
    return this.http.get(url)
        .pipe(catchError(this.handleError));
  }

  delete(uri: string): Observable<any> {
    let url = environment.baseURL + uri;
    return this.http.delete(url, this.options)
      .pipe(catchError(this.handleError));
  }

}
