import { Component, OnInit, ViewChild, ContentChild, AfterViewInit } from '@angular/core';
import { ModalDialogComponent } from '../shared/dialog/modal-dialog.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit, AfterViewInit {

  // @ViewChild(ModalDialogComponent) homemodal;
  @ViewChild('modalprompt') modalPrompt: ModalDialogComponent ;
  

  ngOnInit() {
  }

  ngAfterViewInit() {
    console.log('After view Init');
  }

  
  openDialog(closeFunction) {
    this.modalPrompt.open('Sample Title', 'Hello how are you', (result) => {
      console.log(result);
    }); 
  }

}
