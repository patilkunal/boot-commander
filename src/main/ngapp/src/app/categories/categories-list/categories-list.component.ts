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
              .subscribe((data) => { this.categories = data; console.log(this.categories); },
              (err) => this.errorHandler.handleHttpError(err),
              () => console.log('Categories get complete')
            );
  }

  ngOnDestroy() {
    this.categoriesSub.unsubscribe();
  }

  deleteCategory(cat: Category) {
      console.log(cat);
    if(cat.testCount == 0) {
      this.service.deleteCategory(cat.id).then(
          (data) => {
            console.log('Delete success');
          },
          (err) => {
            console.log('Error deleting category');
            this.errorHandler.handleHttpError(err);
          } );
    } else {
      alert("Cannot delete this category used in test and/or host definitions");
    }
  }

}
