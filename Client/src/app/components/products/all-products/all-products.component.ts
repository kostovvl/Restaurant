import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import Category from 'src/app/core/model/category.model';
import Product from 'src/app/core/model/product.model';

import { ProductsService } from 'src/app/core/service/products.service'

@Component({
  selector: 'app-all-products',
  templateUrl: './all-products.component.html',
  styleUrls: ['./all-products.component.css']
})
export class AllProductsComponent implements OnInit {

  allProducts$: Observable<Product[]>;
  allCategories$: Observable<Category[]>;

  constructor(
    private productService: ProductsService
  ) { }

  ngOnInit(): void {
    this.allProducts$ = this.productService.getAllProducts();
    this.allCategories$ = this.productService.getAllCategories();
  }

  deleteCategory(id: number) {
    this.productService.deleteCategory(id)
    .subscribe(data => {
      this.ngOnInit()
    })
  }

  deleteProduct(id: number) {
    this.productService.deleteProduct(id)
    .subscribe(data => {
      this.ngOnInit()
    })
  }

}
