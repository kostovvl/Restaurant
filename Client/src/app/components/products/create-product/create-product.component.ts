import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { ProductsService } from 'src/app/core/service/products.service';

import Category from 'src/app/core/model/category.model';
import { FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-create-product',
  templateUrl: './create-product.component.html',
  styleUrls: ['./create-product.component.css']
})
export class CreateProductComponent implements OnInit {

  categories$: Observable<Category[]>
  form;

  constructor(
    private fb : FormBuilder,
    private productService : ProductsService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.categories$ = this.productService.getAllCategories();
    this.form = this.fb.group({
      name: ['', Validators.required],
      price: ['', Validators.required],
      categoryId: ['', Validators.required]
    })
  }

  get f () {
    return this.form.controls;
  }

  create() {
    this.productService.createProduct(this.form.value)
    .subscribe(data => {
      this.router.navigate(['products'])
    } )
  }

}
