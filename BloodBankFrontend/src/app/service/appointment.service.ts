import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PredefAppointment } from '../model/predef-appointment.model';
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

  findPredefinedAppointmentsForMedicalCenter(centerId: number): Observable<PredefAppointment[]> {
    return this.http.get<PredefAppointment[]>(this.apiHost + 'appointment/findPredefinedAppointmentsForMedicalCenter/' + centerId);
  }

  schedulePredefinedAppointment(appointmentId: number, registeredUserId: number): Observable<any> {
    return this.http.put<any>(this.apiHost + 'appointment/schedulePredefinedAppointment/' + appointmentId + '/' + registeredUserId, {heaaders: this.headers});
  }
}
