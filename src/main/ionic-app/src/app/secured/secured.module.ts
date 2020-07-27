import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { SecuredPageRoutingModule } from './secured-routing.module';

import { SecuredPage } from './secured.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    SecuredPageRoutingModule
  ],
  declarations: [SecuredPage]
})
export class SecuredPageModule {}
