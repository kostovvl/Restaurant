import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CreateTableComponent } from './create-table/create-table.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AddTableComponent } from './add-table/add-table.component';



@NgModule({
  declarations: [CreateTableComponent, AddTableComponent],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class TablesModule { }
