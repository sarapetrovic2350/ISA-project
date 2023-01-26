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

  getAll(params: any): Observable<medicalCenter[]> {
    return this.http.get<medicalCenter[]>(this.apiHost + 'medicalCenter/findAll', { params });
  }

  getAllCenters(): Observable<medicalCenter[]> {
    return this.http.get<medicalCenter[]>(this.apiHost + 'medicalCenter/getAll', {});
  }
  sortMedicalCentersByNameAsc(params: any):  Observable<medicalCenter[]> {
    return this.http.get<medicalCenter[]>(this.apiHost + 'medicalCenter/findAllSortedByName', { params });
  }
  sortMedicalCentersByCityNameAsc(params: any):  Observable<medicalCenter[]> {
    return this.http.get<medicalCenter[]>(this.apiHost + 'medicalCenter/findAllSortedByCityName', { params });
  }
  sortMedicalCentersByAverageGradeDesc(params: any):  Observable<medicalCenter[]> {
    return this.http.get<medicalCenter[]>(this.apiHost + 'medicalCenter/findAllSortedByAverageGrade', { params });
  }

  createMedicalCenter(medicalCenter: any): Observable<any> {
    console.log(medicalCenter); 
    return this.http.post<any>(this.apiHost + 'medicalCenter/createCenter' , medicalCenter, {headers: this.headers});
  }

  searchMediclaCenter(name : string, place : string): Observable<medicalCenter[]> {
    if(place == ""){
      place = "null";
    }
    if(name == ""){
      name = "null"
    }
    return this.http.get<medicalCenter[]>(this.apiHost + 'medicalCenter/searchMedicalCenterByNameAndPlace/' + name + '/' + place, {headers: this.headers});
  }
  filterMedicalCenters(name : string, place : string, grade:string) : Observable<medicalCenter[]> {
    if(place == ""){
      place = "null";
    }
    if(name == ""){
      name = "null"
    }
    if(grade == "1"){
      grade = "1-2"
    }else if(grade == "2"){
      grade = "2-3"
    }else if(grade == "3"){
      grade = "3-4"
    }else{
      grade = "4-5"
    }
    console.log(grade)
    return this.http.get<medicalCenter[]>(this.apiHost + 'medicalCenter/filterMedicalCenter/' + name + '/' + place + '/' + grade, {headers: this.headers});
  }

  getMedicalCentersWithAvailableAppointment(params: any): Observable<medicalCenter[]> {
    return this.http.get<medicalCenter[]>(this.apiHost + 'medicalCenter/medicalCentersWithAvailableAppointment', { params });
  }

}
