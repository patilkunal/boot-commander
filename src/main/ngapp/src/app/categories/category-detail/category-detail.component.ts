import {Component, OnInit, OnDestroy} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {Router, ActivatedRoute, ParamMap} from '@angular/router';
import {CategoriesService} from '../categories.service';
import {Category} from '../../shared/models/category';
import {ErrorHandlerService} from '../../shared/error-handler/error-handler.service';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-category-detail',
  templateUrl: './category-detail.component.html',
  styleUrls: ['./category-detail.component.css']
})
export class CategoryDetailComponent implements OnInit {

  category: Category;
  private id: number;
  private svcSubscription: Subscription;

  constructor(
    private actRoute: ActivatedRoute,
    private router: Router,
    private categoryService: CategoriesService,
    private errorHandler: ErrorHandlerService) {
  }

  ngOnInit() {
    // we get category data from the resolver
    this.category = this.actRoute.snapshot.data['category'];
  }

  save() {
    console.log('Saving category detail');
    this.categoryService.saveCategory(this.category)
      .subscribe(
      (data) => { this.router.navigate(['/categories']); },
      (err) => this.errorHandler.handleHttpError(err)
      );

  }

  cancel() {
    console.log('Cancelling save');
    this.router.navigate(['/categories/list']);
  }

}
