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
  users: User[]= [];
  filteredString : string='';
  constructor(private showUsersService: ShowUsersService) { }

  ngOnInit(): void {
    
    let user = localStorage.getItem('currentUser');
    let role = localStorage.getItem('role');
    if(role == "ROLE_CENTER_ADMINISTRATOR") {
      this.showUsersService.getAllRegistredUsers().subscribe((data: any) => {
        this.allUsers = data;
        this.users = data;
    
        })
    }
    if(role == "ROLE_SYSTEM_ADMINISTRATOR") {
      this.showUsersService.getAll().subscribe((data: any) => {
      this.allUsers = data;
      this.users = data;
        })
    }

    
  }

  search(filterString : string){
    let filteredUsers : User[] = [];
    filteredUsers = this.transform(this.allUsers, this.filteredString);
    if(filteredUsers.length!==0){
      this.users = filteredUsers;
    }
    else{
      this.users = [];
    }
  }

  cancelSearch(){
    this.users = this.allUsers;
    this.filteredString='';
  }

  transform(users : User[], filteredString : string){
    return users.filter((user)=>
    {
      return user.name.toLocaleLowerCase().includes(filteredString.toLocaleLowerCase()) || user.surname.toLocaleLowerCase().includes(filteredString.toLocaleLowerCase());
    })
  }

}
