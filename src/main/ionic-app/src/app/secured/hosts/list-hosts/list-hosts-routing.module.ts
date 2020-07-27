import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ListHostsPage } from './list-hosts.page';
import { HostResolve, HostListResolve } from 'src/app/resolver/host-resolve';

const routes: Routes = [
  {
    path: '',
    resolve: {
      hosts: HostListResolve
    },
    runGuardsAndResolvers: 'always',
    component: ListHostsPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ListHostsPageRoutingModule {}
