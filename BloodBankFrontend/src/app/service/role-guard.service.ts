import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { AuthGuardService } from './auth-guard.service';

@Injectable({
  providedIn: 'root'
})
export class RoleGuardService {

  constructor(public authGuardService: AuthGuardService, private router: Router) { }
  canActivate(route: ActivatedRouteSnapshot): boolean {
    const expectedRole = route.data['expectedRole'];
    let currentRole;
    currentRole = localStorage.getItem('role')
    console.log("rola je " + currentRole)
    
    if (!this.authGuardService.getToken() || !currentRole!.includes(expectedRole)) {
      this.router.navigate(['/forbidden']);
      return false;
    }
    return true;
  }
}
