import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpErrorResponse, HttpResponse} from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { throwError, Observable } from 'rxjs';
import { catchError } from 'rxjs/operators'; 
import { ToastController } from '@ionic/angular';
import { ThrowStmt } from '@angular/compiler';

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient, private toastController: ToastController) {
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
    this.showMessage(message);
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

    //const headers = new HttpHeaders();

    const url = environment.baseURL + uri;

    return this.http.post(url, JSON.stringify(data), this.httpOptions)
          .pipe(catchError(this.handleError));

  }

  put(uri: string, data: any): Observable<any> {

    // const headers = new HttpHeaders();
    // const options = {header: headers, withCredentials: false};

    const url = environment.baseURL + uri;

    return this.http.put(url, JSON.stringify(data), this.httpOptions)
          .pipe(catchError(this.handleError));

  }

  get(uri: string): Observable<any> {
    let url = environment.baseURL + uri;
    return this.http.get(url)
        .pipe(catchError(this.handleError));
  }

  delete(uri: string): Observable<any> {
    let url = environment.baseURL + uri;
    return this.http.delete(url, this.httpOptions)
      .pipe(catchError(this.handleError));
  }

  async showMessage(msg: string) {
    const toast = await this.toastController.create({
      header: 'Error',
      message: msg,
      position: 'top',
      buttons: [
        {
          text: 'Ok',
          role: 'cancel'
        }
      ]
    });
    toast.present();
  }

}
