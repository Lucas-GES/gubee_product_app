import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { ReactiveFormsModule } from "@angular/forms";
import { ProductListComponent } from "./product-list/product-list.component";
import { ProductRoutingModule } from "./product-routing.module";


@NgModule({
    imports: [
        CommonModule,
        ProductRoutingModule,
        ReactiveFormsModule
    ],
    declarations: [ProductListComponent]
})
export class ProductsModule {}