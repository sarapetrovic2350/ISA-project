import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SystemAdministrator } from '../model/system-administrator';

@Injectable({
  providedIn: 'root'
})
export class SystemAdministratorService {

  apiHost: string = 'http://localhost:8091/';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }

  createSystemAdmin(systemAdministrator: any): Observable<SystemAdministrator>{
    return this.http.post<any>(this.apiHost + 'systemAdministrator/registerSystemAdministrator' , systemAdministrator, {headers: this.headers});
  }
}
