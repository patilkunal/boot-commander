import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ListHostsPage } from './list-hosts.page';

const routes: Routes = [
  {
    path: '',
    component: ListHostsPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ListHostsPageRoutingModule {}
