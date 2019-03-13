import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { routerNgProbeToken } from '@angular/router/src/router_module';
import { AuthService } from './auth.service';
import { TokenStorage } from '../shared/TokenStorage';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { of } from 'rxjs';
import { ModalDialogComponent } from '../shared/dialog/modal-dialog.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  username: string;
  password: string;

  @ViewChild('modalprompt') modalPrompt: ModalDialogComponent ;
  
  constructor(private router: Router, private authService: AuthService, private tokenStorage: TokenStorage) { }

  ngOnInit() {
    if (this.tokenStorage.getAuthToken() != null) {
      // TODO: validate existing token by making HTTP call
      // redirect to home page since we already have valid token
      console.log('Already authenticated ... redirecting to home');
      this.router.navigate(['home']);
    }
  }

  login(): void {
    this.authService.attemptAuth(this.username, this.password).subscribe(
      (data: HttpResponse<any>) => {
        const token = data.headers.get('Authorization');
        console.log('Got Auth token as ' + token);
        if (token != null) {
          this.tokenStorage.setAuthToken(token);
          this.router.navigate(['home']);
        } else {
          this.modalPrompt.open('Authentication Error', 'Invalid combination of username and password', (result) => {});
        }
      }
    );
    /*
    if (this.username === 'admin' && this.password === 'admin') {
      this.router.navigate(['home']);
    } else {
      alert('Invalid username and password');
    }
    */
  }

}
