import { Component, OnInit, ViewChild } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthService } from '../auth/auth.service';
import { TokenStorage } from '../shared/TokenStorage';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ModalDialogComponent } from '../shared/dialog/modal-dialog.component';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AlertService } from '../common/alert/alert.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  userName: string;
  password: string;
  loginForm: FormGroup;
  submitted = false;
  authFailed = false;

  @ViewChild('modalprompt') modalPrompt: ModalDialogComponent ;
  
  constructor(
    private router: Router, 
    private authService: AuthService, 
    private tokenStorage: TokenStorage,
    private actRoute: ActivatedRoute,
    private formBuilder: FormBuilder,
    private alertService: AlertService
    ) { }

  ngOnInit() {
    this.authFailed = false;
    this.loginForm = this.formBuilder.group({
      userName: ['', Validators.required],
      password: ['', Validators.required]
    });
    const isLogout: boolean = (this.actRoute.snapshot.queryParams['logout'] != null);
    if (isLogout) {
      console.log('Logging out User');
      this.tokenStorage.logout();
    } else if (this.tokenStorage.getAuthToken() != null) {
      // TODO: validate existing token by making HTTP call
      // redirect to home page since we already have valid token
      console.log('Already authenticated ... redirecting to home');
      this.router.navigate(['home']);
    }
  }

   // convenience getter for easy access to form fields
   get f() { return this.loginForm.controls; }

  login(): void {
    this.submitted = true;
    if (this.loginForm.valid) {
      this.authService.attemptAuth(this.loginForm.get('userName').value, this.loginForm.get('password').value).subscribe(
        (data: HttpResponse<any>) => {
          const token = data.headers.get('Authorization');
          console.log('Got Auth token as ' + token);
          if (token != null) {
            this.tokenStorage.setAuthToken(token);
            this.router.navigate(['home']);
          } else {
            console.log('Login comp: auth failed');
            this.alertService.error('Invalid username and password combination');
            this.authFailed = true;
            // this.modalPrompt.open('Authentication Error', 'Unable to get authentication data from server', (result) => {});
          }
        }, (err) => {
          console.log('Login comp: auth error: ' + JSON.stringify(err));
          this.authFailed = true;
          if (err instanceof HttpErrorResponse && err.status === 403) {
            this.alertService.error('Invalid username and password combination');
          }
        }
      );
    }
    /*
    if (this.username === 'admin' && this.password === 'admin') {
      this.router.navigate(['home']);
    } else {
      alert('Invalid username and password');
    }
    */
  }

}
