import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { HistoryOfVisit } from 'src/app/model/history-of-visit.model';
import { ReportService } from 'src/app/service/report.service';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-history-of-visits',
  templateUrl: './history-of-visits.component.html',
  styleUrls: ['./history-of-visits.component.css']
})
export class HistoryOfVisitsComponent implements OnInit {

  title = "History of visits to medical centers";
  public dataSource = new MatTableDataSource<HistoryOfVisit>();
  public displayedColumns = ['date', 'donatedBlood', 'bloodType', 'centerName'];
  public historyOfVisits: HistoryOfVisit[] = [];
  public selectedOption: string = "";
  public registeredUserId: number = 0;

  constructor(private route: ActivatedRoute, private router: Router, private reportService: ReportService, private authService: AuthService) { }

  ngOnInit(): void {
    this.registeredUserId = parseInt(this.authService.getCurrentUser().userId);
    this.reportService.findHistoryOfVisitsForUser(this.registeredUserId).subscribe(res => {
      this.historyOfVisits = res;
      console.log(this.historyOfVisits);
      this.dataSource.data = this.historyOfVisits;
    });
  }

  changeSorting() {
    console.log(this.selectedOption);
    if (this.selectedOption == '1') {
      this.historyOfVisits.sort((a, b) => new Date(a.date).getTime() - new Date(b.date).getTime());
      return this.dataSource.data = this.historyOfVisits;
    }
    else if (this.selectedOption == '3') {
      this.historyOfVisits.sort((a, b) => new Date(b.date).getTime() - new Date(a.date).getTime());
      return this.dataSource.data = this.historyOfVisits;
    
    } else if (this.selectedOption == '2') {
      this.historyOfVisits.sort((a, b) => a.medicalCenterName.localeCompare(b.medicalCenterName));
      return this.dataSource.data = this.historyOfVisits;
    }
    else if (this.selectedOption == '4') {
      this.historyOfVisits.sort(function (a, b) {

        return parseInt(b.donatedBloodQuantity) - parseInt(a.donatedBloodQuantity)
      })
      return this.dataSource.data = this.historyOfVisits;
    }else {
      return null;
    }
  }

}
