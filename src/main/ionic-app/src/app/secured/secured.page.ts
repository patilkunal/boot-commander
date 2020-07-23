import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../services/authentication.service';
import { AlertController } from '@ionic/angular';

@Component({
  selector: 'app-secured',
  templateUrl: './secured.page.html',
  styleUrls: ['./secured.page.scss'],
})
export class SecuredPage implements OnInit {

  constructor(
    private router: Router, 
    private authService: AuthenticationService,
    private alertController: AlertController) { }

  ngOnInit() {
  }

  logoutAction() {
    this.authService.logout().then(resp => {
      this.router.navigate(['']);
    });  
  }

  async logoutConfirm() {
    const modal = await this.alertController.create({
      header: 'Logout',
      message: 'Do you want to logout?',
      buttons: [
        {
          text: 'Yes',
          handler: () => {
            this.logoutAction();
          }
        },
        {
          text: 'No',
          role: 'cancel',
          cssClass: 'secondary'
        }
      ]
    });
    await modal.present();
  }
}
