import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CenterAdministrator } from '../model/center-administrator.model';

@Injectable({
  providedIn: 'root'
})
export class CenterAdministratorService {
  apiHost: string = 'http://localhost:8091/';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }

  getCenterAdministratorById(id: string): Observable<CenterAdministrator> {
    return this.http.get<CenterAdministrator>(this.apiHost + 'centerAdministrator/getCenterAdministratorById/' + id, {headers: this.headers});
  }

  updateCenterAdministrator(centerAdministrator: any): Observable<any> {
    return this.http.put<any>(this.apiHost + 'centerAdministrator/update' , centerAdministrator, {headers: this.headers});
  }

}
