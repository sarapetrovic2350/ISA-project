import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from "@angular/material/table";
import { ActivatedRoute, Params, Router } from '@angular/router';
import { User } from 'src/app/model/user.model';
import { ReportService } from 'src/app/service/report.service';
import { CenterAdministrator } from 'src/app/model/center-administrator.model';
import { AuthService } from 'src/app/service/auth.service';
import { CenterAdministratorService } from 'src/app/service/center-administrator.service';
import { medicalCenter } from 'src/app/model/medicalCenter.model';
import { ShowReport } from 'src/app/model/show-report.model';

@Component({
  selector: 'app-history-report',
  templateUrl: './history-report.component.html',
  styleUrls: ['./history-report.component.css']
})
export class HistoryReportComponent implements OnInit {

  public dataSource = new MatTableDataSource<ShowReport>();
  public displayedColumns = ['Date', 'Patient Name', 'Patient Surname','Blood Type', 'Quantaty'];
  public reports: ShowReport[] = [];
  public report: ShowReport | undefined = undefined;
  public user = new User();
  public medCenter: medicalCenter = new medicalCenter();
  public administrator: CenterAdministrator | undefined = undefined;
  public centerAdministrators: CenterAdministrator[] = [];
  public selectedOption: string = "";

  constructor(private reportService: ReportService,private centerAdministratorService: CenterAdministratorService, private router: Router, private route: ActivatedRoute, private authService: AuthService) { }

  ngOnInit(): void {

    this.user = this.authService.getCurrentUser();
      this.centerAdministratorService.findByEmail(this.user.email).subscribe(res => {
        this.administrator = res;
        //console.log(this.administrator); 
        
          this.centerAdministratorService.findCenterByAdminEmail(String(this.administrator.email)).subscribe(res => {
            this.medCenter = res;
            //console.log(this.medCenter);

              this.reportService.getAllByMedicalCenterId(this.medCenter.centerId).subscribe((data: any) => {  
              
              this.reports = data;
              console.log(this.reports);     
              this.dataSource.data = this.reports;
            })
            

          })
        
        
      })
    

  }

  changeSorting() {
    console.log(this.selectedOption);
    if (this.selectedOption == '1') {
      this.reports.sort((a, b) => new Date(a.date).getTime() - new Date(b.date).getTime());
      return this.dataSource.data = this.reports;
    }
    else if (this.selectedOption == '3') {
      this.reports.sort((a, b) => new Date(b.date).getTime() - new Date(a.date).getTime());
      return this.dataSource.data = this.reports;
    
    } else if (this.selectedOption == '2') {
      this.reports.sort((a, b) => a.patientName.localeCompare(b.patientName));
      return this.dataSource.data = this.reports;
    }
    else if (this.selectedOption == '4') {
      this.reports.sort((a, b) => b.patientName.localeCompare(a.patientName));
      return this.dataSource.data = this.reports;
    }else if(this.selectedOption == '5'){
      this.reports.sort((a, b) => a.patientSurname.localeCompare(b.patientSurname));
      return this.dataSource.data = this.reports;
    }else if(this.selectedOption == '6') {
      this.reports.sort((a, b) => b.patientSurname.localeCompare(a.patientSurname));
      return this.dataSource.data = this.reports;
    }else if(this.selectedOption == '7'){
      this.reports.sort(function (a, b) {
        return (b.quantaty) - (a.quantaty)
      })
      return this.dataSource.data = this.reports;
    }else {
      this.reports.sort(function (a, b) {
        return (a.quantaty) - (b.quantaty)
      })
      return this.dataSource.data = this.reports;
    }
  }

}
