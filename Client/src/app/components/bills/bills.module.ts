import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BillDetailsComponent } from './bill-details/bill-details.component';
import { CreateBillComponent } from './create-bill/create-bill.component';
import { BillAddProductComponent } from './bill-add-product/bill-add-product.component';



@NgModule({
  declarations: [BillDetailsComponent, CreateBillComponent, BillAddProductComponent],
  imports: [
    CommonModule
  ]
})
export class BillsModule { }
