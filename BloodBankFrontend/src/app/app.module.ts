import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

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
    QuestionnaireComponent
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
    ToastrModule.forRoot({
      timeOut: 3000,
      positionClass: 'toast-top-right',
    }),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
