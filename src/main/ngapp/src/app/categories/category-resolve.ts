import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Category } from '../shared/models/category';
import { CategoriesService } from './categories.service';
import { Observable } from 'rxjs';
import { catchError} from 'rxjs/operators';

@Injectable()
export class CategoryResolve implements Resolve<Category> {

    constructor(private service: CategoriesService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Category | Observable<Category> | Promise<Category> {
        const id: number = parseInt(route.paramMap.get('id'), 10);
        return this.service.getCategory( id );
    }

}

@Injectable()
export class CategoriesResolve implements Resolve<Category[]> {

    constructor(private service: CategoriesService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Category[] | Observable<Category[]> | Promise<Category[]> {
        console.log("Getting categories");
        // return Observable.of('Hello Alligator!').delay(2000);
        return this.service.getCategories();
    }

}
