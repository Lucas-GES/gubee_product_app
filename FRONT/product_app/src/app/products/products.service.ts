import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { tap } from "rxjs";
import { environment } from "src/environments/environment";
import { Product } from "./product";

@Injectable({
    providedIn: 'root'
})
export class ProductsService{

    private readonly API = `${environment.API}products`;

    constructor(private http: HttpClient){}

    listAllProducts(){
        return this.http.get<Product[]>(this.API)
        .pipe(
            tap(console.log)
        );
    }

    marketFilter(filter: string){
        return this.http.get<Product[]>(`${this.API}/market/${filter}`)
        .pipe(
            tap(console.log)
        );
    }

    techFilter(filter?: string[]){
        let tech = `${this.API}/technology?`;
        if(filter != undefined){
            filter.forEach(technology => {
                tech += `arr=${technology}&`;
            });
        }
       
        console.log(tech);
        return this.http.get<Product[]>(tech);
    }
}