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

  name: string = "";
  surname: string = "";
  isSearched: Boolean = false;


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

  public searchUser(): void {

    let role = localStorage.getItem('role');
    if(role == "ROLE_SYSTEM_ADMINISTRATOR"){
      const name = this.name;
      const surname = this.surname;
      this.showUsersService.searchUsersForSystemAdmin(name, surname).subscribe((data: any) => {
      this.users = data;
      console.log(data);
      console.log(this.users);
      this.isSearched = true;
    })

    }else{
      const name = this.name;
      const surname = this.surname;
      this.showUsersService.searchUsersForCenterAdmin(name, surname).subscribe((data: any) => {
      this.users = data;
      console.log(data);
      console.log(this.users);
      this.isSearched = true;
    })

  };
}
}
