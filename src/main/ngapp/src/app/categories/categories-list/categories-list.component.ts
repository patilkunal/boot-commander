import { ErrorHandlerService } from '../../shared/error-handler/error-handler.service';
import { Category } from '../../shared/models/category';
import { CategoriesService } from '../categories.service';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-categories-list',
  templateUrl: './categories-list.component.html',
  styleUrls: ['./categories-list.component.css']
})
export class CategoriesListComponent implements OnInit, OnDestroy {

  public categories: Category[];
  //Use these for paging when we implement it on backend API
  currentPage: number;
  pageSize: number;

  constructor(
    private actRoute: ActivatedRoute,
    private service: CategoriesService,
    private errorHandler: ErrorHandlerService) { }

  ngOnInit() {
    this.categories = this.actRoute.snapshot.data['categories'];
  }

  ngOnDestroy() {
    
  }

  // we will get it from the $event binding from the pagination component
  updatePage(pageNumber) {
    this.currentPage = pageNumber;
  }

  deleteCategory(cat: Category) {
      console.log(cat);
    if (cat.testCount === 0) {
      this.service.deleteCategory(cat.id).then(
          (data) => {
            console.log('Delete success');
          },
          (err) => {
            console.log('Error deleting category');
            this.errorHandler.handleHttpError(err);
          } );
    } else {
      alert('Cannot delete this category used in test and/or host definitions');
    }
  }

}
