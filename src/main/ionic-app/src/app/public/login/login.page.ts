import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { StorageService } from 'src/app/services/storage.service';
import { AuthConstants } from 'src/app/constants/auth-constants';
import { User } from 'src/app/models/user';
import { HttpErrorResponse } from '@angular/common/http';
import { throwError } from 'rxjs';


@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage implements OnInit {

  loginForm: FormGroup;
  isSubmitted = false;
  loginError = null;

  constructor(private router: Router,
    private formBuilder: FormBuilder,
    private authService: AuthenticationService,
    private storageService: StorageService) { }

  ngOnInit() {
    this.loginError = null;
    this.loginForm = this.formBuilder.group({
      username: ['', [Validators.required]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
    // phone: Validators.pattern('^[0-9]+$')
    // email: Validators.pattern('[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$')
  }

  get errorControl() {
    return this.loginForm.controls;
  }

  loginAction() {
    this.isSubmitted = true;
    if (!this.loginForm.valid) {
      console.log('Please provide all the required values!')
      return false;
    } else {
      console.log('Going to login using: ' + JSON.stringify(this.loginForm.value));
      this.authService.login(this.loginForm.value).subscribe((resp) => {
        if (resp.status === 200) {
          this.storageService.store(AuthConstants.USER_DATA, resp);
          this.storageService.store(AuthConstants.TOKEN, resp.headers.get(AuthConstants.TOKEN_HEADER_NAME));
          this.router.navigate(['secured']);
        } else if(resp.status === 403) {
            this.loginError = "Invalid username and password";
        } else {
          this.loginError = "Error authenticating username";
        }
      }, (error: any) => {
        this.handleError(error);
      });
    }
  }

  navigateTo(page) {
    this.router.navigate([page]);
  }

  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      this.loginError = error.error.message;
      console.error('An error occurred:', error.error.message);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong.
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
        if(error.status === 0) {
          this.loginError = "Unable to connect to server to authenticate. Please try later.";
        } else {
          this.loginError = `Server error [code: ${error.status}, message: ${error.statusText}]`;
        }
    }
  }

}
