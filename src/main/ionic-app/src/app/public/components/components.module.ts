import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LogoComponent } from './logo/logo.component';
import { SlidesComponent } from './slides/slides.component';
import { IonicModule } from '@ionic/angular';
import { StartComponent } from './start/start.component';
import { PrivacyComponent } from './privacy/privacy.component';



@NgModule({
  declarations: [LogoComponent, SlidesComponent, StartComponent, PrivacyComponent],
  exports: [LogoComponent, SlidesComponent, StartComponent, PrivacyComponent],
  imports: [
    CommonModule, IonicModule
  ]
})
export class ComponentsModule { }
