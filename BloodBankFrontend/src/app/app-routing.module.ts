import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserRegistrationComponent } from './components/user-registration/user-registration.component';
import { LoginComponent } from './components/login/login.component';
import { UpdateUserComponent } from './components/update-user/update-user.component';
import { UpdateMedicalCenntarComponent } from './components/update-medical-cenntar/update-medical-cenntar.component';
import { UpdateCentAdministratorComponent } from './components/update-cent-administrator/update-cent-administrator.component';
import { MedicalCentersComponent } from './components/medical-centers/medical-centers.component';
import { HomeComponent } from './pages/home/home.component';
import { ShowUsersComponent } from './components/show-users/show-users.component';
import { RegisterMedicalCenterComponent } from './components/register-medical-center/register-medical-center.component';
import { ChangePasswordPageComponent } from './components/change-password-page/change-password-page.component';
import { CreateCenterAdminComponent } from './components/create-center-admin/create-center-admin.component';
import { QuestionnaireComponent } from './components/questionnaire/questionnaire.component';

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
  },
  {
    path: 'show-users',
    component: ShowUsersComponent
  },
  {
    path: 'create-medical-cenntar',
    component: RegisterMedicalCenterComponent
  }, 
  {
    path: 'change-password-page',
    component: ChangePasswordPageComponent
  }, 
  {
    path: 'create-center-admin',
    component: CreateCenterAdminComponent
  },
  {
    path: 'questionnaire',
    component: QuestionnaireComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
