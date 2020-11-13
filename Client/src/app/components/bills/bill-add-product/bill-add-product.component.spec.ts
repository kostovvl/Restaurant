import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BillAddProductComponent } from './bill-add-product.component';

describe('BillAddProductComponent', () => {
  let component: BillAddProductComponent;
  let fixture: ComponentFixture<BillAddProductComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BillAddProductComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BillAddProductComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
