import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import Product from '../model/product.model';

@Injectable({
  providedIn: 'root'
})
export class ProductsService {

  private readonly get_all_products_url = 'http://localhost:8080/products/all';
  private readonly get_all_categories_url = 'http://localhost:8080/categories/all';
  private readonly create_category_url = 'http://localhost:8080/categories/add';
  private readonly create_product_url = 'http://localhost:8080/products/add';

  constructor(private http: HttpClient) { }

  createCategory(form: Object) {
    return this.http.post(this.create_category_url, form)
  }

  createProduct(form: Object) {
    return this.http.post(this.create_category_url, form)
  }

  getAllProducts() {
    return this.http.get<Product[]>(this.get_all_products_url);
  }

}
