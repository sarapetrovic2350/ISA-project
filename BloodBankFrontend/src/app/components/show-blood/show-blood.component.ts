import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from "@angular/material/table";
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Blood } from 'src/app/model/blood.model';
import { User } from 'src/app/model/user.model';
import { BloodService } from 'src/app/service/blood.service';
import { CenterAdministrator } from 'src/app/model/center-administrator.model';
import { AuthService } from 'src/app/service/auth.service';
import { CenterAdministratorService } from 'src/app/service/center-administrator.service';
import { medicalCenter } from 'src/app/model/medicalCenter.model';

@Component({
  selector: 'app-show-blood',
  templateUrl: './show-blood.component.html',
  styleUrls: ['./show-blood.component.css']
})

export class ShowBloodComponent implements OnInit {

  public dataSource = new MatTableDataSource<Blood>();
  public displayedColumns = ['BloodType', 'Quantaty'];
  public bloodBanks: Blood[] = [];
  public blood: Blood | undefined = undefined;
  public user = new User();
  public medCenter: medicalCenter = new medicalCenter();
  public administrator: CenterAdministrator | undefined = undefined;
  public centerAdministrators: CenterAdministrator[] = [];

  constructor(private bankService: BloodService,private centerAdministratorService: CenterAdministratorService, private router: Router, private route: ActivatedRoute, private authService: AuthService) { }

  ngOnInit(): void {

    this.user = this.authService.getCurrentUser();
      this.centerAdministratorService.findByEmail(this.user.email).subscribe(res => {
        this.administrator = res;
        console.log(this.administrator); 
        
          this.centerAdministratorService.findCenterByAdminEmail(String(this.administrator.email)).subscribe(res => {
            this.medCenter = res;
            console.log(this.medCenter);

              this.bankService.showBloodByCenterId(this.medCenter.centerId).subscribe((data: any) => {  
              console.log(this.bloodBanks);     
              this.bloodBanks = data;
              this.dataSource.data = this.bloodBanks;
            })
            

          })
        
        
      })
    

  }

}
