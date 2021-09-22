import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from "@angular/common/http";
import { Observable, throwError } from 'rxjs';
import { environment } from '../../../../../environments/environment';
import { Product } from 'src/app/shared/models/product';
import { catchError } from 'rxjs/internal/operators/catchError';
import { Seller } from 'src/app/shared/models/seller';


@Injectable({
  providedIn: 'root'
})
export class SellerRecommendedProductsService {
  seller: Seller;
  product: Product;

  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }


  getRecommendedProduct(): Observable<Product> {
    this.seller = JSON.parse(sessionStorage.getItem("seller"))
    const url = environment.sellerProductRecommendAPI + "/getRecommendedProduct/" + this.seller.emailId + "/";
    return this.http.get<Product>(url)
    .pipe(catchError(this.handleError));

  }

  deleteRecommendedProduct(emailId ,productId): Observable<Product> {
    const url = environment.sellerProductRecommendAPI + "/deleteRecommendedProduct/?emailId=" + emailId + "&productId=" + productId;
    console.log(JSON.stringify(emailId));
    return this.http.post<Product>(url,JSON.stringify(emailId, productId), { headers: this.headers, responseType: 'text' as 'json' })
      .pipe(catchError(this.handleError));
  }

  private handleError(err: HttpErrorResponse) {
    console.log(err)
    let errMsg: string = '';
    if (err.error instanceof Error) {
      errMsg = err.error.message;
      console.log(errMsg)
    }
    else if (typeof err.error === 'string') {
      errMsg = JSON.parse(err.error).message
    }
    else {
      if (err.status == 0) {
        errMsg = "A connection to back end can not be established.";
      } else {
        errMsg = err.error.message;
      }
    }
    return throwError(errMsg);
  }
}
