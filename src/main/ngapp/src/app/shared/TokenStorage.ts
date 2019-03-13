import { Injectable } from '@angular/core';

const AUTH_TOKEN = 'AuthKey';

@Injectable()
export class TokenStorage {
    constructor() { }
    logout() {
        window.sessionStorage.removeItem(AUTH_TOKEN);
        window.sessionStorage.clear();
    }
    public setAuthToken(token: string) {
        window.sessionStorage.removeItem(AUTH_TOKEN);
        window.sessionStorage.setItem(AUTH_TOKEN, token);
    }
    public getAuthToken() {
        return window.sessionStorage.getItem(AUTH_TOKEN);
    }
}
