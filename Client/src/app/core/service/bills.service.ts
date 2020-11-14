import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import Bill from '../model/bill.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BillsService {

  private readonly get_all_bills_by_waiter = 'http://localhost:8080/bills/get/by_waiter/';
  private readonly get_details = 'http://localhost:8080/bills/get/details/';

  private readonly create_new_bill = 'http://localhost:8080/bills/create/';
  private readonly add_products = 'http://localhost:8080/bills/add/product/';

  private readonly remove_product = 'http://localhost:8080/bills/delete/product/';
  
  constructor(private http: HttpClient) { }

  createBill(tableId: number, waiterId: number) {
    console.log(tableId, waiterId)
   return this.http.post(this.create_new_bill + waiterId + '/' + tableId, Object);
  }

  getAllBillsByWaiter(id: number) {
    return this.http.get<Bill[]>(this.get_all_bills_by_waiter + id);
  }

  addProducts(billId: number, productId: number, quantity: number) {
    return this.http.put(this.add_products + billId + '/' + productId + '/' + quantity, Object);
  }

  getDetails(billId: number) {
   return this.http.get<Bill>(this.get_details + billId);
  }

  removeProduct(billId: number, productId: number) {
   return this.http.delete(this.remove_product + billId + '/' + productId);
  }
}
