import { Component, OnInit } from '@angular/core';
import { catchError, EMPTY, Observable } from 'rxjs';
import { Products2Service } from '../products2.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  products$!: Observable<any[]>;

  checkedTech: any = [];

  marketNames: any = [];

  technologyNames: any = [];
  
  constructor(
    private service: Products2Service
  ) { }

  ngOnInit(): void {
    this.onRefresh();    
    this.findMarketNames(this.products$);
    this.findTechNames(this.products$);
  }

  onRefresh() {
    this.products$ = this.service.listAllProducts()
      .pipe(
        catchError(error => {
          console.log(error);
          this.handleError();
          return EMPTY;
        })
      )
  }

  handleError() {
    alert("Error 404");
  }

  findMarketNames(products: any){
    let market: any = [];    
    products.forEach((product:any) => {
      product.forEach((obj: any) => {
        market.push(obj.market);
        market.forEach((names: any)=> {
          if(!this.marketNames.includes(names.name)){
            this.marketNames.push(names.name);
          }
        })
      });        
    });   
  }

  findTechNames(products: any){
    let tech: any = [];
    products.forEach((product:any) => {
      product.forEach((obj:any) => {
        tech.push(obj.technology)
        tech.forEach((techName:any)=> {
          techName.forEach((names: any)=>{
            if(!this.technologyNames.includes(names.name)){
              this.technologyNames.push(names.name);
            }            
          })
        })
      })
    })
  }

  techChecked(technology: string){
    if(this.checkedTech.includes(technology)){
      this.checkedTech = this.checkedTech.filter((e: string) => e != technology);
    }else{
      this.checkedTech.push(technology);
      console.log(technology);
    }
    
  }

  filterTech(){
    this.products$ = this.service.techFilter(this.checkedTech)
    .pipe(
      catchError(error => {
        console.log(error);
        this.handleError();
        return EMPTY;
      })
    )
  }

  filterMarket(market: string){
    this.products$ = this.service.marketFilter(market)
    .pipe(
      catchError(error => {
        console.log(error);
        this.handleError();
        return EMPTY;
      })
    )
  }

}
