import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import { AuthRequest } from 'src/app/model/user.model';
import { AuthService } from 'src/app/service/auth.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{

  title = 'Login to Blood Bank';
  request = new AuthRequest();
  submitted = false;
  message: string= "";

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private authService: AuthService

  ) { }

  ngOnInit(): void {

  }

  onSubmit() {
    
    if (this.request.email == '' || this.request.password == '') {
      this.message = 'Email or password missing.';
    } else {
      this.submitted = true;
      this.authService.login(this.request).subscribe(
        {
          next: (res) => {
            this.successfulLogin(res);
            Swal.fire({
              icon: 'success',
              title: 'Success!',
              text: 'Sucessfully logged in!',
            })
            window.location.href = '/';   
           
          },
          error: (e) => {
            this.submitted = false;
            console.log(e);
            Swal.fire({
              icon: 'error',
              title: 'Oops...',
              text: 'Invalid email or password.',
            })   
          
          }
        });
    
    }
  }
  successfulLogin(data: any) {
    this.authService.setLoggedUser(data);
  }
}

