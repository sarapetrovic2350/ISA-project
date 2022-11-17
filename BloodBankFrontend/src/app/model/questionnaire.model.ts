export class Questionnaire {
    constructor(
        public userEmail: string = '',
        public weight: number = 0,
        public age: number = 0,
        public name: string = '',
        public generalGoodHealth: Boolean = false,  
        public symptomsOfIllness: Boolean = false, 
        public underMedication: Boolean = false, 
        public normalBloodPressure: Boolean = false, 
        public skinDisorders: Boolean = false, 
        public tattooOrPiercing: Boolean = false, 
        public recentlyVisitedDentist: Boolean = false, 
        public recentlyDonatedBlood: Boolean = false, 
        public hasPeriod: Boolean = false, 
      ) {}
}
