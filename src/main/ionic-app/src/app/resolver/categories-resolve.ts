import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot } from '@angular/router';
import { Category } from '../models/category';
import { CategoriesService } from '../services/categories.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CategoryResolve implements Resolve<Category> {

    constructor(private categoryService: CategoriesService) {}

    resolve(route: ActivatedRouteSnapshot): Category | Observable<Category> {
        const id: number = parseInt(route.paramMap.get('id'), 10);
        console.log("Resolving for category for id: " + id);
        if(id > -1) {
          return this.categoryService.getCategory(id);
        } else {
          let cat = new Category();
          cat.id = -1;
          return cat;
        }
    }
}

@Injectable({
  providedIn: 'root'
})
export class CategoriesListResolve implements Resolve<Category[]> {

    constructor(private categoryService: CategoriesService) {}

    resolve() {
        return this.categoryService.getCategories();
    }
}
