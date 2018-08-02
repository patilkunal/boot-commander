import { ErrorHandlerService } from '../shared/error-handler/error-handler.service';
import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Category } from '../shared/models/category';

import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable()
export class CategoriesService {
    // public API = 'http://localhost:8080/api';

    // private URL = `${this.API}/categories`;
    private URL = environment.baseAPI_URL + '/categories';

    constructor(private httpClient: HttpClient, private errorHandler: ErrorHandlerService) {

    }

    getCategories(): Observable<Category[]> {
        return this.httpClient.get<Category[]>(this.URL);
                    // .map( (resp: Response) => resp.json());

    }

    getCategory(id: number): Observable<Category> {
        return this.httpClient.get<Category>(`${this.URL}/${id}`);
    }

    saveCategory(cat: Category): Observable<Category> {
        if (cat.id > 0) {
            return this.httpClient.put<Category>(`${this.URL}/${cat.id}`, cat);
        } else {
            return this.httpClient.post<Category>(this.URL, cat);
        }
    }

    deleteCategory(id: number): Promise<any> {
        return this.httpClient.delete(`${this.URL}/${id}`).toPromise();
    }

}
