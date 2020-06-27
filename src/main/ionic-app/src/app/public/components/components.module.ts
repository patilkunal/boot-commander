import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LogoComponent } from './logo/logo.component';
import { SlidesComponent } from './slides/slides.component';
import { IonicModule } from '@ionic/angular';



@NgModule({
  declarations: [LogoComponent, SlidesComponent],
  exports: [LogoComponent, SlidesComponent],
  imports: [
    CommonModule, IonicModule
  ]
})
export class ComponentsModule { }
