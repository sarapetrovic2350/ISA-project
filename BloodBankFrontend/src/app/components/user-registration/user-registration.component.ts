import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user.model';
import { Router } from '@angular/router';
import { RegisterUserService } from 'src/app/service/register-user.service';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-user-registration',
  templateUrl: './user-registration.component.html',
  styleUrls: ['./user-registration.component.css']
})
export class UserRegistrationComponent implements OnInit {

  title = 'Register to Blood Bank';
  user = new User();
  passwordRepeated: string= "";
  street: string = "";
  streetNumber: string = "";
  city: string= "";
  country: string="";
  message: string="";
  submitted = false;

  constructor(
    private router: Router,
    private registerUserService: RegisterUserService
  ) { }

  ngOnInit(): void {
   
  }

  onSubmit() {

     if(this.passwordRepeated != this.user.password) {
      this.message = "Passwords do not match!";
      return
    }

    this.user.address.street = this.street;
    this.user.address.streetNumber = this.streetNumber;
    this.user.address.city = this.city;
    this.user.address.country = this.country;
    this.registerUserService.registerUser(this.user).subscribe(
      {
        next: (res) => {
          this.router.navigate(['/login']);
          Swal.fire({
            icon: 'success',
            title: 'Success!',
            text: 'Sucessfully registered!',
          })   
         
        },
        error: (e) => {
          this.submitted = false;
          console.log(e);
          Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Email already exists.',
          })   
        
        }

    });
  }

}
