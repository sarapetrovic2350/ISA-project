import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Questionnaire } from 'src/app/model/questionnaire.model';
import { User } from 'src/app/model/user.model';
import { AuthService } from 'src/app/service/auth.service';
import { QuestionnaireService } from 'src/app/service/questionnaire.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-questionnaire',
  templateUrl: './questionnaire.component.html',
  styleUrls: ['./questionnaire.component.css']
})
export class QuestionnaireComponent implements OnInit {

  title = 'Donor questionnaire';
  user = new User();
  questionnaire = new Questionnaire();
  submitted = false;
  weight: string = "";
  age: string = "";

  constructor(
    private authService: AuthService,
    private questionnaireService: QuestionnaireService,
    private router: Router) { }

  ngOnInit(): void {
    this.user = this.authService.getCurrentUser();
  }

  onSubmit() {
    this.questionnaire.weight = parseInt(this.weight);
    this.questionnaire.age = parseInt(this.age);
    this.submitted = true;
    this.questionnaire.userEmail = this.user.email;
    this.questionnaireService.saveQuestionnaire(this.questionnaire).subscribe(
      {
        next: (res) => {
          Swal.fire({
            icon: 'success',
            title: 'Success!',
            text: 'Sucessfully saved!',
          })

        },
        error: (e) => {
          this.submitted = false;
          console.log(e);
          Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Something went wrong.',
          })

        }
      });
    
  }
}
