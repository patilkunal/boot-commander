import {Component, OnInit, OnDestroy} from '@angular/core';

import { ErrorHandlerService } from '../../shared/error-handler/error-handler.service';
import { Host } from '../../shared/models/host';
import { HostService } from '../hosts.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-hosts-list',
  templateUrl: './hosts-list.component.html',
  styleUrls: ['./hosts-list.component.css']
})
export class HostsListComponent implements OnInit, OnDestroy {
  private hosts: Host[];
  private sub: Subscription;

  constructor(
    private hostService: HostService,
    private errorHandler: ErrorHandlerService) { }

  ngOnInit() {
    this.sub = this.hostService.getHosts().subscribe(
      (data) => {
        this.hosts = data;
        console.log(data);
      },
      (err) => {
        this.errorHandler.handleHttpError(err);
      }
    );
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  deleteHost(host: Host) {
    this.hostService.delete(host.id).then(
      (data) => {
        console.log('Successfully deleted host');
      },
      (err) => {
        this.errorHandler.handleHttpError(err);
      }
    )
      
  }
}
