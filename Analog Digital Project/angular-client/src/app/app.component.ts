import { Component } from '@angular/core';
import { AnalogDigitalService } from './services/analog-digital.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {

  constructor(private adService:AnalogDigitalService){

  }

  value=false;
  idValue=0;
  valueAnalog=0;
  a=0;
  d=false;
  submitDigital(){
    this.adService.updateDigitalValue(this.idValue,{"value":this.value}).subscribe(data=>console.log(data));
  }

  submitAnalog(){
    this.adService.updateAnalogValue(this.idValue,{"value":this.valueAnalog}).subscribe(data=>console.log(data));
  }

  readDigital(){
    this.adService.getDigitalValue(this.idValue).subscribe(data=>{this.d=data});
  }

  readAnalog(){
    this.adService.getAnalogValue(this.idValue).subscribe(data=>this.a=data);
  }

}