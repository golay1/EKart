import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import {AppRoutingModule} from './app-routing.module';
import { EKartRoutingGuard } from './app.routing-guard';
import { AuthorisationErrorComponent } from './shared/authorisation-error/authorisation-error.component';
import { SellerModule } from './seller/seller.module';
import { CustomerModule } from './customer/customer.module';
import {NgxPaginationModule} from 'ngx-pagination';


@NgModule({
  declarations: [
    AppComponent,
    AuthorisationErrorComponent
  ],
  imports: [
    NgxPaginationModule,
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    SellerModule,
    CustomerModule
  ],
  providers: [EKartRoutingGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
