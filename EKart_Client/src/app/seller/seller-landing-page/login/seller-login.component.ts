import { RouterModule, Router } from '@angular/router';
import { SellerLoginService } from './seller-login.service';
import { LoginValidators } from '../../../shared/validators/login.validator';
import { Seller } from '../../../shared/models/seller';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { RecommendedProduct } from 'src/app/shared/models/recommendedProduct';
@Component({
    selector: 'seller-login',
    templateUrl: './seller-login.component.html',
    styleUrls: ['./seller-login.component.css']
})
export class SellerLoginComponent implements OnInit {
    seller: Seller;
    // recommendedProduct: RecommendedProduct;
    loginForm: FormGroup;
    errorMessage: string;
    successMessage: string;
    tryToLogin: boolean = false;
    constructor(private fb: FormBuilder, private loginService: SellerLoginService,
        private router: Router) {

    }

    ngOnInit() {
        this.seller = new Seller();
        this.createForm();

    }
    createForm() {

        this.loginForm = this.fb.group({
            emailId: [this.seller.emailId, [Validators.required, LoginValidators.validateEmailId], null],
            password: [this.seller.password, [Validators.required, LoginValidators.validatePassword], null]
        });
    }

    login() {
        this.errorMessage = null;
        this.successMessage = null;
        this.seller = this.loginForm.value as Seller;

        this.loginService.login(this.seller).subscribe(
            (response) => {
                this.seller = response
                sessionStorage.setItem("seller", JSON.stringify(this.seller));
                sessionStorage.setItem("sellerProducts", JSON.stringify(this.seller.products));
                // sessionStorage.setItem("sellerRecProducts", JSON.stringify(this.recommendedProduct.seller));
                sessionStorage.setItem("userType", JSON.stringify("Seller"));
                sessionStorage.setItem("orders", JSON.stringify(this.seller.products));//for try
                this.router.navigate(['/sellerHome']);
            }, error => this.errorMessage = <any>error
        )
    }
}