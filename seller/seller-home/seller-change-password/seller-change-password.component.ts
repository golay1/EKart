import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Seller } from '../../../shared/models/seller';
import { SellerChangePasswordService } from './seller-change-password.service';
import { LoginValidators } from '../../../shared/validators/login.validator';
import { Router } from '@angular/router';
import { timeout } from 'q';
@Component({
    selector: 'seller-change-password',
    templateUrl: './seller-change-password.component.html'
})
export class SellerChangePasswordComponent {

    seller: Seller;
    sellerToUpdate: Seller;
    sellerString: string;
    updateUserDetailsForm: FormGroup;
    errorMessage: string;
    successMessage: string;
    infoMessage: any;
    constructor(private fb: FormBuilder, private changePasswordService: SellerChangePasswordService, private router:Router) {

    }

    ngOnInit() {

        this.seller = JSON.parse(sessionStorage.getItem("seller"));
        this.createForm();

    }
    createForm() {
        // let emailId = this.seller.emailId;
        let pswd=this.seller.password;
        this.updateUserDetailsForm = this.fb.group({
            password: ["", [Validators.required, LoginValidators.validatePassword]],
            newPassword: ["", Validators.required],
            confirmNewPassword: ["", Validators.required],
            emailId: [this.seller.emailId, [Validators.required]]
        });

        this.updateUserDetailsForm.get('confirmNewPassword').setValidators([Validators.required, LoginValidators.confirmPassword(this.updateUserDetailsForm.get('newPassword'))])
    }
    updateSellerPassword() {
        this.errorMessage = null;
        this.successMessage = null;
        this.infoMessage = null;
        this.sellerToUpdate = this.updateUserDetailsForm.value as Seller;

        this.changePasswordService.updatePassword(this.sellerToUpdate).subscribe(
            (response) => {
                this.successMessage = response;
                this.updateUserDetailsForm.reset();

                this.infoMessage = "Will be logged out in 5 seconds";
                sessionStorage.clear();
                setTimeout(()=> this.router.navigate(['/seller']), 5000)
            }, error => this.errorMessage = <any>error
        )
    }
}