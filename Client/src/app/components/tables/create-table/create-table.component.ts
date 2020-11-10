import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { TablesService } from 'src/app/core/service/tables.service';

@Component({
  selector: 'app-create-table',
  templateUrl: './create-table.component.html',
  styleUrls: ['./create-table.component.css']
})
export class CreateTableComponent implements OnInit {

  form;

  constructor(private tableService: TablesService,
    private fb: FormBuilder) { }

  ngOnInit(): void {
    this.form = this.fb.group({
      number: ['', Validators.required]
    })
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
