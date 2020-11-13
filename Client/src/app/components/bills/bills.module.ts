import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BillDetailsComponent } from './bill-details/bill-details.component';
import { CreateBillComponent } from './create-bill/create-bill.component';



@NgModule({
  declarations: [BillDetailsComponent, CreateBillComponent],
  imports: [
    CommonModule
  ]
})
export class BillsModule { }
