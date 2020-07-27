import { Component, OnInit, OnDestroy, AfterContentInit, AfterViewInit, AfterContentChecked, AfterViewChecked, OnChanges, ViewChild } from '@angular/core';
import { Host } from '../../../models/host';
import { HostService } from 'src/app/services/host-service';
import { ActivatedRoute, Router } from '@angular/router';
import { AlertController, IonSearchbar, IonList, ToastController } from '@ionic/angular';
import { Observable, Subscription, BehaviorSubject } from 'rxjs';

@Component({
  selector: 'app-list-hosts',
  templateUrl: './list-hosts.page.html',
  styleUrls: ['./list-hosts.page.scss'],
})
export class ListHostsPage implements OnInit, OnDestroy, AfterViewInit {

  hosts: Host[];
  filteredHosts: Host[];
  hostsSub: Subscription;
  @ViewChild('hostlist') ionlist: IonList;
  @ViewChild('hostsearch') search: IonSearchbar;

  constructor(
    private route: Router,
    private actRoute: ActivatedRoute,
    private hostService: HostService,
    private alertController: AlertController,
    private toastController: ToastController) { }

    ngOnInit() {
      this.hostsSub = this.actRoute.data.subscribe(data => {
        this.hosts = data.hosts;
        this.filteredHosts = this.hosts;
      });
      // this.hosts = this.actRoute.snapshot.data.hosts;
      // this.filteredHosts = this.hosts;
      // this.hostlist.closeSlidingItems();
    }

    ngOnDestroy() {
      this.hostsSub.unsubscribe();
    }

    ngAfterViewInit() {
      this.ionlist.closeSlidingItems()
    }

  async deleteHost(host: Host) {
    // TODO: if host is used for test run, do not delete it
    const modal = await this.alertController.create({
      header: 'Confirm',
      message: `Delete host ${host.name}?`,
      buttons: [
        {
          text: 'Yes',
          handler: () => {
            this.ionlist.closeSlidingItems();
            this.search.value = '';
            this.hostService.deleteHost(host.id).then(() => {
              this.route.navigate(['/secured/hosts/list'], { queryParams: { 'refresh': 1 } });
            }).catch((err) => {
              this.toastMessage(JSON.stringify(err));
            })
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

  edit(id: number) {
    this.route.navigate(['/secured/hosts/edit/' + id]);
  }

  doRefresh(evt) {
      this.hostService.getHosts().then((data) => {
        evt.target.complete();
        this.ionlist.closeSlidingItems();
        this.search.value = '';
        this.hosts = data;
        this.filteredHosts = data;
      })
  }

  async filterHosts(evt) {
    const searchTerm: string = evt.srcElement.value;
    if(!searchTerm) {
      this.filteredHosts = this.hosts;
      return;
    }

    this.filteredHosts = this.hosts.filter(h => h.name.toLowerCase().indexOf(searchTerm.toLowerCase()) > -1);
  }

  async toastMessage(msg: string) {
    const toast = await this.toastController.create({
      header: 'Login error',
      message: msg,
      position: 'top',
      buttons: [
        {
          text: 'Ok',
          role: 'cancel'
        }
      ]
    });
    toast.present();
  }

}
