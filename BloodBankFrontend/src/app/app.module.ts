import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { JwtHelperService, JWT_OPTIONS  } from '@auth0/angular-jwt';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AngularMaterialModule } from './angular-material/angular-material.module';
import { FormsModule, ReactiveFormsModule} from '@angular/forms'
import { UserRegistrationComponent } from './components/user-registration/user-registration.component';
import { LoginComponent } from './components/login/login.component';
import { ToastrModule } from 'ngx-toastr';
import { HttpClientModule } from '@angular/common/http';
import { UpdateUserComponent } from './components/update-user/update-user.component';
import { UpdateMedicalCenntarComponent } from './components/update-medical-cenntar/update-medical-cenntar.component';
import { UpdateCentAdministratorComponent } from './components/update-cent-administrator/update-cent-administrator.component';
import { HeaderComponent } from './components/header/header.component';
import { MedicalCentersComponent } from './components/medical-centers/medical-centers.component';
import { HomeComponent } from './pages/home/home.component';
import { ShowUsersComponent } from './components/show-users/show-users.component';
import { RegisterMedicalCenterComponent } from './components/register-medical-center/register-medical-center.component';
import { ChangePasswordPageComponent } from './components/change-password-page/change-password-page.component';
import { CreateCenterAdminComponent } from './components/create-center-admin/create-center-admin.component';
import { QuestionnaireComponent } from './components/questionnaire/questionnaire.component';
import { NgxPaginationModule } from 'ngx-pagination';
import {MatSelectModule} from '@angular/material/select';
import { PredefinedAppointmentComponent } from './components/predefined-appointment/predefined-appointment.component';
import { CreateReportComponent } from './components/create-report/create-report.component';
import { ConfirmRegistrationComponent } from './components/confirm-registration/confirm-registration.component';
import { CreateSystemAdminComponent } from './components/create-system-admin/create-system-admin.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatStepperModule } from '@angular/material/stepper';
import { PredefinedAppointmentsForCenterComponent } from './components/predefined-appointments-for-center/predefined-appointments-for-center.component';
import { RegisteredUserAppointmentComponent } from './components/registered-user-appointment/registered-user-appointment.component';
import { ScheduledAppointmentsComponent } from './components/scheduled-appointments/scheduled-appointments.component';
import {AuthService} from './service/auth.service';

import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { TokenInterceptor } from './interceptor/TokenInterceptor';
import { AuthGuardService } from './service/auth-guard.service';
import { ForbiddenComponent } from './components/forbidden/forbidden.component';
import { ShowBloodComponent } from './components/show-blood/show-blood.component';
import { HistoryReportComponent } from './components/history-report/history-report.component'
import { HistoryOfVisitsComponent } from './components/history-of-visits/history-of-visits.component';
import { ShowAppointmentsComponent } from './components/show-appointments/show-appointments.component';
import { CalendarAppointmentsComponent } from './components/calendar-appointments/calendar-appointments.component';
import { ScheduleModule, RecurrenceEditorModule, DayService, WeekService, MonthService } from '@syncfusion/ej2-angular-schedule'



@NgModule({
  declarations: [
    AppComponent,
    UserRegistrationComponent,
    LoginComponent,
    UpdateUserComponent,
    UpdateMedicalCenntarComponent,
    UpdateCentAdministratorComponent,
    HeaderComponent,
    MedicalCentersComponent,
    HomeComponent,
    ShowUsersComponent,
    RegisterMedicalCenterComponent,
    ChangePasswordPageComponent,
    CreateCenterAdminComponent,
    QuestionnaireComponent,
    PredefinedAppointmentComponent,
    CreateReportComponent,
    ConfirmRegistrationComponent,
    PredefinedAppointmentsForCenterComponent,
    RegisteredUserAppointmentComponent,
    CreateSystemAdminComponent,
    PredefinedAppointmentsForCenterComponent,
    ScheduledAppointmentsComponent,
    ForbiddenComponent,
    ShowBloodComponent,
    HistoryReportComponent,
    HistoryOfVisitsComponent,
    ShowAppointmentsComponent,
    CalendarAppointmentsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    AngularMaterialModule,
    FormsModule,
    NgxPaginationModule,
    HttpClientModule,
    ReactiveFormsModule,
    MatSelectModule,
    MatFormFieldModule,
    MatInputModule,
    MatStepperModule,
    ToastrModule.forRoot({
      timeOut: 3000,
      positionClass: 'toast-top-right',
    }),
    ScheduleModule, RecurrenceEditorModule,
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    },
    AuthService,
    AuthGuardService,
    { provide: JWT_OPTIONS, useValue: JWT_OPTIONS },
    JwtHelperService,
    DayService, WeekService, MonthService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
