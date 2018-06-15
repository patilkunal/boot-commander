import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';

import { ErrorHandlerComponent } from './error-handler.component';
import { ErrorHandlerService } from './error-handler.service';

@NgModule({
    imports: [
      BrowserModule,
      FormsModule
    ],
    declarations: [
      ErrorHandlerComponent
    ],
    providers: [
      ErrorHandlerService
    ]
})
export class ErrorHandlerModule {

}
