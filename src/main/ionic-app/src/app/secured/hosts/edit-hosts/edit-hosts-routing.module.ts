import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { EditHostsPage } from './edit-hosts.page';
import { HostResolve } from 'src/app/resolver/host-resolve';
import { CategoriesListResolve } from 'src/app/resolver/categories-resolve';

const routes: Routes = [
  {
    path: '',
    component: EditHostsPage,
    resolve: {
      host: HostResolve,
      categories: CategoriesListResolve
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class EditHostsPageRoutingModule {}
