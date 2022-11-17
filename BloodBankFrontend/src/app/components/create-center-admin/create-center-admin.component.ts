import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { CenterAdministrator } from 'src/app/model/center-administrator.model';
import { User } from 'src/app/model/user.model';
import { AuthService } from 'src/app/service/auth.service';
import { CenterAdministratorService } from 'src/app/service/center-administrator.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-create-center-admin',
  templateUrl: './create-center-admin.component.html',
  styleUrls: ['./create-center-admin.component.css']
})
export class CreateCenterAdminComponent implements OnInit {

  public user: User= new User() ; 
  public administrator: CenterAdministrator = new CenterAdministrator();

  message: string="";
  submitted = false;

  showMessage = false;

  constructor(private toastr: ToastrService,private centerAdministratorService: CenterAdministratorService, private route: ActivatedRoute,
    private router: Router, private authService: AuthService) { }

  ngOnInit(): void {
  }

  onSubmit() {

    //this.medicalCenter.address.street = this.street;
    //this.medicalCenter.address.streetNumber = this.streetNumber;
    //this.medicalCenter.address.city = this.city;
    //this.medicalCenter.address.country = this.country;
    //console.log(this.street);
    this.centerAdministratorService.createCenterAdmin(this.administrator).subscribe(
    
      {
        next: (res) => {
          //this.router.navigate(['/login']);
          Swal.fire({
            icon: 'success',
            title: 'Success!',
            text: 'Sucessfully registered!',
          })   
         
          
        
        }

    });
  }

}
