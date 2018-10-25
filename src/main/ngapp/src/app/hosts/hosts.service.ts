import { ErrorHandlerService } from '../shared/error-handler/error-handler.service';
import { Injectable, ErrorHandler } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Host } from '../shared/models/host';

import { Observable } from 'rxjs';
import {environment} from '../../environments/environment';

@Injectable()
export class HostService {

  private URL = environment.baseAPI_URL + '/hosts';

  constructor(private httpClient: HttpClient, private errorHandler: ErrorHandler) {

  }

  getHosts(): Observable<Host[]> {
    return this.httpClient.get<Host[]>(this.URL);
  }

  getHost(id: number): Observable<Host> {
    return this.httpClient.get<Host>(`${this.URL}/${id}`);
  }

  save(host: Host): Observable<Host> {
    if (host.id > 0) {
      return this.httpClient.put<Host>(`${this.URL}/${host.id}`, host);
    } else {
      return this.httpClient.post<Host>(this.URL, host);
    }
  }

  delete(id: number) {
    return this.httpClient.delete(`${this.URL}/${id}`).toPromise();
  }
}
