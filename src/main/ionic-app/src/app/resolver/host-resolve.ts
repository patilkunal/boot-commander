import { Injectable } from '@angular/core';
import { Resolve, RouterStateSnapshot, ActivatedRouteSnapshot } from '@angular/router';
import { Observable } from 'rxjs';
import { HostService } from 'src/app/services/host-service';
import { CategoriesService } from 'src/app/services/categories.service';
import { Host } from '../models/host';

@Injectable({
  providedIn: 'root'
})
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

@Injectable({
  providedIn: 'root'
})export class HostListResolve implements Resolve<Host[]> {

  constructor(private hostService: HostService) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Promise<Host[]> {
      console.log('Getting all hosts');
      return this.hostService.getHosts();
  }

}
