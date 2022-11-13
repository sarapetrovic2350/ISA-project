import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { medicalCenter } from 'src/app/model/medicalCenter.model';
import { MedicalCenterServiceService } from 'src/app/service/medical-center.service.service';

@Component({
  selector: 'app-update-medical-cenntar',
  templateUrl: './update-medical-cenntar.component.html',
  styleUrls: ['./update-medical-cenntar.component.css']
})

export class UpdateMedicalCenntarComponent implements OnInit {

  title = 'Update Medical Center';

  public medCenter: medicalCenter | undefined = undefined;

  constructor(private toastr: ToastrService,private medicalCenterService: MedicalCenterServiceService, private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    let b = "2";
    if (b != null) {
      this.medicalCenterService.getMedicalCenterById(b).subscribe(res => {
        this.medCenter = res;
        console.log(this.medCenter.address);
      })
    }
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
