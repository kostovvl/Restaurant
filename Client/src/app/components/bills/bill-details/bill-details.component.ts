import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BillsService } from 'src/app/core/service/bills.service';

import Bill from 'src/app/core/model/bill.model';

@Component({
  selector: 'app-bill-details',
  templateUrl: './bill-details.component.html',
  styleUrls: ['./bill-details.component.css']
})
export class BillDetailsComponent implements OnInit {

  bill: Bill;

  constructor(
    private route: ActivatedRoute,
    private billsService: BillsService
  ) { }

  ngOnInit(): void {
    let billId = this.route.snapshot.params['id'];
    this.billsService.getDetails(billId)
    .subscribe(data => {
      this.bill = data;
    })
  }

  getName(productId: number, products: Map<number, string>) {
    return products.get(productId);
  }

  remove(productId: number) {
    let billId  = this.bill.id;
    this.billsService.removeProduct(billId, productId)
    .subscribe(data => {
      this.ngOnInit()
    })
  }

}
