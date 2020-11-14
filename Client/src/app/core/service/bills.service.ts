import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import Bill from '../model/bill.model';

@Injectable({
  providedIn: 'root'
})
export class BillsService {

  private readonly get_all_bills_by_waiter = 'http://localhost:8080/bills/get/by_waiter/';
  private readonly create_new_bill = 'http://localhost:8080/bills/create/';

  constructor(private http: HttpClient) { }

  createBill(tableId: number, waiterId: number) {
    console.log(tableId, waiterId)
   return this.http.post(this.create_new_bill + waiterId + '/' + tableId, Object);
  }

  getAllBillsByWaiter(id: number) {
    return this.http.get<Bill[]>(this.get_all_bills_by_waiter + id);
  }
}
