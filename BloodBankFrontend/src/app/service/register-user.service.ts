import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../model/user.model';

@Injectable({
  providedIn: 'root'
})

export class RegisterUserService {
  apiHost: string = 'http://localhost:8091/';
  url = this.apiHost + 'user/registerUser';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(
    private http: HttpClient
    ) {}

    registerUser(user: User) {
      return this.http.post<any>(this.url, user);
    }
}
