import { ErrorHandlerService } from '../../shared/error-handler/error-handler.service';
import { Category } from '../../shared/models/category';
import { CategoriesService } from '../categories.service';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-categories-list',
  templateUrl: './categories-list.component.html',
  styleUrls: ['./categories-list.component.css']
})
export class CategoriesListComponent implements OnInit, OnDestroy {

  private categories: Category[];
  private categoriesSub: Subscription;

  constructor(
    private service: CategoriesService,
    private errorHandler: ErrorHandlerService) { }

  ngOnInit() {
    this.categoriesSub = this.service.getCategories()
              .subscribe((data) => { this.categories = data; },
              (err) => this.errorHandler.handleHttpError(err),
              () => console.log('Categories get complete')
            );
  }

  ngOnDestroy() {
    this.categoriesSub.unsubscribe();
  }

  deleteCategory(id: number) {
    this.service.deleteCategory(id).then(
        (data) => {
          console.log('Delete success');
        },
        (err) => {
          console.log('Error deleting category');
          this.errorHandler.handleHttpError(err);
        } );
  }

}
