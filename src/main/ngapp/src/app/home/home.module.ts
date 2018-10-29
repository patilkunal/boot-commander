import { ModalDialogContent } from '../shared/dialog/modal-dialog.component';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA  } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { CommonModule } from '@angular/common';
import { HomeComponent } from './home.component';
import { ModalDialogModule } from '../shared/dialog/modal-dialog.module';

const routes: Routes = [
     { path: 'home', component: HomeComponent }
];


@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    ModalDialogModule
  ],
  declarations: [
    HomeComponent    
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
entryComponents: [ModalDialogContent]    
})
export class HomeModule { }
