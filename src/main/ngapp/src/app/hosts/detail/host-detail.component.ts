
import { ErrorHandlerService } from '../../shared/error-handler/error-handler.service';
import { Host } from '../../shared/models/host';
import { HostService } from '../hosts.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-host-detail',
  styleUrls: ['./host-detail.component.css'],
  templateUrl: './host-detail.component.html'
})
export class HostDetailComponent implements OnInit {
  
  private host: Host;
   
  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private hostService: HostService,
    private errorHandler: ErrorHandlerService
  ) { }
  
  ngOnInit() {
    this.host = this.activatedRoute.snapshot.data['host'];
    console.log(this.host);
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
  
}
