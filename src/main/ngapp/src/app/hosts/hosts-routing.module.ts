import { HostDetailComponent } from './detail/host-detail.component';
import { HostResolve } from './host-resolve';
import { HostsListComponent } from './list/hosts-list.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from '../auth/auth.guard';
import { CategoriesResolve } from '../categories/category-resolve';


const routes: Routes = [
    {path: 'hosts', canActivate: [AuthGuard],
        children: [
            {
              path: '',
              redirectTo: 'list',
              pathMatch: 'full'
            },
            {
              path: 'list',
              component: HostsListComponent
            },
            {
                path: ':id',
                component: HostDetailComponent,
                resolve: {
                    host: HostResolve,
                    categories: CategoriesResolve
                }
            }
        ]
    }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HostRoutingModule { }
