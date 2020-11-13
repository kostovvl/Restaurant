import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import Bill from '../model/bill.model';

@Injectable({
  providedIn: 'root'
})
export class BillsService {

  private readonly get_all_bills_by_waiter = 'http://localhost:8080/bills/get/by_waiter/';

  constructor(private http: HttpClient) { }

  getAllBillsByWaiter(id: number) {
    return this.http.get<Bill[]>(this.get_all_bills_by_waiter + id);
  }
}
