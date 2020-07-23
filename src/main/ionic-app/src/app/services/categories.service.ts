import { Injectable } from '@angular/core';
import { HttpService } from './http.service';
import { Observable } from 'rxjs';
import { Category } from '../models/category';
import { identifierModuleUrl } from '@angular/compiler';

@Injectable({
    providedIn: 'root'
})
export class CategoriesService {

    constructor(private httpService: HttpService) {
        
    }

    getCategories(): Observable<Category[]> {
        return this.httpService.get('/categories');
    }

    getCategory(id: number): Observable<Category> {
        return this.httpService.get('/categories/' + id);
    }

    saveCategory(category: Category): Observable<Category> {
        if(category.id > -1) {
            return this.httpService.put('/categories' + category.id, category );
        } else {
            return this.httpService.post('/categories', category );
        }
    }

    deleteCategory(id: number)  {
        this.httpService.delete('/categories' + id);
    }
}