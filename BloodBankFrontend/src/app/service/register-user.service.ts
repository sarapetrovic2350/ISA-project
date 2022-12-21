import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../model/user.model';
import { ChangePasswordDTO } from '../model/change-password-dto.model';

@Injectable({
  providedIn: 'root'
})

export class RegisterUserService {
  apiHost: string = 'http://localhost:8091/';
  url = this.apiHost + 'user/registerUser';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) {}

    registerUser(user: User) {
      return this.http.post<any>(this.url, user);
    }

    getUserByEmail(email: string): Observable<User> {
      return this.http.get<User>(this.apiHost + 'user/getUserByEmail/' + email, {headers: this.headers});
    }

    updateUser(user: any): Observable<any> {
      return this.http.put<any>(this.apiHost + 'user/update' , user, {headers: this.headers});
    }

    changePassword(newPassword: ChangePasswordDTO): Observable<ChangePasswordDTO>{
      return this.http.put<any>(this.apiHost + 'user/changePassword' , newPassword, {headers: this.headers});
    }

    getUserById(id: any): Observable<any>{
      return this.http.get<User>(this.apiHost + 'user/getUserById/' + id, {headers: this.headers});
    }

    checkPenalties(id: any, present: any): Observable<any>{
      return this.http.get<User>(this.apiHost + 'user/checkPenalties/' + id +'/'+ present, {headers: this.headers});
    }
}
