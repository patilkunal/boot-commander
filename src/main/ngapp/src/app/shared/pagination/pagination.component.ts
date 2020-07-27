import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-pagination',
  templateUrl: './pagination.component.html',
  styleUrls: ['./pagination.component.css']
})
export class PaginationComponent implements OnInit {

  @Input() pagesCount;
  pages: number[];
  @Output() changePageEvent: EventEmitter<number> = new EventEmitter<number>();

  constructor() { }

  ngOnInit(): void {
    this.pages = new Array(this.pagesCount);
  }

  switchPage(pageNumber) {
    this.changePageEvent.emit(pageNumber);
  }

}
