import { Component, OnInit } from '@angular/core';
import { medicalCenter } from 'src/app/model/medicalCenter.model';
import { MedicalCenterServiceService } from 'src/app/service/medical-center.service.service';

@Component({
  selector: 'app-medical-centers',
  templateUrl: './medical-centers.component.html',
  styleUrls: ['./medical-centers.component.css']
})
export class MedicalCentersComponent implements OnInit {

  medicalCenters: medicalCenter[] =[];
  selectedOption: string = "";
  filterOption: string = "";
  name: string= "";
  place: string= "";
  isSearched: Boolean = false;
  constructor(private medicalCenterService: MedicalCenterServiceService) { }

  ngOnInit(): void {
    this.medicalCenterService.getAll().subscribe((data: any) => {
    this.medicalCenters = data;
      console.log(data);
      console.log(this.medicalCenters);
    })
  }

  changeSorting() {
    if(this.selectedOption == '1'){ 

      return this.medicalCenters.sort((a, b) => a.name.localeCompare(b.name));

    }else if(this.selectedOption == '3'){   

      this.medicalCenters.sort(function(a, b) {

        return b.averageGrade - a.averageGrade  })
        
    }else if(this.selectedOption == '2'){

    return this.medicalCenters.sort((a, b) => a.address.city.localeCompare(b.address.city));
 
    }
    else {
      return null;
    }

    

}

  public searchMediclaCenter() : void {
  const centerName = this.name;
  const centerPlace = this.place;
  this.medicalCenterService.searchMediclaCenter(centerName, centerPlace).subscribe((data: any) => {
    this.medicalCenters = data;
    console.log(data);
    console.log(this.medicalCenters);
    this.isSearched = true;
      })};

  public filterMedicalCenters(): void {
  const centerName = this.name;
  const centerPlace = this.place;
  const grade = this.filterOption;
  this.medicalCenterService.filterMedicalCenters(centerName, centerPlace, grade).subscribe((data: any) => {
    this.medicalCenters = data;
    console.log(data);
    console.log(this.medicalCenters);
      })};

  cancelSearch(){
    this.medicalCenterService.getAll().subscribe((data: any) => {
      this.medicalCenters = data;
        console.log(data);
        console.log(this.medicalCenters);
        this.isSearched = false;
      })
  }
}
