
import { ErrorHandlerService } from '../../shared/error-handler/error-handler.service';
import { Host } from '../../shared/models/host';
import { Category } from '../../shared/models/category';
import { HostService } from '../hosts.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-host-detail',
  styleUrls: ['./host-detail.component.css'],
  templateUrl: './host-detail.component.html'
})
export class HostDetailComponent implements OnInit {

  host: Host;
  categories: Category[];

  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private hostService: HostService,
    private errorHandler: ErrorHandlerService
  ) { }

  ngOnInit() {
    this.host = this.activatedRoute.snapshot.data['host'];
    this.categories = this.activatedRoute.snapshot.data['categories'];
    if(this.host.category == null) {
      this.host.category = this.categories[0];
    }
    console.log(this.host);
    console.log(this.host.category);
  }

  save() {
    console.log('Saving Host data');
    this.hostService.save(this.host)
      .subscribe(
        (data) => { this.router.navigate(['/hosts']); },
        (err) => { this.errorHandler.handleHttpError(err); }
      );
  }

  cancel() {
    console.log('Cancelling save');
    this.router.navigate(['/hosts/list']);
  }

  compareCategory(c1: Category, c2: Category): boolean {
    return c1 && c2 ? c1.id === c2.id : c1 === c2;
  }

}
