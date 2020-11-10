import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CreateTableComponent } from './create-table/create-table.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';



@NgModule({
  declarations: [CreateTableComponent],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class TablesModule { }
