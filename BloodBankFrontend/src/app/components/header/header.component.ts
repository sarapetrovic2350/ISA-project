import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {



  constructor(private authService: AuthService) { }
  isLoggedIn: boolean = false;
  isCenterAdministrator: boolean = false;
  isRegisteredUser: boolean = false;
  isCenterOrSystemAdmin: boolean = false;
  isSystemAdmin: boolean = false;
  isHeadSystemAdmin: boolean = false;

  ngOnInit(): void {
    this.refreshUser();
  }

  logout() {
    this.authService.logout();
  }
  
  refreshUser(): void {
    let user = localStorage.getItem('currentUser');
    let role = localStorage.getItem('role');
    this.authService.getCurrentUser()
    if(user === null ){
      this.isLoggedIn = false;
    } else{
      this.isLoggedIn = true;
    }
    if(role == "ROLE_CENTER_ADMINISTRATOR") {
      this.isCenterAdministrator = true;
    }
    if(this.authService.getCurrentUser().name === "Vuk") {
      this.isHeadSystemAdmin = true;
    }
    if(role == "ROLE_REGISTERED_USER"){
      this.isRegisteredUser = true;
    }
    if(role == "ROLE_CENTER_ADMINISTRATOR"|| role == "ROLE_SYSTEM_ADMINISTRATOR"){
      this.isCenterOrSystemAdmin = true;
    }
    if(role == "ROLE_SYSTEM_ADMINISTRATOR"){
      this.isSystemAdmin = true;
    }
  }

}
