import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { PredefinedAppointment } from '../model/predefined-appointment.model';

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {

  apiHost: string = 'http://localhost:8091/';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }

  createPredefinedAppointment(appointment: PredefinedAppointment) {
    return this.http.post<any>(this.apiHost + 'appointment/createPredefinedAppointment', appointment);
  }
}
