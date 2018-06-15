import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { ErrorHandlerModule } from './error-handler/error-handler.module';

@NgModule({
  imports: [
    CommonModule,
    HttpClientModule,
    ErrorHandlerModule
  ],
  declarations: []
})
export class SharedModule { }
