import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ProductsService } from 'src/app/core/service/products.service';

@Component({
  selector: 'app-create-category',
  templateUrl: './create-category.component.html',
  styleUrls: ['./create-category.component.css']
})
export class CreateCategoryComponent implements OnInit {

  form;

  constructor(
    private fb: FormBuilder,
    private productsService: ProductsService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.form = this.fb.group({
      name: ['', Validators.required]
    })
  }

  get f () {
    return this.form.controls;
  }

  create() {
    this.productsService.createCategory(this.form.value)
    .subscribe(data => {
      this.router.navigate(['products'])
    })
  }

}
