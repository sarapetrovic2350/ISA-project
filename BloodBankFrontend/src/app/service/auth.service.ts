import { Injectable } from '@angular/core';
import { AuthRequest, User } from '../model/user.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  apiHost: string = 'http://localhost:8091/';
  url = this.apiHost + 'auth/login';
  activate_account_url = this.apiHost + 'auth/activate-account/';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  private user = new User();
  jwt: string = "";
  expiresIn: number = -1;

  constructor(private http: HttpClient) {}

  login(request: AuthRequest) {
    return this.http.post<any>(this.url, request);
  }

  setLoggedUser(data: any) {
    this.user = data.user;
    this.jwt = data.accessToken;
    this.expiresIn = data.expiresIn;
    localStorage.setItem('jwt', this.jwt);
    localStorage.setItem('refreshToken', JSON.stringify(this.expiresIn));
    localStorage.setItem('currentUser', JSON.stringify(this.user));
    localStorage.setItem('role', data.user.authority.name);
    console.log(localStorage.getItem('role'));
    console.log(localStorage.getItem('currentUser'));

  }
  activateAccount(token: string) {
    return this.http.put<any>(this.activate_account_url + token, {headers: this.headers});
  }

  getCurrentUser(): User {
    return JSON.parse(localStorage.getItem('currentUser')!);
  }

  logout() { 
    this.jwt = "";
    localStorage.clear();
    window.location.href = '/login';
  }

}
