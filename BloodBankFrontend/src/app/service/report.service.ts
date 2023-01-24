import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Report } from '../model/report.model';
import { HistoryOfVisit } from '../model/history-of-visit.model';

@Injectable({
  providedIn: 'root'
})
export class ReportService {

  apiHost: string = 'http://localhost:8091/';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }

  createReport(report: Report):  Observable<any>{
    return this.http.post<any>(this.apiHost + 'report/createReport', report, {headers: this.headers});
  }
  findHistoryOfVisitsForUser(userId: number): Observable<HistoryOfVisit[]> {
    return this.http.get<HistoryOfVisit[]>(this.apiHost + 'report/findHistoryOfVisitsForUser/' + userId);
  }

}
