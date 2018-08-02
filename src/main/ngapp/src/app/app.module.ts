import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app.routing.module';
import { CategoriesModule } from './categories/categories.module';
import { NavComponent } from './nav/nav.component';
import { HomeModule } from './home/home.module';
import { HostsModule } from './hosts/hosts.module';
import { ErrorHandlerModule } from './shared/error-handler/error-handler.module';

@NgModule({
  declarations: [
    AppComponent,
    NavComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule.forRoot(),
    ErrorHandlerModule,
    HomeModule,
    CategoriesModule,
    HostsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
