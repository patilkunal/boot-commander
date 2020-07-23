import { Component, OnInit } from '@angular/core';
import { Host } from '../../../models/host';
import { HostService } from 'src/app/services/host-service';
import { ActivatedRoute } from '@angular/router';
import { AlertController, IonSearchbar } from '@ionic/angular';

@Component({
  selector: 'app-list-hosts',
  templateUrl: './list-hosts.page.html',
  styleUrls: ['./list-hosts.page.scss'],
})
export class ListHostsPage implements OnInit {

  hosts: Host[];
  filteredHosts: Host[];

  constructor(
    private actRoute: ActivatedRoute,
    private hostService: HostService,
    private alertController: AlertController) { }

  ngOnInit() {
    this.hosts = this.actRoute.snapshot.data['hosts'];
    this.filteredHosts = this.hosts;
  }

  async deleteHost(host: Host) {
    // TODO: if host is used for test run, do not delete it
    const modal = await this.alertController.create({
      header: 'Confirm',
      message: 'Delete host?',
      buttons: [
        {
          text: 'Yes',
          handler: () => {
            this.hostService.deleteHost(host.id);
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

  edit(host: Host) {

  }

  async filterHosts(evt) {
    const searchTerm: string = evt.srcElement.value;
    if(!searchTerm) {
      this.filteredHosts = this.hosts;
      return;
    }

    this.filteredHosts = this.hosts.filter(h => h.name.toLowerCase().indexOf(searchTerm.toLowerCase()) > -1);
  }

}
