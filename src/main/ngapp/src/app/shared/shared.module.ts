import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { ErrorHandlerModule } from './error-handler/error-handler.module';
// import { TokenStorage } from './TokenStorage';
import { ModalDialogModule } from './dialog/modal-dialog.module';
import { PaginationComponent } from './pagination/pagination.component';

@NgModule({
  imports: [
    CommonModule,
    HttpClientModule,
    ErrorHandlerModule,
    ModalDialogModule
  ],
  declarations: [PaginationComponent],
  providers: [
    // TokenStorage
  ],
  exports: [PaginationComponent]
})
export class SharedModule { }
