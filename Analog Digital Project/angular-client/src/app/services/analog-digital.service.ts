import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { HttpClient } from "@angular/common/http";
@Injectable()
export class AnalogDigitalService{
    
    constructor(private http:HttpClient){

    }

    updateDigitalValue(id:any,p:any) : Observable<any>{
        return this.http.put("http://localhost:9090/digital/"+id,p);
    }

    updateAnalogValue(id:any,p:any) : Observable<any>{
        return this.http.put("http://localhost:9090/analog/"+id,p);
    }

    getDigitalValue(id:any) : Observable<any>{
        return this.http.get("http://localhost:9090/digital/"+id);
    }

    getAnalogValue(id:any) : Observable<any>{
        return this.http.get("http://localhost:9090/analog/"+id);
    }
}
