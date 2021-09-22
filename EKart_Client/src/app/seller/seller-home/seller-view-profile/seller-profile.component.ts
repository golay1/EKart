import { Component, Output, EventEmitter } from "@angular/core";
import { OnInit, OnDestroy } from "@angular/core/src/metadata/lifecycle_hooks";
import { Seller } from "../../../shared/models/seller";
import { SellerProfileService } from "./seller-profile.service";
import { Router, ActivatedRoute } from "@angular/router";
import { SellerSharedService } from "../seller-shared.service";




@Component(
    {
        selector: 'seller-view-profile',
        templateUrl: './seller-profile.component.html',
        styleUrls: ['./seller-profile.component.css']
    }
)
export class SellerProfileComponent implements OnInit, OnDestroy {
    
    seller: Seller;
    updating: string = null;
    sellerToUpdate: Seller; 
    errorMessage: string = "";
    successMessage: string = "";
    backupSeller: Seller;

    constructor(private sellerProfileService: SellerProfileService,
        private router: Router,
        private route: ActivatedRoute,
        private sharedService: SellerSharedService
    ) { }

    
    ngOnInit() {
        this.seller = JSON.parse(sessionStorage.getItem("seller"));
        this.sellerToUpdate = this.seller;
        this.backupSeller = Object.assign({}, this.sellerToUpdate); //clone the order to {} and assigned to newOrder
        this.sharedService.currentSeller.subscribe(seller => this.seller = seller);
    }

    linkClick(value) {
        this.updating = value;
        this.errorMessage = "";
        this.successMessage = "";
    }

    updateDetail(action: string) {
        this.errorMessage = null;
        this.successMessage = null;
        if (action == "update") {
            this.sellerProfileService.updateSellerDetails(this.sellerToUpdate).subscribe(
                (response) => {
                    this.successMessage = response;
                    this.seller = this.sellerToUpdate;
                    this.backupSeller = Object.assign({}, this.sellerToUpdate); //clone the order to {} and assigned to newOrder
                    sessionStorage.setItem("seller", JSON.stringify(this.sellerToUpdate));
                    this.updating = "";

                    this.sharedService.updateSeller(this.sellerToUpdate);
                    this.router.navigateByUrl('/sellerHome/profile');
                }, error => {
                    this.errorMessage = <any>error;
                    Object.assign(this.sellerToUpdate, this.backupSeller); //clone the order to sellerToUpdate (only when object is present not undefined value) and assigned to newOrder
                }

            )
        } else {
            this.updating = "";
            Object.assign(this.sellerToUpdate, this.backupSeller);  //clone the order to sellerToUpdate (only when object is present not undefined value) and assigned to newOrder
        }
    }

    ngOnDestroy(): void {
        Object.assign(this.sellerToUpdate, this.backupSeller);  //clone the order to sellerToUpdate (only when object is present not undefined value) and assigned to newOrder
    }

}