import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';

import { AccordionModule } from 'primeng/components/accordion/accordion';
import { PanelModule } from 'primeng/components/panel/panel';
import { ButtonModule } from 'primeng/components/button/button';
import { RadioButtonModule } from 'primeng/components/radioButton/radioButton';
import {MenubarModule} from 'primeng/menubar';

import { AppComponent } from './app.component';
import { NavComponent } from './nav/nav.component';

@NgModule({
  declarations: [
    AppComponent,
    NavComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    AccordionModule,
    PanelModule,
    ButtonModule,
    RadioButtonModule,
    MenubarModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
