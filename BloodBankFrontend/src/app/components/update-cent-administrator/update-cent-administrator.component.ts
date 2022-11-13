import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { CenterAdministrator } from 'src/app/model/center-administrator.model';
import { CenterAdministratorService } from 'src/app/service/center-administrator.service';

@Component({
  selector: 'app-update-cent-administrator',
  templateUrl: './update-cent-administrator.component.html',
  styleUrls: ['./update-cent-administrator.component.css']
})
export class UpdateCentAdministratorComponent implements OnInit {
  title = 'Update your informations';

  public administrator: CenterAdministrator | undefined = undefined;

  constructor(private toastr: ToastrService,private centerAdministratorService: CenterAdministratorService, private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    let b = "1";
    if (b != null) {
      this.centerAdministratorService.getCenterAdministratorById(b).subscribe(res => {
        this.administrator = res;
      })
    }
  }

  public updateCenterAdministrator(){
    
    this.centerAdministratorService.updateCenterAdministrator(this.administrator).subscribe(
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
