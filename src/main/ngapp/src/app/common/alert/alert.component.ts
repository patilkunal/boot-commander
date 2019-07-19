import { Component, OnInit } from '@angular/core';
import { Alert, AlertType } from './alert';
import { AlertService } from './alert.service';

@Component({
    selector: 'app-commander-alert',
    templateUrl: 'alert.component.html'
})
export class AlertComponent implements OnInit {
    alerts: Alert[] = [];
    
    constructor(private alertService: AlertService) {}

    ngOnInit() {
        this.alertService.getAlert().subscribe((alert: Alert) => {
            if (!alert) {
                this.alerts = [];
                return;
            }

            this.alerts.push(alert);
        });
    }

    removeAlert(alert: Alert) {
        this.alerts = this.alerts.filter( x => x !== alert);
    }

    cssClass(alert: Alert) {
        if (!alert) {
            return;
        }
        switch(alert.type) {
            case AlertType.SUCCESS:
                return 'alert alert-success';
            case AlertType.ERROR:
                return 'alert alert-danger';
            case AlertType.INFO:
                return 'alert alert-info';
            case AlertType.WARN:
                return 'alert alert-warning';
        }
    }
}