import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import {ModalDialogComponent, ModalDialogContent} from './modal-dialog.component';

@NgModule({
    imports: [
      BrowserModule
    ],
    exports: [
      ModalDialogComponent  
    ],
    declarations: [
      ModalDialogComponent,
      ModalDialogContent
    ]
})
export class ModalDialogModule {

}

