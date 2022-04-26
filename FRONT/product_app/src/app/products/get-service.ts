import { HttpClient } from "@angular/common/http";

export class GetService<T> {
    
    constructor(protected http: HttpClient, private API_URL: any){}

    listAllProducts(){
        return this.http.get<T[]>(this.API_URL)
    }
    marketFilter(filter: string){
        return this.http.get<T[]>(`${this.API_URL}market/${filter}`)
    }

    techFilter(filter?: string[]){
        let tech: string = '';        
        if(filter != undefined){
            filter.forEach(technology => {
                tech += `${technology}&`;
            });
        }
        return this.http.get<T[]>(`${this.API_URL}technology?arr=${tech}`)
    }
}