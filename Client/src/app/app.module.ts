import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AuthModule } from 'src/app/components/auth/auth.module';
import { HttpClientModule } from '@angular/common/http';
import { TablesModule } from 'src/app/components/tables/tables.module';
import { ProductsModule } from 'src/app/components/products/products.module';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AllBillsComponent } from './components/bills/all-bills/all-bills.component';
import { WelcomeComponent } from './components/shared/welcome/welcome.component';

@NgModule({
  declarations: [
    AppComponent,
    AllBillsComponent,
    WelcomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AuthModule,
    HttpClientModule,
    TablesModule,
    ProductsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
