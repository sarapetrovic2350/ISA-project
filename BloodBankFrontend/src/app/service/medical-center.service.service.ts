import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { medicalCenter } from '../model/medicalCenter.model';

@Injectable({
  providedIn: 'root'
})

export class MedicalCenterServiceService {
  apiHost: string = 'http://localhost:8091/';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }

  getMedicalCenterById(centerId: string): Observable<medicalCenter> {
    return this.http.get<medicalCenter>(this.apiHost + 'medicalCenter/getMedicalCenterById/' + centerId, {headers: this.headers});
  }

  updateMedicalCenter(medicalCenter: any): Observable<any> {
    console.log(medicalCenter); 
    return this.http.put<any>(this.apiHost + 'medicalCenter/updateCenter' , medicalCenter, {headers: this.headers});
  }

}
