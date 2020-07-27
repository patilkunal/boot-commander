import { ErrorHandlerModule } from '../shared/error-handler/error-handler.module';
import { HostDetailComponent } from './detail/host-detail.component';
import { HostResolve } from './host-resolve';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { HostService } from './hosts.service';
import { HostRoutingModule } from './hosts-routing.module';
import { HostsListComponent } from './list/hosts-list.component';
import { HttpClientModule } from '@angular/common/http';
import { CategoriesResolve } from '../categories/category-resolve';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    HostRoutingModule,
    HttpClientModule,
    ErrorHandlerModule
  ],
  declarations: [
    HostsListComponent,
    HostDetailComponent
  ],
  exports: [],
  providers: [
    HostService, 
    HostResolve,
    CategoriesResolve
  ]
})
export class HostsModule { }
