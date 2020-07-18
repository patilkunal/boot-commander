import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { SecuredPage } from './secured.page';
import { SecuredGuard } from '../guards/secured.guard';

const routes: Routes = [
  {
    path: '',
    component: SecuredPage,
    canActivate: [SecuredGuard],
    children: [     
      {
        path: 'hosts',
        loadChildren: () => import('./hosts/hosts.module').then( m => m.HostsPageModule)
      },
      {
        path: 'overview',
        loadChildren: () => import('./overview/overview.module').then( m => m.OverviewPageModule)
      },
      {
        path: '',
        pathMatch: 'full',
        redirectTo: 'overview'
      }
    ]
  }, 
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'overview'
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class SecuredPageRoutingModule {}
