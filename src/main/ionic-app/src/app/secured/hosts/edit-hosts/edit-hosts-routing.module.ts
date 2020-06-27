import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { EditHostsPage } from './edit-hosts.page';

const routes: Routes = [
  {
    path: '',
    component: EditHostsPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class EditHostsPageRoutingModule {}
