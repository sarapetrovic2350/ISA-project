import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { CenterAdministrator } from 'src/app/model/center-administrator.model';
import { medicalCenter } from 'src/app/model/medicalCenter.model';
import { User } from 'src/app/model/user.model';
import { AuthService } from 'src/app/service/auth.service';
import { CenterAdministratorService } from 'src/app/service/center-administrator.service';
import { MedicalCenterServiceService } from 'src/app/service/medical-center.service.service';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-create-center-admin',
  templateUrl: './create-center-admin.component.html',
  styleUrls: ['./create-center-admin.component.css']
})
export class CreateCenterAdminComponent implements OnInit {

  medicalCenters : medicalCenter[] = [];

  title = 'Create Center Admin';
  public user: User= new User() ; 
  public administrator: CenterAdministrator = new CenterAdministrator();


  selectedCenter: number = 0;

  message: string="";
  submitted = false;

  showMessage = false;

  constructor(private toastr: ToastrService,private centerAdministratorService: CenterAdministratorService, private route: ActivatedRoute,
    private router: Router, private authService: AuthService,private medicalCenterService: MedicalCenterServiceService) { }

  ngOnInit(): void {

    this.medicalCenterService.getAllCenters().subscribe(result => {
      this.medicalCenters = result;
    })
  }

  onSubmit() {

    this.administrator.medicalCenter = this.selectedCenter;

    if (
      !this.validateFirstName() ||
      !this.validateLastName() ||
      !this.validateJmbg() ||
      !this.validateEmail() ||
      //!this.validatePhoneNumber() ||
      !this.validateStreet() ||
      !this.validateStreetNumber() ||
      !this.validateCity() ||
      !this.validateCountry()
    ) {
      this.submitted = false;
      return
    }

    this.centerAdministratorService.createCenterAdmin(this.administrator).subscribe(
    
      {
        next: (res) => {
          //this.router.navigate(['/login']);
          // Swal.fire({
          //   icon: 'success',
          //   title: 'Success!',
          //   text: 'Sucessfully registered!',
          // })
          this.showSuccess();
          
        },
        error: (e) => {this.showError();
          this.submitted = false;
          console.log(e);}

    });
  }

  showSuccess() {
    //this.toastr.success('Successfully updated!', 'Blood Blank App');
    this.showMessage = true; 
    Swal.fire({
      icon: 'success',
      title: 'Success!',
      text: 'Sucessfully updated info!',
    })
  }

  showError() {
    //this.toastr.error('Check the fields again!', 'Warning');
    Swal.fire({
      icon: 'error',
      title: 'Oops...',
      text: 'Something went wrong.',
    }) 
  }

  validateFirstName() {
    if (this.administrator.name.length < 2) {
      this.message = "Your first name should contain at least 2 characters!";
      return false;
    } else if (this.administrator.name.match(/[!@#$%^&*.,:'<>+-/\\"]/g)) {
      this.message = "Your first name shouldn't contain special characters.";
      return false;
    } else if (this.administrator.name.match(/\d/g)) {
      this.message = "Your first name shouldn't contain numbers!";
      return false;
    } else if (!/^[A-Z][a-z]+$/.test(this.administrator.name)) {
      this.message = "Your first name needs to have one upper letter at the start!";
      return false;
    }
    return true;
  }

  validateLastName() {
    if (this.administrator.surname.length < 2) {
      this.message = "Your last name should contain at least 2 characters!";
      return false;
    } else if (this.administrator.surname.length > 36) {
      this.message = "Your last name shouldn't contain more than 36 characters!";
      return false;
    } else if (this.administrator.surname.match(/[!@#$%^&*.,:'<>+-/\\"]/g)) {
      this.message = "Your last name shouldn't contain special characters.";
      return false;
    } else if (this.administrator.surname.match(/\d/g)) {
      this.message = "Your last name shouldn't contain numbers!";
      return false;
    } else if (!/^[A-Z][a-z]+[ ]?[A-Z]*[a-z]*$/.test(this.administrator.surname)) {
      this.message = "Your last name needs to have one upper letter at the start!";
      return false;
    }
    return true;
  }
  validateStreet() {
    if (this.administrator.address.street.match(/[!@#$%^&*.,:'<>+-/\\"]/g)) {
      this.message = "Your street name shouldn't contain special characters.";
      return false;
    } else if (this.administrator.address.street.match(/\d/g)) {
      this.message = "Your street name shouldn't contain numbers!";
      return false;
    } else if (!/^[A-Z][a-z]+[ ]?[A-Z]*[a-z]*$/.test(this.administrator.address.street)) {
      this.message ="Your street name needs to have one upper letter at the start!";
      return false;
    }
    return true;
  }
  validateStreetNumber() {
    if (this.administrator.address.streetNumber.match(/[ ]/g)) {
      this.message = "Your street number shouldn't contain spaces!";
      return false;
    } else if (this.administrator.address.streetNumber.match(/[!@#$%^&*.,:'<>+/\\"]/g)) {
      this.message = "Your street number shouldn't contain special characters.";
      return false;
    } else if (this.administrator.address.streetNumber.length < 1) {
      this.message = "Your street number should contain at least one number.";
      return false;
    }
    return true;
  }
  validateCity() {
    if (this.administrator.address.city.match(/[!@#$%^&*.,:'<>+-/\\"]/g)) {
      this.message ="Your city name shouldn't contain special characters.";
      return false;
    } else if (this.administrator.address.city.match(/\d/g)) {
      this.message ="Your city name shouldn't contain numbers!";
      return false;
    } else if (!/^[A-Z][a-z]+[ ]?[A-Z]*[a-z]*$/.test(this.administrator.address.city)) {
      this.message ="Your city name needs to have one upper letter at the start!";
      return false;
    }
    return true;
  }
  validateCountry() {
    if (this.administrator.address.country.match(/[!@#$%^&*.,:'<>+-/\\"]/g)) {
      this.message ="Your country name shouldn't contain special characters.";
      return false;
    } else if (this.administrator.address.country.match(/\d/g)) {
      this.message ="Your country name shouldn't contain numbers!";
      return false;
    } else if (!/^[A-Z][a-z]+[ ]?[A-Z]*[a-z]*$/.test(this.administrator.address.country)) {
      this.message ="Your country name needs to have one upper letter at the start!";
      return false;
    }
    return true;
  }
  validatePhoneNumber() {
    if (this.administrator.phoneNumber.match(/[a-zA-Z]/g)) {
      this.message = "Your phone number shouldn't contain letters.";
      return false;
    } else if (this.administrator.phoneNumber.match(/[ ]/g)) {
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
    if (this.administrator.email == "") {
      this.message = "Please enter your email.";
      return false;
    } else if (
      !/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/.test(this.administrator.email)
    ) {
      this.message = "You have entered an invalid email!";
      return false;
    }
    return true;
  }
  validateJmbg() {
    if (this.administrator.jmbg.toString().match(/[a-zA-Z]/g)) {
      this.message = "Your jmbg shouldn't contain letters.";
      return false;
    } else if (this.administrator.jmbg.toString().match(/[ ]/g)) {
      this.message ="Your jmbg shouldn't contain spaces!";
      return false;
    } else if (this.administrator.jmbg < 13){
      this.message = "Your jmbg needs to contain 13 numbers!";
      return false;
    }
    return true;
  }

}
