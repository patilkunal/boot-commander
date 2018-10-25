import { HostDetailComponent } from './detail/host-detail.component';
import { HostResolve } from './host-resolve';
import { HostsListComponent } from './list/hosts-list.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';


const routes: Routes = [
    {path: 'hosts',
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
                    host: HostResolve
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
