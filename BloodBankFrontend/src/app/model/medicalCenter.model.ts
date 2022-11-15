import { Address } from "./address.model";

export class medicalCenter{
    constructor(
        public id:number = 0,
        public name:string ='',
        public description:string ='',
        public averageGrade:number=0,
        public address:Address = new Address(),
        public image: string = ''
    ){}
}

