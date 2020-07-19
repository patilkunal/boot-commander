import { Component, OnInit } from '@angular/core';
import { Host } from '../../../models/host';
import { HostService } from 'src/app/services/host-service';

@Component({
  selector: 'app-list-hosts',
  templateUrl: './list-hosts.page.html',
  styleUrls: ['./list-hosts.page.scss'],
})
export class ListHostsPage implements OnInit {

  hosts: Host[];

  constructor(private hostService: HostService) { }

  ngOnInit() {
  }

}
