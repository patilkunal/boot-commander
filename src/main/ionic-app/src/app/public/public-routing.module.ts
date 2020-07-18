import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { PublicPage } from './public.page';
import { PublicGuard } from '../guards/public.guard';

const routes: Routes = [
  {
    path: '',
    component: PublicPage,
    canActivate: [PublicGuard],
    children: [
      {
        path: '',
        redirectTo: 'welcome',
        pathMatch: 'full'
      },
      {
        path: 'login',
        loadChildren: () => import('./login/login.module').then( m => m.LoginPageModule)
      },
      {
        path: 'signup',
        loadChildren: () => import('./signup/signup.module').then( m => m.SignupPageModule)
      },
      {
        path: 'welcome',
        loadChildren: () => import('./welcome/welcome.module').then( m => m.WelcomePageModule)
      }
      ]
  },
  {
    path: 'not-found',
    loadChildren: () => import('./not-found/not-found.module').then( m => m.NotFoundPageModule)
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PublicPageRoutingModule {}
