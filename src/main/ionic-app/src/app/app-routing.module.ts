import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    loadChildren: () => import('./public/public.module').then( m => m.PublicPageModule)
  }
  ,
  {
    path: 'public',
    pathMatch: 'full',
    loadChildren: () => import('./public/public.module').then( m => m.PublicPageModule)
  }
  ,
  {
    path: 'secured',
    pathMatch: 'full',
    loadChildren: () => import('./secured/secured.module').then( m => m.SecuredPageModule)
  },
  {
    path: '**',
    loadChildren: () => import('./public/not-found/not-found.module').then( m => m.NotFoundPageModule)
  }

  // ,
  // {
  //   path: '',
  //   pathMatch: 'full',
  //   redirectTo: 'public'
  // }
];
@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules, onSameUrlNavigation: 'reload' })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {}
