import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { CenterAdministrator } from 'src/app/model/center-administrator.model';
import { User } from 'src/app/model/user.model';
import { AuthService } from 'src/app/service/auth.service';
import { CenterAdministratorService } from 'src/app/service/center-administrator.service';


@Component({
  selector: 'app-update-cent-administrator',
  templateUrl: './update-cent-administrator.component.html',
  styleUrls: ['./update-cent-administrator.component.css']
})
export class UpdateCentAdministratorComponent implements OnInit {
  title = 'Update your informations';
  public user: User= new User() ; 
  public administrator: CenterAdministrator | undefined = undefined;

  constructor(private toastr: ToastrService,private centerAdministratorService: CenterAdministratorService, private route: ActivatedRoute,
    private router: Router, private authService: AuthService) { }

  ngOnInit(): void {

    this.user = this.authService.getCurrentUser();

    if (this.user != null) {
      this.centerAdministratorService.findByEmail(this.user.email).subscribe(res => {
        this.administrator = res;
        console.log(this.administrator.address.city); 
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
