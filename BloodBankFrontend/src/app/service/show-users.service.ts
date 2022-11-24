import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../model/user.model';

@Injectable({
  providedIn: 'root'
})
export class ShowUsersService {

  apiHost: string = 'http://localhost:8091/';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }

  getAll(): Observable<User[]>{
    return this.http.get<User[]>(this.apiHost + 'user/getAll');
  }

  getAllRegistredUsers(): Observable<User[]>{
    return this.http.get<User[]>(this.apiHost + 'user/getAllRegistredUsers');
  }

  searchUsersForSystemAdmin(name : string, surname : string): Observable<User[]> {
    if(surname == ""){
      surname = "null";
    }
    if(name == ""){
      name = "null"
    }
    return this.http.get<User[]>(this.apiHost + 'user/findUserByNameAndSurnameForSystemAdmin/' + name + '/' + surname, {headers: this.headers});
  }

  searchUsersForCenterAdmin(name : string, surname : string): Observable<User[]> {
    if(surname == ""){
      surname = "null";
    }
    if(name == ""){
      name = "null"
    }
    return this.http.get<User[]>(this.apiHost + 'user/findUserByNameAndSurnameForCenterAdmin/' + name + '/' + surname, {headers: this.headers});
  }

}
