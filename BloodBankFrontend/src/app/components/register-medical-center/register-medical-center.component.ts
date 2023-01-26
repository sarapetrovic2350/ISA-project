import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { medicalCenter } from 'src/app/model/medicalCenter.model';
import { MedicalCenterServiceService } from 'src/app/service/medical-center.service.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-register-medical-center',
  templateUrl: './register-medical-center.component.html',
  styleUrls: ['./register-medical-center.component.css']
})
export class RegisterMedicalCenterComponent implements OnInit {

  title = 'Create New Medical Center';
  medicalCenter = new medicalCenter();
  name:string =''
  street: string = "";
  streetNumber: string = "";
  city: string= "";
  country: string="";
  description:string =''
  averageGrade:number=0
  image: string = ''
  submitted = false;
  message: string="";

  constructor(
    private router: Router,
    private medicalCenterService: MedicalCenterServiceService
  ) { }

  ngOnInit(): void {
  }

  onSubmit() {

    if (
      !this.validateFirstName() ||
      !this.validateStreet() ||
      !this.validateStreetNumber() ||
      !this.validateCity() ||
      !this.validateCountry() ||
      !this.validateDescription() ||
      !this. validateAverageGrade()
    ) {
      this.submitted = false;
      return
    }

    //this.medicalCenter.address.street = this.street;
    //this.medicalCenter.address.streetNumber = this.streetNumber;
    //this.medicalCenter.address.city = this.city;
    //this.medicalCenter.address.country = this.country;
    //console.log(this.street);
    this.medicalCenterService.createMedicalCenter(this.medicalCenter).subscribe(
    
      {
        next: (res) => {
          this.showSuccess();
      },
      error: (e) => {this.showError();
        console.log(e);}

    });
  }

  showSuccess() {
    // this.toastr.success('Successfully updated!', 'Blood Blank App');
    this.submitted = false;
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
     if (this.medicalCenter.name.length < 2) {
       this.message = "The name should contain at least 2 characters!";
       return false;
     }
     return true;
   }
 
   validateDescription() {
     if (this.medicalCenter.description.length < 10) {
       this.message = "The name should contain at least 10 characters!";
       return false;
     } 
     return true;
   }
 
   validateAverageGrade() {
     if(this.medicalCenter.averageGrade > 5.1){
       this.message = "The average grade is between 0 and 5.";
       return false;
     }else if (this.medicalCenter.averageGrade.toString().match(/[a-zA-Z]/g)) {
       this.message = "The average grade shouldn't contain letters.";
       return false;
     } else if (this.medicalCenter.averageGrade.toString().match(/[ ]/g)) {
       this.message ="Your average grade shouldn't contain spaces!";
       return false;
     } else if (this.medicalCenter.averageGrade < 4){
       this.message = "Your average grade needs to contain 4 numbers!";
       return false;
     }
     return true;
   }
 
   validateStreet() {
     if (this.medicalCenter.address.street.match(/[!@#$%^&*.,:'<>+-/\\"]/g)) {
       this.message = "Your street name shouldn't contain special characters.";
       return false;
     } else if (this.medicalCenter.address.street.match(/\d/g)) {
       this.message = "Your street name shouldn't contain numbers!";
       return false;
     } else if (!/^[A-Z][a-z]+[ ]?[A-Z]*[a-z]*$/.test(this.medicalCenter.address.street)) {
       this.message ="Your street name needs to have one upper letter at the start!";
       return false;
     }
     return true;
   }
   validateStreetNumber() {
     if (this.medicalCenter.address.streetNumber.match(/[ ]/g)) {
       this.message = "Your street number shouldn't contain spaces!";
       return false;
     } else if (this.medicalCenter.address.streetNumber.match(/[!@#$%^&*.,:'<>+/\\"]/g)) {
       this.message = "Your street number shouldn't contain special characters.";
       return false;
     } else if (this.medicalCenter.address.streetNumber.length < 1) {
       this.message = "Your street number should contain at least one number.";
       return false;
     }
     return true;
   }
   validateCity() {
     if (this.medicalCenter.address.city.match(/[!@#$%^&*.,:'<>+-/\\"]/g)) {
       this.message ="Your city name shouldn't contain special characters.";
       return false;
     } else if (this.medicalCenter.address.city.match(/\d/g)) {
       this.message ="Your city name shouldn't contain numbers!";
       return false;
     } else if (!/^[A-Z][a-z]+[ ]?[A-Z]*[a-z]*$/.test(this.medicalCenter.address.city)) {
       this.message ="Your city name needs to have one upper letter at the start!";
       return false;
     }
     return true;
   }
   validateCountry() {
     if (this.medicalCenter.address.country.match(/[!@#$%^&*.,:'<>+-/\\"]/g)) {
       this.message ="Your country name shouldn't contain special characters.";
       return false;
     } else if (this.medicalCenter.address.country.match(/\d/g)) {
       this.message ="Your country name shouldn't contain numbers!";
       return false;
     } else if (!/^[A-Z][a-z]+[ ]?[A-Z]*[a-z]*$/.test(this.medicalCenter.address.country)) {
       this.message ="Your country name needs to have one upper letter at the start!";
       return false;
     }
     return true;
   }
}
