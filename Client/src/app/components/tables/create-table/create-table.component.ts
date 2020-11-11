import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { TablesService } from 'src/app/core/service/tables.service';
import Table from 'src/app/core/model/table.model';

@Component({
  selector: 'app-create-table',
  templateUrl: './create-table.component.html',
  styleUrls: ['./create-table.component.css']
})
export class CreateTableComponent implements OnInit {

  form;
  tables$: Observable<Table[]>

  constructor(private tableService: TablesService,
    private fb: FormBuilder) { }

  ngOnInit(): void {
    this.form = this.fb.group({
      number: ['', Validators.required]
    })
    this.tables$ = this.tableService.getAllTables();
    this.tables$.subscribe(data => {console.log(data)})
  }

  get f() {
    return this.form.controls;
  }

  submit() {
    this.tableService.createTable(this.form.value['number'])
    .subscribe(data => {
      this.ngOnInit();
    })
  }

}
