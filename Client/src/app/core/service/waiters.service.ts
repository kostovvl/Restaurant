import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import Waiter from '../model/waiter.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WaitersService {

  private readonly get_all_waiters_url = 'http://localhost:8080/waiters/all';
  private readonly add_table_to_waiter = 'http://localhost:8080/waiters/add_table/';

  constructor(private http: HttpClient) { }


  getAllWaiters() {
    return this.http.get<Waiter[]>(this.get_all_waiters_url);
  }

  addTableToWaiter(form: Object) {
   let waiterId = form['waiter'];
   let tableId = form['table'];

   return this.http.put(this.add_table_to_waiter + waiterId + '/' + tableId, Object);
  }

}
