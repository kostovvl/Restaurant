import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import Table from '../model/table.model';

@Injectable({
  providedIn: 'root'
})
export class TablesService {

  private readonly get_existing_tables_url = 'http://localhost:8080/tables/all';
  private readonly get_free_tables_url = 'http://localhost:8080/tables/free';
  private readonly create_new_table_url = 'http://localhost:8080/tables/create/';
  
  


  constructor(private http: HttpClient) { }

  createTable(tableNumber: number) {
    return this.http.post(this.create_new_table_url + tableNumber, Object);
  }

  getAllTables() {
    return this.http.get<Table[]>(this.get_existing_tables_url);
  }

  getAllFreeTables() {
    return this.http.get<Table[]>(this.get_free_tables_url);
  }

}
