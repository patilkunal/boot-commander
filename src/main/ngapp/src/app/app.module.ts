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
import { TokenInterceptor } from './auth/token-interceptor';
import { TokenStorage } from './shared/TokenStorage';
import { AuthModule } from './auth/auth.module';
import { AuthGuard } from './auth/auth.guard';
import { HttpErrorInterceptor } from './common/http-error.interceptor';
import { AlertService } from './common/alert/alert.service';
import { AlertComponent } from './common/alert/alert.component';

@NgModule({
  declarations: [
    AppComponent,
    NavComponent,
    AlertComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule.forRoot(),
    SharedModule,
    HomeModule,
    LoginModule,
    CategoriesModule,
    HostsModule,
    AuthModule
  ],
  providers: [
    AuthGuard, TokenStorage, AlertService,
    {
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptor,
    multi: true
  },     {
    provide: HTTP_INTERCEPTORS,
    useClass: HttpErrorInterceptor,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
