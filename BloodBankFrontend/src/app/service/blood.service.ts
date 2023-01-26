import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Blood } from '../model/blood.model';

@Injectable({
  providedIn: 'root'
})
export class BloodService {

  apiHost: string = 'http://localhost:8091/';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }

  showBloodByCenterId(centerId: number): Observable<Blood> {
    return this.http.get<Blood>(this.apiHost + 'blood/getBloodByCenterId/' + centerId);
  }
}
