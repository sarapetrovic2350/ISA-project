import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { medicalCenter } from 'src/app/model/medicalCenter.model';
import { MedicalCenterServiceService } from 'src/app/service/medical-center.service.service';
import { CenterAdministrator } from 'src/app/model/center-administrator.model';
import { User } from 'src/app/model/user.model';
import { AuthService } from 'src/app/service/auth.service';
import { CenterAdministratorService } from 'src/app/service/center-administrator.service';
import { MatTableDataSource } from '@angular/material/table';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-update-medical-cenntar',
  templateUrl: './update-medical-cenntar.component.html',
  styleUrls: ['./update-medical-cenntar.component.css']
})

export class UpdateMedicalCenntarComponent implements OnInit {

  title = 'Update Medical Center';

  public medCenter: medicalCenter = new medicalCenter();
  public user: User= new User() ; 
  public administrator: CenterAdministrator | undefined = undefined;
  public dataSource = new MatTableDataSource<CenterAdministrator>();
  public centerAdministrators: CenterAdministrator[] = [];

  message: string="";
  submitted = false;

  public displayedColumns = ['Name', 'Surname'];

  constructor(private toastr: ToastrService,private medicalCenterService: MedicalCenterServiceService, private route: ActivatedRoute,
    private router: Router, private centerAdministratorService: CenterAdministratorService, 
    private authService: AuthService) { }

  ngOnInit(): void {
    this.user = this.authService.getCurrentUser();

    if (this.user != null) {
      this.centerAdministratorService.findByEmail(this.user.email).subscribe(res => {
        this.administrator = res;

        if (this.administrator != null) {
          this.centerAdministratorService.findCenterByAdminEmail(String(this.administrator.email)).subscribe(res => {
            this.medCenter = res;
            console.log(this.medCenter);

            if (this.medCenter != null) {
              this.centerAdministratorService.findAdministratorsByCenterId(String(this.medCenter.centerId)).subscribe((data: any) => {
                this.centerAdministrators = data;
                this.dataSource.data = this.centerAdministrators;
              })
            }

          })
        }
        
      })
    }

    // if (this.administrator != null) {
    //   this.centerAdministratorService.findCenterByAdminEmail(String(this.administrator.email)).subscribe(res => {
    //     this.medCenter = res;
    //     console.log(this.medCenter);
    //   })
    // }
  }

  public updateMedicalCenter(){
    
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

    this.medicalCenterService.updateMedicalCenter(this.medCenter).subscribe(
      {next: (res) => {
      //this.router.navigate(['/update-user']);
      this.showSuccess();
    },
    error: (e) => {this.showError();
      console.log(e);}
    });
  }

  public gotoBloods(){
    this.router.navigate(['/show-blood']);
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
    if (this.medCenter.name.length < 2) {
      this.message = "The name should contain at least 2 characters!";
      return false;
    }
    return true;
  }

  validateDescription() {
    if (this.medCenter.description.length < 10) {
      this.message = "The name should contain at least 10 characters!";
      return false;
    } 
    return true;
  }

  validateAverageGrade() {
    if(this.medCenter.averageGrade > 5.1){
      this.message = "The average grade is between 0 and 5.";
      return false;
    }else if (this.medCenter.averageGrade.toString().match(/[a-zA-Z]/g)) {
      this.message = "The average grade shouldn't contain letters.";
      return false;
    } else if (this.medCenter.averageGrade.toString().match(/[ ]/g)) {
      this.message ="Your average grade shouldn't contain spaces!";
      return false;
    } else if (this.medCenter.averageGrade < 4){
      this.message = "Your average grade needs to contain 4 numbers!";
      return false;
    }
    return true;
  }

  validateStreet() {
    if (this.medCenter.address.street.match(/[!@#$%^&*.,:'<>+-/\\"]/g)) {
      this.message = "Your street name shouldn't contain special characters.";
      return false;
    } else if (this.medCenter.address.street.match(/\d/g)) {
      this.message = "Your street name shouldn't contain numbers!";
      return false;
    } else if (!/^[A-Z][a-z]+[ ]?[A-Z]*[a-z]*$/.test(this.medCenter.address.street)) {
      this.message ="Your street name needs to have one upper letter at the start!";
      return false;
    }
    return true;
  }
  validateStreetNumber() {
    if (this.medCenter.address.streetNumber.match(/[ ]/g)) {
      this.message = "Your street number shouldn't contain spaces!";
      return false;
    } else if (this.medCenter.address.streetNumber.match(/[!@#$%^&*.,:'<>+/\\"]/g)) {
      this.message = "Your street number shouldn't contain special characters.";
      return false;
    } else if (this.medCenter.address.streetNumber.length < 1) {
      this.message = "Your street number should contain at least one number.";
      return false;
    }
    return true;
  }
  validateCity() {
    if (this.medCenter.address.city.match(/[!@#$%^&*.,:'<>+-/\\"]/g)) {
      this.message ="Your city name shouldn't contain special characters.";
      return false;
    } else if (this.medCenter.address.city.match(/\d/g)) {
      this.message ="Your city name shouldn't contain numbers!";
      return false;
    } else if (!/^[A-Z][a-z]+[ ]?[A-Z]*[a-z]*$/.test(this.medCenter.address.city)) {
      this.message ="Your city name needs to have one upper letter at the start!";
      return false;
    }
    return true;
  }
  validateCountry() {
    if (this.medCenter.address.country.match(/[!@#$%^&*.,:'<>+-/\\"]/g)) {
      this.message ="Your country name shouldn't contain special characters.";
      return false;
    } else if (this.medCenter.address.country.match(/\d/g)) {
      this.message ="Your country name shouldn't contain numbers!";
      return false;
    } else if (!/^[A-Z][a-z]+[ ]?[A-Z]*[a-z]*$/.test(this.medCenter.address.country)) {
      this.message ="Your country name needs to have one upper letter at the start!";
      return false;
    }
    return true;
  }
}
