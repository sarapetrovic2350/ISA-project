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
import { CreateSystemAdminComponent } from './components/create-system-admin/create-system-admin.component';
import { QuestionnaireComponent } from './components/questionnaire/questionnaire.component';
import { PredefinedAppointmentComponent } from './components/predefined-appointment/predefined-appointment.component';
import { ConfirmRegistrationComponent } from './components/confirm-registration/confirm-registration.component';
import {CreateReportComponent} from './components/create-report/create-report.component';
import { PredefinedAppointmentsForCenterComponent } from './components/predefined-appointments-for-center/predefined-appointments-for-center.component';
import { RegisteredUserAppointmentComponent } from './components/registered-user-appointment/registered-user-appointment.component';
import { ScheduledAppointmentsComponent } from './components/scheduled-appointments/scheduled-appointments.component';
import { RoleGuardService } from './service/role-guard.service';
import { ForbiddenComponent } from './components/forbidden/forbidden.component';
import { ShowBloodComponent } from './components/show-blood/show-blood.component'; 
import { HistoryReportComponent } from './components/history-report/history-report.component';
import { HistoryOfVisitsComponent } from './components/history-of-visits/history-of-visits.component';

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
    component: UpdateUserComponent,
    canActivate: [RoleGuardService],
    data: {
      expectedRole: 'ROLE_REGISTERED_USER'
    }
  },
  {
    path: 'update-medical-cenntar',
    component: UpdateMedicalCenntarComponent,
    canActivate: [RoleGuardService],
    data: {
      expectedRole: 'ROLE_CENTER_ADMINISTRATOR'
    }
  },
  {
    path: 'update-cent-administrator',
    component: UpdateCentAdministratorComponent,
    canActivate: [RoleGuardService],
    data: {
      expectedRole: 'ROLE_CENTER_ADMINISTRATOR'
    }
  },
  {
    path: 'medical-centers',
    component: MedicalCentersComponent
  },
  {
    path: 'show-blood',
    component: ShowBloodComponent, 
    canActivate: [RoleGuardService],
    data: {
      expectedRole: 'ROLE_CENTER_ADMINISTRATOR'
    }
  },
  {
    path: 'show-users',
    component: ShowUsersComponent
  },
  {
    path: 'create-medical-cenntar',
    component: RegisterMedicalCenterComponent,
    canActivate: [RoleGuardService],
    data: {
      expectedRole: 'ROLE_SYSTEM_ADMINISTRATOR'
    }
  }, 
  {
    path: 'change-password-page',
    component: ChangePasswordPageComponent
  }, 
  {
    path: 'create-center-admin',
    component: CreateCenterAdminComponent,
    canActivate: [RoleGuardService],
    data: {
      expectedRole: 'ROLE_SYSTEM_ADMINISTRATOR'
    }
  },
  {
    path: 'questionnaire',
    component: QuestionnaireComponent,
    canActivate: [RoleGuardService],
    data: {
      expectedRole: 'ROLE_REGISTERED_USER'
    }
  },
  {
    path: 'predefined-appointment',
    component: PredefinedAppointmentComponent,
    canActivate: [RoleGuardService],
    data: {
      expectedRole: 'ROLE_CENTER_ADMINISTRATOR'
    }
  }, 
  {
    path: 'confirm-registration/:id',
    component: ConfirmRegistrationComponent
  },
  {
    path: 'create-system-admin',
    component: CreateSystemAdminComponent,
    canActivate: [RoleGuardService],
    data: {
      expectedRole: 'ROLE_SYSTEM_ADMINISTRATOR'
    }
  },
  {
    path: 'predefined-appointments/:id',
    component: PredefinedAppointmentsForCenterComponent,
    canActivate: [RoleGuardService],
    data: {
      expectedRole: 'ROLE_REGISTERED_USER'
    }
  },
  {
    path: 'registered-user-appointment',
    component: RegisteredUserAppointmentComponent,
    canActivate: [RoleGuardService],
    data: {
      expectedRole: 'ROLE_REGISTERED_USER'
    }
  },
  {
    path: 'scheduled-appointments',
    component: ScheduledAppointmentsComponent,
    canActivate: [RoleGuardService],
    data: {
      expectedRole: 'ROLE_REGISTERED_USER'
    }
  },
  {
    path:'report/:id/search', 
    component: CreateReportComponent, 
    canActivate: [RoleGuardService],
    data: {
      expectedRole: 'ROLE_CENTER_ADMINISTRATOR'
    }
  },
  {
    path:'create-report', 
    component: CreateReportComponent, 
    canActivate: [RoleGuardService],
    data: {
      expectedRole: 'ROLE_CENTER_ADMINISTRATOR'
    }
  },
  {
    path: 'history-of-visits',
    component: HistoryOfVisitsComponent,
    canActivate: [RoleGuardService],
    data: {
      expectedRole: 'ROLE_REGISTERED_USER'
    }
  }, 
  {
    path: 'forbidden',
    component: ForbiddenComponent
  }, 
  {
    path: 'history-report',
    component: HistoryReportComponent,
    canActivate: [RoleGuardService],
    data: {
      expectedRole: 'ROLE_CENTER_ADMINISTRATOR'
    }
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
