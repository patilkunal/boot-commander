import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login.component';
import { SharedModule } from '../shared/shared.module';
import { ModalDialogModule } from '../shared/dialog/modal-dialog.module';
import { ModalDialogContent } from '../shared/dialog/modal-dialog.component';
import { AuthModule } from '../auth/auth.module';

const routes: Routes = [
  { path: 'login', component: LoginComponent }
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    FormsModule,
    SharedModule,
    ModalDialogModule,
    AuthModule
  ],
  declarations: [LoginComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
entryComponents: [ModalDialogContent]
})
export class LoginModule { }
