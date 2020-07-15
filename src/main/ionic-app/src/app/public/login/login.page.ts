import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';


@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage implements OnInit {

   loginForm: FormGroup;
   isSubmitted = false;

  constructor(private router: Router, private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      username: ['', [Validators.required] ], 
      password: ['', [Validators.required, Validators.minLength(6)] ] 
    });
    // phone: Validators.pattern('^[0-9]+$')
    // email: Validators.pattern('[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$')
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
      this.router.navigate(['secured']);
    }
  }

  navigateTo(page) {
    this.router.navigate([page]);
  }
}
