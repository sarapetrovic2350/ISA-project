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
  constructor(private medicalCenterService: MedicalCenterServiceService) { }

  ngOnInit(): void {
    this.medicalCenterService.getAll().subscribe((data: any) => {
    this.medicalCenters = data;
      console.log(data);
      console.log(this.medicalCenters);
    })
  }

}
