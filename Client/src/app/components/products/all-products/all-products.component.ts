import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import Product from 'src/app/core/model/product.model';

import { ProductsService } from 'src/app/core/service/products.service'

@Component({
  selector: 'app-all-products',
  templateUrl: './all-products.component.html',
  styleUrls: ['./all-products.component.css']
})
export class AllProductsComponent implements OnInit {

  allProducts$: Observable<Product[]>

  constructor(
    private productService: ProductsService
  ) { }

  ngOnInit(): void {
    this.allProducts$ = this.productService.getAllProducts();
    this.allProducts$.subscribe(data => console.log(data))
  }

}
