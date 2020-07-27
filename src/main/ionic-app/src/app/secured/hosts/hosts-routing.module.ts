import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { HostsPage } from './hosts.page';

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'list'
  },
  {
    path: 'list',
    loadChildren: () => import('./list-hosts/list-hosts.module').then( m => m.ListHostsPageModule)
  },
  {
    path: 'edit/:id',
    loadChildren: () => import('./edit-hosts/edit-hosts.module').then( m => m.EditHostsPageModule)
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class HostsPageRoutingModule {} 
