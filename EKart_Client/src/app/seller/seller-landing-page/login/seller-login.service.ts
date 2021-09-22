import { HttpClient, HttpHeaders, HttpErrorResponse } from "@angular/common/http";
import { Observable, throwError } from 'rxjs';
import { Injectable } from '@angular/core';
import { Seller } from '../../../shared/models/seller';
import { environment } from '../../../../environments/environment';
import { catchError } from 'rxjs/operators';


@Injectable({
    providedIn: 'root'
})
export class SellerLoginService {
    private headers = new HttpHeaders({ 'Content-Type': 'application/json' });

    constructor(private http: HttpClient) {

    }
    login(seller: Seller): Observable<Seller> {
        const url = environment.sellerAPIUrl + '/sellerLogin';
        return this.http.post<Seller>(url, seller, { headers: this.headers })
            .pipe(catchError(this.handleError));

    }
    private handleError(err: HttpErrorResponse) {
        console.log(err)
        let errMsg:string='';
        if (err.error instanceof Error) {   
            errMsg=err.error.message;
            console.log(errMsg)
        }
         else if(typeof err.error === 'string'){
            errMsg=JSON.parse(err.error).message
        }
        else {
           if(err.status==0){ 
               errMsg="A connection to back end can not be established.";
           }else{
               errMsg=err.error.message;
           }
         }
            return throwError(errMsg);
    }
}