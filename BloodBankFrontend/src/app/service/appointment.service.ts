import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PredefAppointment } from '../model/predef-appointment.model';
import { PredefinedAppointment } from '../model/predefined-appointment.model';
import { ScheduledAppointment } from '../model/scheduled-appointment.model';
import {ShowAppointments} from '../model/show-appointments.model'; 

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

  findPredefinedAppointmentsForMedicalCenter(centerId: number): Observable<PredefAppointment[]> {
    return this.http.get<PredefAppointment[]>(this.apiHost + 'appointment/findPredefinedAppointmentsForMedicalCenter/' + centerId);
  }

  schedulePredefinedAppointment(appointmentId: number, registeredUserId: number): Observable<any> {
    return this.http.put<any>(this.apiHost + 'appointment/schedulePredefinedAppointment/' + appointmentId + '/' + registeredUserId, {heaaders: this.headers});
  }

  createAppointmentRegisteredUser(appointmentRegisteredUserDTO: PredefinedAppointment){
    return this.http.post<any>(this.apiHost + 'appointment/createAppointmentRegisteredUser', appointmentRegisteredUserDTO);
  }
  findScheduledAppointmentsForRegisteredUser(registeredUserId: number): Observable<ScheduledAppointment[]> {
    return this.http.get<ScheduledAppointment[]>(this.apiHost + 'appointment/findScheduledAppointmentsForRegisteredUser/' + registeredUserId);
  }

  cancelScheduledAppointment(appointmentId: number): Observable<any> {
    return this.http.put<any>(this.apiHost + 'appointment/cancelScheduledAppointment/' + appointmentId, {heaaders: this.headers});
  }

  getAllAppointmentsByCenterAdministrator(email: string): Observable<ShowAppointments[]> {
    return this.http.get<ShowAppointments[]>(this.apiHost + 'appointment/getAppointmentByCenterAdministratorId/' + email);
  }
  

}
