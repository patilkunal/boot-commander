import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';
  pizzaSelection: any;
  menuitems = ['File', 'User', 'Host'];

  pizzaClick(evt) {
    console.log(this.pizzaSelection);
    console.log(evt);
  }
}
