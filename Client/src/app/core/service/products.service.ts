import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import Product from '../model/product.model';

@Injectable({
  providedIn: 'root'
})
export class ProductsService {

  private readonly get_all_products_url = 'http://localhost:8080/products/all';

  constructor(private http: HttpClient) { }

  getAllProducts() {
    return this.http.get<Product[]>(this.get_all_products_url);
  }

}
