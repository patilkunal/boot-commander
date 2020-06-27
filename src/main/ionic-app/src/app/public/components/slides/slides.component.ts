import { Component, OnInit } from '@angular/core';
import { IonSlides } from '@ionic/angular';

@Component({
  selector: 'app-slides',
  templateUrl: './slides.component.html',
  styleUrls: ['./slides.component.scss'],
})
export class SlidesComponent implements OnInit {

  slideOpts = {
    initialSlide: 1,
    speed: 400
  };

  constructor() { }

  ngOnInit() {}

}
