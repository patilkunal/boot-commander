import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LogoComponent } from './logo/logo.component';
import { SlidesComponent } from './slides/slides.component';
import { IonicModule } from '@ionic/angular';
import { StartComponent } from './start/start.component';



@NgModule({
  declarations: [LogoComponent, SlidesComponent, StartComponent],
  exports: [LogoComponent, SlidesComponent, StartComponent],
  imports: [
    CommonModule, IonicModule
  ]
})
export class ComponentsModule { }
