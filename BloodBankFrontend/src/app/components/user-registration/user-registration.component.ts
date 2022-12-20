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
  jmbg: string = "";

  constructor(
    private router: Router,
    private registerUserService: RegisterUserService
  ) { }

  ngOnInit(): void {
   
  }

  onSubmit() {
    this.user.jmbg = parseInt(this.jmbg)
    if (
      !this.validateFirstName() ||
      !this.validateLastName() ||
      !this.validateJmbg() ||
      !this.validateEmail() ||
      !this.validatePhoneNumber() ||
      !this.validateStreet() ||
      !this.validateStreetNumber() ||
      !this.validateCity() ||
      !this.validateCountry() ||
      !this.validatePassword()
    ) {
      this.submitted = false;
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
            text: 'Account verification link has been sent to your email. Please check your email and confirm the registration!',
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
  validateFirstName() {
    if (this.user.name.length < 2) {
      this.message = "Your first name should contain at least 2 characters!";
      return false;
    } else if (this.user.name.match(/[!@#$%^&*.,:'<>+-/\\"]/g)) {
      this.message = "Your first name shouldn't contain special characters.";
      return false;
    } else if (this.user.name.match(/\d/g)) {
      this.message = "Your first name shouldn't contain numbers!";
      return false;
    } else if (!/^[A-Z][a-z]+$/.test(this.user.name)) {
      this.message = "Your first name needs to have one upper letter at the start!";
      return false;
    }
    return true;
  }

  validateLastName() {
    if (this.user.surname.length < 2) {
      this.message = "Your last name should contain at least 2 characters!";
      return false;
    } else if (this.user.surname.length > 36) {
      this.message = "Your last name shouldn't contain more than 36 characters!";
      return false;
    } else if (this.user.surname.match(/[!@#$%^&*.,:'<>+-/\\"]/g)) {
      this.message = "Your last name shouldn't contain special characters.";
      return false;
    } else if (this.user.surname.match(/\d/g)) {
      this.message = "Your last name shouldn't contain numbers!";
      return false;
    } else if (!/^[A-Z][a-z]+[ ]?[A-Z]*[a-z]*$/.test(this.user.surname)) {
      this.message = "Your last name needs to have one upper letter at the start!";
      return false;
    }
    return true;
  }
  validateStreet() {
    if (this.street.match(/[!@#$%^&*.,:'<>+-/\\"]/g)) {
      this.message = "Your street name shouldn't contain special characters.";
      return false;
    } else if (this.street.match(/\d/g)) {
      this.message = "Your street name shouldn't contain numbers!";
      return false;
    } else if (!/^[A-Z][a-z]+[ ]?[A-Z]*[a-z]*$/.test(this.street)) {
      this.message ="Your street name needs to have one upper letter at the start!";
      return false;
    }
    return true;
  }
  validateStreetNumber() {
    if (this.streetNumber.match(/[ ]/g)) {
      this.message = "Your street number shouldn't contain spaces!";
      return false;
    } else if (this.streetNumber.match(/[!@#$%^&*.,:'<>+/\\"]/g)) {
      this.message = "Your street number shouldn't contain special characters.";
      return false;
    } else if (this.streetNumber.length < 1) {
      this.message = "Your street number should contain at least one number.";
      return false;
    }
    return true;
  }
  validateCity() {
    if (this.city.match(/[!@#$%^&*.,:'<>+-/\\"]/g)) {
      this.message ="Your city name shouldn't contain special characters.";
      return false;
    } else if (this.city.match(/\d/g)) {
      this.message ="Your city name shouldn't contain numbers!";
      return false;
    } else if (!/^[A-Z][a-z]+[ ]?[A-Z]*[a-z]*$/.test(this.city)) {
      this.message ="Your city name needs to have one upper letter at the start!";
      return false;
    }
    return true;
  }
  validateCountry() {
    if (this.country.match(/[!@#$%^&*.,:'<>+-/\\"]/g)) {
      this.message ="Your country name shouldn't contain special characters.";
      return false;
    } else if (this.country.match(/\d/g)) {
      this.message ="Your country name shouldn't contain numbers!";
      return false;
    } else if (!/^[A-Z][a-z]+[ ]?[A-Z]*[a-z]*$/.test(this.country)) {
      this.message ="Your country name needs to have one upper letter at the start!";
      return false;
    }
    return true;
  }
  validatePhoneNumber() {
    if (this.user.phoneNumber.match(/[a-zA-Z]/g)) {
      this.message = "Your phone number shouldn't contain letters.";
      return false;
    } else if (this.user.phoneNumber.match(/[ ]/g)) {
      this.message ="Your phone number shouldn't contain spaces!";
      return false;
    } else if (
      !/^[+]*[(]{0,1}[0-9]{1,3}[)]{0,1}[-\\s./0-9]*$/.test(this.user.phoneNumber)
    ) {
      this.message = "Your phone number is not in right form!";
      return false;
    }
    return true;
  }
  validateEmail() {
    if (this.user.email == "") {
      this.message = "Please enter your email.";
      return false;
    } else if (
      !/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/.test(this.user.email)
    ) {
      this.message = "You have entered an invalid email!";
      return false;
    }
    return true;
  }
  validateJmbg() {
    if (this.user.jmbg.toString().match(/[a-zA-Z]/g)) {
      this.message = "Your jmbg shouldn't contain letters.";
      return false;
    } else if (this.user.jmbg.toString().match(/[ ]/g)) {
      this.message ="Your jmbg shouldn't contain spaces!";
      return false;
    } else if (this.user.jmbg < 13){
      this.message = "Your jmbg needs to contain 13 numbers!";
      return false;
    }
    return true;
  }

  validatePassword() {
    if (this.user.password.length < 6) {
      this.message = "Your password should contain at least 6 character!";
      return false; 
    } else if (!this.user.password.match(/[A-Z]/g)) {
      this.message = "Your password should contain at least one big letter.";
      return false;
    } else if (!this.user.password.match(/[0-9]/g)) {
      this.message = "Your password should contain at least one number.";
      return false;
    } else if (this.user.password !== this.passwordRepeated) {
      this.message = "Passwords do not match!";
      return false;
    }
    return true;
  }

}
