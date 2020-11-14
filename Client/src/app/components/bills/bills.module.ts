import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BillDetailsComponent } from './bill-details/bill-details.component';
import { BillAddProductComponent } from './bill-add-product/bill-add-product.component';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { AllBillsComponent } from './all-bills/all-bills.component';



@NgModule({
  declarations: [AllBillsComponent, BillDetailsComponent, BillAddProductComponent],
  imports: [
    BrowserModule,
    CommonModule,
    ReactiveFormsModule
  ]
})
export class BillsModule { }
