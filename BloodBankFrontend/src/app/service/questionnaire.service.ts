import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Questionnaire } from '../model/questionnaire.model';

@Injectable({
  providedIn: 'root'
})
export class QuestionnaireService {
  apiHost: string = 'http://localhost:8091/';
  url = this.apiHost + 'donorQuestionnaire/saveQuestionnaire';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  
  constructor(private http: HttpClient) { }
  saveQuestionnaire(questionnaire: Questionnaire){
    return this.http.post<any>(this.url, questionnaire);
  }

  checkQuestionnaire(id: any): Observable<any>{
    return this.http.get<any>(this.apiHost + 'donorQuestionnaire/checkQuestionnaire/' + id, {headers: this.headers});
  }
}
