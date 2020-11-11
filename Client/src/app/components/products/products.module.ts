import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AllProductsComponent } from './all-products/all-products.component';
import { CreateCategoryComponent } from './create-category/create-category.component';
import { CreateProductComponent } from './create-product/create-product.component';
import { RouterModule } from '@angular/router';



@NgModule({
  declarations: [AllProductsComponent, CreateCategoryComponent, CreateProductComponent],
  imports: [
    CommonModule,
    RouterModule
  ]
})
export class ProductsModule { }
