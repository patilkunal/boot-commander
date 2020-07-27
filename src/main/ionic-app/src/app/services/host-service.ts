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

    save(host: Host): Observable<Host> {
        if(host.id > -1) {
            console.log('Updating host: ' + host);
            return this.httpService.put('/hosts/' + host.id, host);
        } else {
            console.log('Saving new host: ' + host);
            return this.httpService.post('/hosts', host);
        }
    }

    deleteHost(id: number): Promise<any> {
        // TODO: implement me
        return this.httpService.delete('/hosts/' + id).toPromise();
    }
    
}