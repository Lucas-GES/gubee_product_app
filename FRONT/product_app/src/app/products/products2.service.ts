import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { environment } from "src/environments/environment";
import { GetService } from "./get-service";
import { Product } from "./product";

@Injectable({
    providedIn: 'root'
})
export class Products2Service extends GetService<Product>{

    constructor(protected override http: HttpClient){
        super(http, `${environment.API}product/`)
    }
}