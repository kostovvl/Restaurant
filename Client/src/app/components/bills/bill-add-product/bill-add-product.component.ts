import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import Product from 'src/app/core/model/product.model';
import { BillsService } from 'src/app/core/service/bills.service';
import { ProductsService } from 'src/app/core/service/products.service';

@Component({
  selector: 'app-bill-add-product',
  templateUrl: './bill-add-product.component.html',
  styleUrls: ['./bill-add-product.component.css']
})
export class BillAddProductComponent implements OnInit {

  allProducts$: Observable<Product[]>;
  form;

  constructor(
    private productsService: ProductsService,
    private billsServise: BillsService,
    private route: ActivatedRoute,
    private router: Router,  
    private fb: FormBuilder
  ) { }

  ngOnInit(): void {
    this.allProducts$ = this.productsService.getAllProducts();
    this.form = this.fb.group({
      product: ['', Validators.required],
      quantity: ['', Validators.required]
    })
  }

  get f () {
    return this.form.controls;
  }

  add() {
    let billId = this.route.snapshot.params['id'];
    let productId = this.form.value['product'];
    let quantity = this.form.value['quantity'];
    this.billsServise.addProducts(billId, productId, quantity)
    .subscribe(data => {
      this.router.navigate(['bills'])
    }) 
  }

}
