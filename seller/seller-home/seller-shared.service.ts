import { Injectable } from "@angular/core";
import { BehaviorSubject } from 'rxjs'
import { Seller } from "../../shared/models/seller";

@Injectable({
    providedIn:'root'
})
export class SellerSharedService{

    private loggedInSellerDetails=new BehaviorSubject<Seller>(JSON.parse(sessionStorage.getItem("seller")));
    currentSeller=this.loggedInSellerDetails.asObservable();

    updateSeller(seller:Seller){
        this.loggedInSellerDetails.next(seller);
    }

}