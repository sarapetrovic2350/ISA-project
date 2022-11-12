import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user.model';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { RegisterUserService } from 'src/app/service/register-user.service';


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

  constructor(
    private router: Router,
    private toastr: ToastrService,
    private registerUserService: RegisterUserService
  ) { }

  ngOnInit(): void {
   
  }

  showError(message: string, title: string) {
    this.toastr.error(message, title);
  }

  onSubmit() {
    this.user.address.street = this.street;
    this.user.address.streetNumber = this.streetNumber;
    this.user.address.city = this.city;
    this.user.address.country = this.country;
    this.registerUserService.registerUser(this.user).subscribe(
      {
        next: (res) => {
          this.router.navigate(['/']);
          this.showSuccess();
         
        },
        error: (e) => {
          console.log(e);}

    });
  }

  showSuccess() {
    this.toastr.success('Sucessfully registred!', 'Blood Bank Application');
  }

}
