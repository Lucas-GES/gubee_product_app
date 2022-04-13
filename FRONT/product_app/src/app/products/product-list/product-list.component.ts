import { Component, OnInit } from '@angular/core';
import { catchError, EMPTY, Observable } from 'rxjs';
import { Product } from '../product';
import { Products2Service } from '../products2.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  products$!: Observable<Product[]>;

  checkedTech: any = [];

  constructor(
    private service: Products2Service
  ) { }

  ngOnInit(): void {
    this.onRefresh();   
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

  techChecked(technology: string){
    if(this.checkedTech.includes(technology)){
      this.checkedTech = this.checkedTech.filter((e: string) => e != technology);
    }else{
      this.checkedTech.push(technology);
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
