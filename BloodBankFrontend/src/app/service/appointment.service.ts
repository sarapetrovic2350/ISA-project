import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PredefinedAppointment } from '../model/predefined-appointment.model';

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {

  apiHost: string = 'http://localhost:8091/';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }

  createPredefinedAppointment(appointment: PredefinedAppointment) {
    console.log(appointment)
    return this.http.post<any>(this.apiHost + 'appointment/createPredefinedAppointment', appointment);
  }

  findPredefinedAppointmentsForMedicalCenter(centerId: number): Observable<any[]> {
    return this.http.get<any[]>(this.apiHost + 'appointment/findPredefinedAppointmentsForMedicalCenter/' + centerId);
  }

  schedulePredefinedAppointment(appointmentId: number, registeredUserId: number): Observable<any> {
    return this.http.put<any>(this.apiHost + 'appointment/schedulePredefinedAppointment/' + appointmentId + '/' + registeredUserId, {heaaders: this.headers});
  }

  createAppointmentRegisteredUser(appointmentRegisteredUserDTO: PredefinedAppointment){
    return this.http.post<any>(this.apiHost + 'appointment/createAppointmentRegisteredUser', appointmentRegisteredUserDTO);
  }
}
