import { HttpClient } from "@angular/common/http";

export class GetService<T> {
    
    constructor(protected http: HttpClient, private API_URL: any){}

    listAllProducts(){
        return this.http.get<T[]>(this.API_URL)
    }
    marketFilter(filter: string){
        return this.http.get<T[]>(`${this.API_URL}market/${filter}`)
    }

    techFilter(filter: any[]){
        let tech: string = '';
        for(let i = 0; i< filter.length; i++){
            tech += filter[i] + ',';
        }
        return this.http.get<T[]>(`${this.API_URL}technology?arr=${filter}`)
    }
}