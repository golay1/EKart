import { Component, OnInit } from "@angular/core";
import { Seller } from "../../shared/models/seller";
import { Router, ActivatedRoute } from "@angular/router";
import { SellerSharedService } from "./seller-shared.service";


@Component({
    selector: 'seller-home',
    templateUrl: './../seller-home/seller-home.component.html',

})
export class SellerHomeComponent implements OnInit {
    isViewProductSelected: boolean = false;
    loggedInSeller: Seller;
    constructor(private router: Router, private route: ActivatedRoute, private sharedService: SellerSharedService) {
    }

    ngOnInit() {
        this.sharedService.currentSeller.subscribe(seller => this.loggedInSeller = seller);
        this.loggedInSeller = JSON.parse(sessionStorage.getItem("seller"));
    }

    getAllProducts() {
        this.isViewProductSelected = !this.isViewProductSelected;
    }

    update(seller: Seller) {
        this.loggedInSeller = seller;
    }

    logout() {
        sessionStorage.clear();
        this.router.navigate(["seller"]);
    }

}