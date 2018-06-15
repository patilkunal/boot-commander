import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app.routing.module';
import { CategoriesModule } from './categories/categories.module';
import { NavComponent } from './nav/nav.component';
import { HomeModule } from './home/home.module';
import { ErrorHandlerModule } from './shared/error-handler/error-handler.module';

@NgModule({
  declarations: [
    AppComponent,
    NavComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ErrorHandlerModule,
    HomeModule,
    CategoriesModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
