import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {

  menuitems: MenuItem[];

  constructor() { }

  ngOnInit() {
    this.menuitems = [
      {label: 'File'},
      {label: 'Edit'}
    ];
  }

}
