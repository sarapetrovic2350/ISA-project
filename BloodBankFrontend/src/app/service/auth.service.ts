import { Injectable } from '@angular/core';
import { AuthRequest, User } from '../model/user.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  apiHost: string = 'http://localhost:8091/';
  url = this.apiHost + 'auth/login';
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
    localStorage.setItem('currentUser', JSON.stringify(this.user));
    console.log(localStorage.getItem('currentUser'));

  }

  getCurrentUser(): User {
    return JSON.parse(localStorage.getItem('currentUser')!);
  }

  logout() {
    this.user = new User();
    localStorage.setItem('currentUser', JSON.stringify(this.user));
    window.location.href = '/';
  }

}
