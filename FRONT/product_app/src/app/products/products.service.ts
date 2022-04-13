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

    techFilter(filter: any[]){
        let tech: string = '';
        for(let i = 0; i< filter.length; i++){
            tech += filter[i] + ',';
        }
        return this.http.get<Product[]>(`${this.API}/technology?arr=${filter}`)
    }
}