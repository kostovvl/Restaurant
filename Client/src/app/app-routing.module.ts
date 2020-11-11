import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from '../app/components/auth/home/home.component';
import { AllBillsComponent } from 'src/app/components/bills/all-bills/all-bills.component';
import { WelcomeComponent } from 'src/app/components/shared/welcome/welcome.component';
import { CreateTableComponent } from 'src/app/components/tables/create-table/create-table.component';
import { AddTableComponent } from 'src/app/components/tables/add-table/add-table.component';
import { AllProductsComponent } from 'src/app/components/products/all-products/all-products.component';
import { CreateCategoryComponent } from 'src/app/components/products/create-category/create-category.component';
import { CreateProductComponent } from 'src/app/components/products/create-product/create-product.component';

const routes: Routes = [
  { path: '', pathMatch: 'full', component: HomeComponent },
  { path: 'home', component: HomeComponent },
  { path: 'welcome', component: WelcomeComponent },
  { path: 'bills', component: AllBillsComponent },
  { path: 'tables/create', component:  CreateTableComponent},
  { path: 'tables/add', component:  AddTableComponent},
  { path: 'products', component:  AllProductsComponent}, 
  { path: 'products/create/category', component: CreateCategoryComponent},
  { path: 'products/create/product', component: CreateProductComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
