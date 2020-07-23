import { HttpService } from './http.service';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Host } from '../models/host';

@Injectable({
    providedIn: 'root'
})
export class HostService {

    constructor(private httpService: HttpService) {
    }
  
    getHosts(): Observable<Host[]> {
        return this.httpService.get('/hosts');
    }

    getHost(id: number): Observable<Host> {
        return this.httpService.get('/hosts/' + id);
    }

    saveHost(host: Host): Observable<Host> {
        if(host.id > -1) {
            return this.httpService.put('/hosts' + host.id, host);
        } else {
            return this.httpService.post('/hosts', host);
        }
    }

    deleteHost(id: number) {
        // TODO: implement me
        return this.httpService.delete('/hosts' + id);
    }
    
}