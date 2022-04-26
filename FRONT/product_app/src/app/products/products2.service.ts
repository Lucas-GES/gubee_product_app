import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { environment } from "src/environments/environment";
import { GetService } from "./get-service";

@Injectable({
    providedIn: 'root'
})
export class Products2Service extends GetService<any>{

    constructor(protected override http: HttpClient){
        super(http, `${environment.API}product/`)
    }
}