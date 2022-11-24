import { Component, OnInit } from '@angular/core';
import { medicalCenter } from 'src/app/model/medicalCenter.model';
import { MedicalCenterServiceService } from 'src/app/service/medical-center.service.service';

@Component({
  selector: 'app-medical-centers',
  templateUrl: './medical-centers.component.html',
  styleUrls: ['./medical-centers.component.css']
})
export class MedicalCentersComponent implements OnInit {

  medicalCenters: medicalCenter[] = [];
  page = 1;
  count = 0;
  pageSize = 3;
  pageSizes = [3, 6, 9];
  selectedOption: string = "";
  filterOption: string = "";
  name: string = "";
  place: string = "";
  isSearched: Boolean = false;

  constructor(private medicalCenterService: MedicalCenterServiceService) { }

  ngOnInit(): void {
    this.retrieveCenters();
  }

  retrieveCenters(): void {
    const params = this.getRequestParams(this.page, this.pageSize);
    this.medicalCenterService.getAll(params).subscribe((data: any) => {
      this.medicalCenters = data.centers;
      this.count = data.totalItems;
      console.log(data);
      console.log(this.medicalCenters);
    })
  }

  handlePageChange(event: number): void {
    this.page = event;
    this.retrieveCenters();
  }

  getRequestParams(page: number, pageSize: number): any {
    let params: any = {};

    if (page) {
      params[`page`] = page - 1;
    }

    if (pageSize) {
      params[`size`] = pageSize;
    }

    return params;
  }

  changeSorting() {
    if (this.selectedOption == '1') {

      return this.medicalCenters.sort((a, b) => a.name.localeCompare(b.name));

    } else if (this.selectedOption == '3') {

      this.medicalCenters.sort(function (a, b) {

        return b.averageGrade - a.averageGrade
      })

    } else if (this.selectedOption == '2') {

      return this.medicalCenters.sort((a, b) => a.address.city.localeCompare(b.address.city));

    }
    else {
      return null;
    }

  }

  public searchMediclaCenter(): void {
    const centerName = this.name;
    const centerPlace = this.place;
    this.medicalCenterService.searchMediclaCenter(centerName, centerPlace).subscribe((data: any) => {
      this.medicalCenters = data;
      console.log(data);
      console.log(this.medicalCenters);
      this.isSearched = true;
    })
  };

  public filterMedicalCenters(): void {
    const centerName = this.name;
    const centerPlace = this.place;
    const grade = this.filterOption;
    this.medicalCenterService.filterMedicalCenters(centerName, centerPlace, grade).subscribe((data: any) => {
      this.medicalCenters = data;
      console.log(data);
      console.log(this.medicalCenters);
    })
  };

  cancelSearch() {
    this.retrieveCenters();
    this.isSearched = false;
  }
}
