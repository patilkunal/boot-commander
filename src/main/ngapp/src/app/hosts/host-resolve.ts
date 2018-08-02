import { Host } from '../shared/models/host';
import { HostService } from './hosts.service';
import { Injectable } from '@angular/core';
import { Resolve, RouterStateSnapshot, ActivatedRouteSnapshot } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable()
export class HostResolve implements Resolve<Host> {
  
  constructor(private hostService: HostService) { }
  
  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Host> {
    const id: number = parseInt(route.paramMap.get('id'), 10);
    console.log("Resolving host for id: " + id);
    return this.hostService.getHost(id);
  }
  
}
