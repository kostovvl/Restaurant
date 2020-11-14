import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthService } from 'src/app/core/service/auth.service';
import { BillsService } from 'src/app/core/service/bills.service';
import { TablesService } from 'src/app/core/service/tables.service';

import Bill from 'src/app/core/model/bill.model';
import Table from 'src/app/core/model/table.model';
import { FormBuilder, Validators } from '@angular/forms';


@Component({
  selector: 'app-all-bills',
  templateUrl: './all-bills.component.html',
  styleUrls: ['./all-bills.component.css']
})
export class AllBillsComponent implements OnInit {

  allBills$: Observable<Bill[]>;
  allTables$: Observable<Table[]>
  form;

  constructor(
    private authService: AuthService,
    private billsService: BillsService,
    private tablesService: TablesService,
    private fb: FormBuilder,
    ) { }

  ngOnInit(): void {
    let waiterId = Number(this.authService.getId());
    this.allBills$ = this.billsService.getAllBillsByWaiter(waiterId);
    this.allTables$ = this.tablesService.getAllTables();
    this.form = this.fb.group({
      table: ['', Validators.required]
    })
  }

  get f() {
    return this.form.controls;
  }

  create() {
    let waiterId = Number(this.authService.getId());
    let tableId = this.form.value['table'];
    this.billsService.createBill(tableId, waiterId)
    .subscribe(data => {
      this.ngOnInit();
    })
    
  }

}
