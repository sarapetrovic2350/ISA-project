import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserRegistrationComponent } from './components/user-registration/user-registration.component';
import { LoginComponent } from './components/login/login.component';
import { UpdateUserComponent } from './components/update-user/update-user.component';
import { UpdateMedicalCenntarComponent } from './components/update-medical-cenntar/update-medical-cenntar.component';
import { UpdateCentAdministratorComponent } from './components/update-cent-administrator/update-cent-administrator.component';
import { MedicalCentersComponent } from './components/medical-centers/medical-centers.component';
import { HomeComponent } from './pages/home/home.component';

const routes: Routes = [
  {
    path:'',
    component: HomeComponent
  },
  {
    path: 'user-registration',
    component: UserRegistrationComponent
  }, 
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'update-user',
    component: UpdateUserComponent
  },
  {
    path: 'update-medical-cenntar',
    component: UpdateMedicalCenntarComponent
  },
  {
    path: 'update-cent-administrator',
    component: UpdateCentAdministratorComponent
  },
  {
    path: 'medical-centers',
    component: MedicalCentersComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
