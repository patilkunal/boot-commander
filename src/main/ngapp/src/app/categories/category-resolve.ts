import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Category } from '../shared/models/category';
import { CategoriesService } from './categories.service';
import { Observable } from 'rxjs';

@Injectable()
export class CategoryResolve implements Resolve<Category> {

    constructor(private service: CategoriesService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Category | Observable<Category> | Promise<Category> {
        const id: number = parseInt(route.paramMap.get('id'), 10);
        return this.service.getCategory( id );
    }

}
