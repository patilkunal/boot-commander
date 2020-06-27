import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { ListHostsPageRoutingModule } from './list-hosts-routing.module';

import { ListHostsPage } from './list-hosts.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    ListHostsPageRoutingModule
  ],
  declarations: [ListHostsPage]
})
export class ListHostsPageModule {}
