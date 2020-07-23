import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { SecuredPage } from './secured.page';
import { SecuredGuard } from '../guards/secured.guard';
import { UserTokenResolver } from '../resolver/user-token-resolver';

const routes: Routes = [
  {
    path: 'secured',
    component: SecuredPage,
    canActivate: [SecuredGuard],
    resolve: {
      // ensures user token is read ahead of time and cached to prevent re-read
      // access AuthenticationService -> userToken$ behaviour to access token
      userToken: UserTokenResolver
    },
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
    path: 'categories',
    loadChildren: () => import('./categories/categories.module').then( m => m.CategoriesPageModule)
  }
  // , 
  // {
  //   path: '',
  //   pathMatch: 'full',
  //   redirectTo: 'overview'
  // }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class SecuredPageRoutingModule {}
