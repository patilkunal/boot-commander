import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { CategoriesListComponent } from './categories-list/categories-list.component';
import { CategoryDetailComponent } from './category-detail/category-detail.component';
import { CategoryResolve } from './category-resolve';

const routes: Routes = [
    {path: 'categories',
        children: [
            {
              path: '',
              redirectTo: 'list',
              pathMatch: 'full'
            },
            {
              path: 'list',
              component: CategoriesListComponent
            },
            {
                path: ':id',
                component: CategoryDetailComponent,
                resolve: {
                    category: CategoryResolve
                }
            }
        ]
    }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CategoriesRoutingModule { }

