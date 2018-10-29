import {Component, Input} from '@angular/core';
import { NgbModalOptions, NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-modal-content',
  template: `
    <div class="modal-header">
      <h4 class="modal-title">{{title}}</h4>
      <button type="button" class="close" aria-label="Close" (click)="activeModal.dismiss('Cross click')">
        <span aria-hidden="true">&times;</span>
      </button>
    </div>
    <div class="modal-body">
      <p>{{message}}</p>
    </div>
    <div class="modal-footer">
      <button type="button" class="btn btn-primary btn-outline-dark" (click)="activeModal.close('ok')">OK</button>
      <button type="button" class="btn btn-default btn-outline-dark" (click)="activeModal.close('cancel')">Cancel</button>
    </div>
  `
})
export class ModalDialogContent {
  @Input() message;
  @Input() title;

  constructor(public activeModal: NgbActiveModal) {}
}


@Component({
    selector: 'app-modal-dialog',
    templateUrl: './modal-dialog.component.html',
    styleUrls: ['./modal-dialog.component.css']
})
export class ModalDialogComponent {
  
  constructor(private modalService: NgbModal) {
  }

  open(title: string, msg: string, callBack: (s: string) => any) {
    const options: NgbModalOptions = { backdrop: 'static', centered: true, size: 'sm'};
    const modalRef = this.modalService.open(ModalDialogContent, options);
    modalRef.componentInstance.title = title;
    modalRef.componentInstance.message = msg;
    modalRef.result.then( (result: string) => callBack(result) );
  }
}
