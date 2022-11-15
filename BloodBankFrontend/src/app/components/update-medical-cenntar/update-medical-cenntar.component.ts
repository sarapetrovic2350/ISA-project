import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { medicalCenter } from 'src/app/model/medicalCenter.model';
import { MedicalCenterServiceService } from 'src/app/service/medical-center.service.service';
import { CenterAdministrator } from 'src/app/model/center-administrator.model';
import { User } from 'src/app/model/user.model';
import { AuthService } from 'src/app/service/auth.service';
import { CenterAdministratorService } from 'src/app/service/center-administrator.service';

@Component({
  selector: 'app-update-medical-cenntar',
  templateUrl: './update-medical-cenntar.component.html',
  styleUrls: ['./update-medical-cenntar.component.css']
})

export class UpdateMedicalCenntarComponent implements OnInit {

  title = 'Update Medical Center';

  public medCenter: medicalCenter | undefined = undefined;
  public user: User= new User() ; 
  public administrator: CenterAdministrator | undefined = undefined;

  constructor(private toastr: ToastrService,private medicalCenterService: MedicalCenterServiceService, private route: ActivatedRoute,
    private router: Router, private centerAdministratorService: CenterAdministratorService, 
    private authService: AuthService) { }

  ngOnInit(): void {
    let b = "2";
    this.user = this.authService.getCurrentUser();

    if (this.user != null) {
      this.centerAdministratorService.findByEmail(this.user.email).subscribe(res => {
        this.administrator = res;
        if (this.administrator != null) {
          this.centerAdministratorService.findCenterByAdminEmail(String(this.administrator.email)).subscribe(res => {
            this.medCenter = res;
            console.log(this.medCenter);
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
    
    this.medicalCenterService.updateMedicalCenter(this.medCenter).subscribe(
      {next: (res) => {
      //this.router.navigate(['/update-user']);
      this.showSuccess();
    },
    error: (e) => {this.showError();
      console.log(e);}
    });
  }

  showSuccess() {
    this.toastr.success('Successfully updated!', 'Blood Blank App');
  }

  showError() {
    this.toastr.error('Check the fields again!', 'Warning');
  }
}
