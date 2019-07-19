import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { Alert, AlertType } from './alert';
import { Router, NavigationStart } from '@angular/router';

@Injectable()
export class AlertService {

    private subject = new Subject<Alert>();
    private keepAfterRouteChange = false;

    constructor(private router: Router) {
        router.events.subscribe(event => {
            if (event instanceof NavigationStart) {
                if (this.keepAfterRouteChange) {
                    this.keepAfterRouteChange = false;
                } else {
                    this.clear();
                }
            }
        });
    }

    getAlert(): Observable<any> {
        return this.subject.asObservable();
    }

    success(message: string, keepAfterRouteChange: boolean = false) {
        this.alert(AlertType.SUCCESS, message, keepAfterRouteChange);
    }

    error(message: string, keepAfterRouteChange: boolean = false) {
        this.alert(AlertType.ERROR, message, keepAfterRouteChange);
    }

    info(message: string, keepAfterRouteChange: boolean = false) {
        this.alert(AlertType.INFO, message, keepAfterRouteChange);
    }

    warn(message: string, keepAfterRouteChange: boolean = false) {
        this.alert(AlertType.WARN, message, keepAfterRouteChange);
    }

    alert(_type: AlertType, msg: string, keepAfterRouteChange: boolean) {
        this.keepAfterRouteChange = keepAfterRouteChange;
        this.subject.next(<Alert>{type: _type, message: msg});
    }

    clear() {
        this.subject.next();
    }
    
}