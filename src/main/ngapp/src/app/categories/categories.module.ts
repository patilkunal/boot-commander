import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { CategoriesRoutingModule } from './categories-routing.module';
import { CategoriesListComponent } from './categories-list/categories-list.component';
import { CategoryDetailComponent } from './category-detail/category-detail.component';
import { CategoriesService } from './categories.service';
import { CategoryResolve } from './category-resolve';
import { HttpClientModule } from '@angular/common/http';
import { ErrorHandlerModule } from '../shared/error-handler/error-handler.module';
import { SharedModule } from '../shared/shared.module';


@NgModule({
  // imports only accept NgModules
  imports: [
    CommonModule,
    CategoriesRoutingModule,
    FormsModule,
    HttpClientModule,
    ErrorHandlerModule,
    SharedModule
  ],
  // declaration only accept component, pipe or directive
  declarations: [
     CategoriesListComponent,
    CategoryDetailComponent
  ],
  // Export what you want to make visible outside
  // No need to export CategoriesComponent since we are providing the routing
  exports: [],
  providers: [
    CategoriesService,
    CategoryResolve
  ]
})
export class CategoriesModule { }
