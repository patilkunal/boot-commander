import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app.routing.module';
import { CategoriesModule } from './categories/categories.module';
import { NavComponent } from './nav/nav.component';
import { HomeModule } from './home/home.module';
import { LoginModule } from './login/login.module';
import { HostsModule } from './hosts/hosts.module';
import { SharedModule } from './shared/shared.module';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { TokenInterceptor } from './login/token-interceptor';
import { TokenStorage } from './shared/TokenStorage';

@NgModule({
  declarations: [
    AppComponent,
    NavComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule.forRoot(),
    SharedModule,
    HomeModule,
    LoginModule,
    CategoriesModule,
    HostsModule
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptor,
    multi: true
  }, TokenStorage],
  bootstrap: [AppComponent]
})
export class AppModule { }
