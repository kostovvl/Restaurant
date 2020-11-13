import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthService } from 'src/app/core/service/auth.service';
import { BillsService } from 'src/app/core/service/bills.service';

import Bill from 'src/app/core/model/bill.model';

@Component({
  selector: 'app-all-bills',
  templateUrl: './all-bills.component.html',
  styleUrls: ['./all-bills.component.css']
})
export class AllBillsComponent implements OnInit {

  allBills$: Observable<Bill[]>;

  constructor(
    private authService: AuthService,
    private billsService: BillsService
    ) { }

  ngOnInit(): void {
    let waiterId = Number(this.authService.getId());
    this.allBills$ = this.billsService.getAllBillsByWaiter(waiterId);
  }

}
