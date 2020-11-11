import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { WaitersService } from 'src/app/core/service/waiters.service';
import { TablesService } from 'src/app/core/service/tables.service';

import Waiter from 'src/app/core/model/waiter.model';
import Table from  'src/app/core/model/table.model';
import { FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-table',
  templateUrl: './add-table.component.html',
  styleUrls: ['./add-table.component.css']
})
export class AddTableComponent implements OnInit {

  waiters$: Observable<Waiter[]>
  tables$: Observable<Table[]>
  form;

  constructor(
    private wiatersService: WaitersService,
    private tablesService: TablesService,
    private fb: FormBuilder
    ) { }

  ngOnInit(): void {
    this.waiters$ = this.wiatersService.getAllWaiters();
    this.tables$ = this.tablesService.getAllFreeTables();
    this.form = this.fb.group({
      waiter: ['', Validators.required],
      table: ['', Validators.required]
    })
  }

  get f () {
    return this.form.controls;
  }

  add() {
   this.wiatersService.addTableToWaiter(this.form.value)
   .subscribe( data => this.ngOnInit());
  }

}
