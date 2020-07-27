import { Host } from '../shared/models/host';
import { HostService } from './hosts.service';
import { Injectable } from '@angular/core';
import { Resolve, RouterStateSnapshot, ActivatedRouteSnapshot } from '@angular/router';
import { Observable } from 'rxjs';
import { CategoriesService } from '../categories/categories.service';

@Injectable()
export class HostResolve implements Resolve<Host> {

  constructor(private hostService: HostService, private categorieService: CategoriesService) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Host | Observable<Host> {
    const id: number = parseInt(route.paramMap.get('id'), 10);
    console.log("Resolving host for id: " + id);
    if(id > 0) {
      return this.hostService.getHost(id);
    } else {
      let host = new Host();
      host.id = -1;
      host.port = 80;
      host.secureHttp = false;
      return host;
    }
  }

}
