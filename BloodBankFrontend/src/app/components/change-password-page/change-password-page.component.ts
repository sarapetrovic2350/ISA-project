import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { CenterAdministrator } from 'src/app/model/center-administrator.model';
import { User } from 'src/app/model/user.model';
import { ChangePasswordDTO } from 'src/app/model/change-password-dto.model';
import { AuthService } from 'src/app/service/auth.service';
import { CenterAdministratorService } from 'src/app/service/center-administrator.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-change-password-page',
  templateUrl: './change-password-page.component.html',
  styleUrls: ['./change-password-page.component.css']
})
export class ChangePasswordPageComponent implements OnInit {
  title = 'Update your Password';
  public user: User= new User() ; 
  public administrator: CenterAdministrator | undefined = undefined;
  public newPassword: ChangePasswordDTO = new ChangePasswordDTO();

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

  public changePassword(){

    this.centerAdministratorService.changePassword(this.newPassword).subscribe(
      {next: (res) => {
      //this.router.navigate(['/update-user']);
      this.showSuccess();
    },
    error: (e) => {this.showError();
      console.log(e);}
    });

  }

  showSuccess() {
    //this.toastr.success('Successfully updated!', 'Blood Blank App');
    Swal.fire({
      icon: 'success',
      title: 'Success!',
      text: 'Sucessfully changed your password!',
    })
  }

  showError() {
    //this.toastr.error('Check the fields again!', 'Warning');
    Swal.fire({
      icon: 'error',
      title: 'Oops...',
      text: 'Something went wrong',
    }) 
  }

}
