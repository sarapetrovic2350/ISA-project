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
  title = 'Update informations';

  public user: User | undefined = undefined;

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
          Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Check the fields again!',
          })  
      }
      });
  }
  
  showSuccess() {
    this.toastr.success('Successfully updated!', 'Blood Blank App');
  }

  showError() {
    this.toastr.error('Check the fields again!', 'Warning');
  }

}
