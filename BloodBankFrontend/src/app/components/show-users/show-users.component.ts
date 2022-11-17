import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user.model';
import { ShowUsersService } from 'src/app/service/show-users.service';

@Component({
  selector: 'app-show-users',
  templateUrl: './show-users.component.html',
  styleUrls: ['./show-users.component.css']
})
export class ShowUsersComponent implements OnInit {

  allUsers: User[] = [];
  constructor(private showUsersService: ShowUsersService) { }

  ngOnInit(): void {
    
    let user = localStorage.getItem('currentUser');
    let role = localStorage.getItem('role');
    if(role == "ROLE_CENTER_ADMINISTRATOR") {
      this.showUsersService.getAllRegistredUsers().subscribe((data: any) => {
        this.allUsers = data;
    
        })
    }
    if(role == "ROLE_SYSTEM_ADMINISTRATOR") {
      this.showUsersService.getAll().subscribe((data: any) => {
      this.allUsers = data;
        })
    }

    
  }

}
