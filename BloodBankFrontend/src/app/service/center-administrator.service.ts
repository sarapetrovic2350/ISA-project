import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CenterAdministrator } from '../model/center-administrator.model';
import {medicalCenter} from '../model/medicalCenter.model';
import { ChangePasswordDTO } from '../model/change-password-dto.model';

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

  findByEmail(email: string): Observable<CenterAdministrator>{
    return this.http.get<CenterAdministrator>(this.apiHost + 'centerAdministrator/getCenterAdministratorByEmail/' + email, {headers: this.headers});
  }

  findCenterByAdminEmail(email: string): Observable<medicalCenter>{
    return this.http.get<medicalCenter>(this.apiHost + 'centerAdministrator/getMedicalCenterByAdminEmail/' + email, {headers: this.headers});
  }

  findAdministratorsByCenterId(id: string): Observable<CenterAdministrator[]>{
    return this.http.get<CenterAdministrator[]>(this.apiHost + 'centerAdministrator/getCenterAdministratorsByCenterId/' + id, {headers: this.headers});
  }

  changePassword(newPassword: ChangePasswordDTO): Observable<CenterAdministrator>{
    return this.http.put<any>(this.apiHost + 'centerAdministrator/changePassword' , newPassword, {headers: this.headers});
  }

  createCenterAdmin(centerAdministrator: any): Observable<CenterAdministrator>{
    return this.http.post<any>(this.apiHost + 'centerAdministrator/registerCenterAdministrator' , centerAdministrator, {headers: this.headers});
  }
}
