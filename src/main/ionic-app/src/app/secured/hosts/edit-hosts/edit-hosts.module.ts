import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { EditHostsPageRoutingModule } from './edit-hosts-routing.module';

import { EditHostsPage } from './edit-hosts.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    IonicModule,
    EditHostsPageRoutingModule
  ],
  declarations: [EditHostsPage]
})
export class EditHostsPageModule {}
