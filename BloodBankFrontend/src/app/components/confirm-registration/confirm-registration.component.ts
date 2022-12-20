import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/service/auth.service';
import { ActivatedRoute, Router, withRouterConfig } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-confirm-registration',
  templateUrl: './confirm-registration.component.html',
  styleUrls: ['./confirm-registration.component.css']
})
export class ConfirmRegistrationComponent implements OnInit {

  title = 'Account activation';
  confirmationToken: string = '';
  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
    let href = window.location.href;
    let paths = [];
    paths = href.split("/");
    this.confirmationToken = paths[4];
    console.log(this.confirmationToken);
    this.authService.activateAccount(this.confirmationToken).subscribe(
      {
        next: (res) => {
          this.router.navigate(['/login']);
          Swal.fire({
            icon: 'success',
            title: 'Success!',
            text: 'You have sucessfully activated your account! You can now log in.',
          })
        },
        error: (e) => {
          console.log(e);
          Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Your token is invalid or expired!',
          })
        }
      });
  }

}
