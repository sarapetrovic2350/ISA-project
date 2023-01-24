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

}
