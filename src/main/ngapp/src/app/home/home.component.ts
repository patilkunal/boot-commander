import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
//import * as jQuery from 'jquery';
import { ModalDialogComponent } from '../shared/dialog/modal-dialog.component';

declare var jQuery:any;

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit, AfterViewInit {

  @ViewChild('hmodal') homemodal;

  constructor() { }

  ngOnInit() {
  }

  ngAfterViewInit() {
    console.log("After view init");
    console.log(this.homemodal);
  }

  openDialog() {
    jQuery(this.homemodal.nativeElement).modal('show'); 
    console.log(this.homemodal);
    //this.homemodal.open('123');
  }

}
