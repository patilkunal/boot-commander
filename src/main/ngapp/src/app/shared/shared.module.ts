import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { ErrorHandlerModule } from './error-handler/error-handler.module';
// import { TokenStorage } from './TokenStorage';
import { ModalDialogModule } from './dialog/modal-dialog.module';

@NgModule({
  imports: [
    CommonModule,
    HttpClientModule,
    ErrorHandlerModule,
    ModalDialogModule
  ],
  declarations: [],
  providers: [
    // TokenStorage
  ]
})
export class SharedModule { }
