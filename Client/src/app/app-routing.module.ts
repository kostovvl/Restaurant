import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from '../app/components/auth/home/home.component';
import { AllBillsComponent } from 'src/app/components/bills/all-bills/all-bills.component';
import { WelcomeComponent } from 'src/app/components/shared/welcome/welcome.component';
import { CreateTableComponent } from 'src/app/components/tables/create-table/create-table.component';

const routes: Routes = [
  { path: '', pathMatch: 'full', component: HomeComponent },
  { path: 'home', component: HomeComponent },
  { path: 'welcome', component: WelcomeComponent },
  { path: 'bills', component: AllBillsComponent },
  { path: 'tables/create', component:  CreateTableComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
