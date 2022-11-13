import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { User } from 'src/app/model/user.model';
import { RegisterUserService } from 'src/app/service/register-user.service';

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css']
})
export class UpdateUserComponent implements OnInit {
  title = 'Update informations';

  public user: User | undefined = undefined;

  constructor(private toastr: ToastrService,private userService: RegisterUserService, private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    //let b = localStorage.getItem('registeredUser');
    let b = "1";
    if (b != null) {
      this.userService.getUserById(b).subscribe(res => {
        this.user = res;
      })
    }
  }

  public updateUser() : void {
    this.userService.updateUser(this.user).subscribe(res => {
      this.router.navigate(['/update-user']);
        //this.showSuccess();
    });
  }
  

}
