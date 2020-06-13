import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable, of } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  authURL = environment.baseAPI_URL + '/login';

  constructor(private http: HttpClient) { }

  attemptAuth(p_username: string, p_password: string): Observable<HttpResponse<any>> {
    const creds = {userName: p_username, password: p_password};
    console.log('Attempting Authentication');
    // observe returns the complete RAW response
    return this.http.post<HttpResponse<string>>(this.authURL, creds, {observe: 'response'});
  }
}
