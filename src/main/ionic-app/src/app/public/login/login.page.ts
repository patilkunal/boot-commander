import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { StorageService } from 'src/app/services/storage.service';
import { AuthConstants } from 'src/app/constants/auth-constants';
import { User } from 'src/app/models/user';


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
        if(error.status === 403) {
          this.loginError = "Invalid username and password";
        } else {
          this.loginError = "Server Error during authentication: " + JSON.stringify(error);
        }
        console.log(JSON.stringify(error));
      });
    }
  }

  navigateTo(page) {
    this.router.navigate([page]);
  }
}
