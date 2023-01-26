import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { User } from 'src/app/model/user.model';
import { AuthService } from 'src/app/service/auth.service';
import { RegisterUserService } from 'src/app/service/register-user.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css']
})
export class UpdateUserComponent implements OnInit {
  title = 'Update your informations';

  //public user: User | undefined = undefined;
  user = new User();
  message: string="";
  submitted = false;
  name: string= "";
  surname: string= "";

  constructor(private toastr: ToastrService,private userService: RegisterUserService, private route: ActivatedRoute,
    private router: Router, private authService: AuthService) { }

  ngOnInit(): void {
    this.user = this.authService.getCurrentUser();
    if (this.user != null) {
      this.userService.getUserByEmail(this.user.email).subscribe(res => {
        this.user = res;
      })
    }
  }

  public updateUser() : void {
    if (
      !this.validateFirstName() ||
      !this.validateLastName() ||
      !this.validateJmbg() ||
      !this.validatePhoneNumber() ||
      !this.validateStreet() ||
      !this.validateStreetNumber() ||
      !this.validateCity() ||
      !this.validateCountry()
    ) {
      this.submitted = false;
      return
    }
    this.userService.updateUser(this.user).subscribe( 
      {next: (res) => {
        this.router.navigate(['/update-user']);
          Swal.fire({
            icon: 'success',
            title: 'Success!',
            text: 'Sucessfully updated!',
          })  
      },
      error: (e) => {
        console.log(e);
        this.submitted = false;
          Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Check the fields again!',
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
    if (this.user.address.street.match(/[!@#$%^&*.,:'<>+-/\\"]/g)) {
      this.message = "Your street name shouldn't contain special characters.";
      return false;
    } else if (this.user.address.street.match(/\d/g)) {
      this.message = "Your street name shouldn't contain numbers!";
      return false;
    } else if (!/^[A-Z][a-z]+[ ]?[A-Z]*[a-z]*$/.test(this.user.address.street)) {
      this.message ="Your street name needs to have one upper letter at the start!";
      return false;
    }
    return true;
  }
  validateStreetNumber() {
    if (this.user.address.streetNumber.match(/[ ]/g)) {
      this.message = "Your street number shouldn't contain spaces!";
      return false;
    } else if (this.user.address.streetNumber.match(/[!@#$%^&*.,:'<>+/\\"]/g)) {
      this.message = "Your street number shouldn't contain special characters.";
      return false;
    } else if (this.user.address.streetNumber.length < 1) {
      this.message = "Your street number should contain at least one number.";
      return false;
    }
    return true;
  }
  validateCity() {
    if (this.user.address.city.match(/[!@#$%^&*.,:'<>+-/\\"]/g)) {
      this.message ="Your city name shouldn't contain special characters.";
      return false;
    } else if (this.user.address.city.match(/\d/g)) {
      this.message ="Your city name shouldn't contain numbers!";
      return false;
    } else if (!/^[A-Z][a-z]+[ ]?[A-Z]*[a-z]*$/.test(this.user.address.city)) {
      this.message ="Your city name needs to have one upper letter at the start!";
      return false;
    }
    return true;
  }
  validateCountry() {
    if (this.user.address.country.match(/[!@#$%^&*.,:'<>+-/\\"]/g)) {
      this.message ="Your country name shouldn't contain special characters.";
      return false;
    } else if (this.user.address.country.match(/\d/g)) {
      this.message ="Your country name shouldn't contain numbers!";
      return false;
    } else if (!/^[A-Z][a-z]+[ ]?[A-Z]*[a-z]*$/.test(this.user.address.country)) {
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

}
