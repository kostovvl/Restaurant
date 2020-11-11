import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import Product from '../model/product.model';
import Category from '../model/category.model';

@Injectable({
  providedIn: 'root'
})
export class ProductsService {

  
  private readonly create_category_url = 'http://localhost:8080/categories/add';
  private readonly delete_category_url = 'http://localhost:8080/categories/delete/';
  private readonly create_product_url = 'http://localhost:8080/products/add';
  private readonly delete_product_url = 'http://localhost:8080/products/delete/';
  private readonly get_all_products_url = 'http://localhost:8080/products/all';
  private readonly get_all_categories_url = 'http://localhost:8080/categories/all';

  constructor(private http: HttpClient) { }

  createCategory(form: Object) {
    return this.http.post(this.create_category_url, form)
  }

  deleteCategory(id: number) {
    return this.http.delete(this.delete_category_url + id);
  }

  createProduct(form: Object) {
    return this.http.post(this.create_product_url, form)
  }

  deleteProduct(id: number) {
    return this.http.delete(this.delete_product_url + id);
  }

  getAllProducts() {
    return this.http.get<Product[]>(this.get_all_products_url);
  }

  getAllCategories() {
    return this.http.get<Category[]>(this.get_all_categories_url);
  }

}
