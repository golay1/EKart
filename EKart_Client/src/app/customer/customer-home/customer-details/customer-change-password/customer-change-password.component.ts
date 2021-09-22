import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Customer } from '../../../../shared/models/customer';
import { CustomerChangePasswordService } from './customer-change-password.service';
import { LoginValidators } from '../../../../shared/validators/login.validator';
import { Router } from '@angular/router';
@Component({
    selector: 'customer-change-password',
    templateUrl: './customer-change-password.component.html'
})
export class CustomerChangePasswordComponent {

    customer: Customer;
    customerToUpdate: Customer;
    customerString: string;
    pass:any;
    updateUserDetailsForm: FormGroup;
    errorMessage: string;
    successMessage: string;
    infoMessage: string;
    constructor(private fb: FormBuilder, private changePasswordService: CustomerChangePasswordService, private router: Router) {
    }

    ngOnInit() {
        this.customer = JSON.parse(sessionStorage.getItem("customer"));
        this.createForm();
    }
    createForm() {
        let emailId = this.customer.emailId;
        let password=this.customer.password;
        this.updateUserDetailsForm = this.fb.group({
           password:[password, [Validators.required,LoginValidators.validatePassword]],
            newPassword: ["", [Validators.required, LoginValidators.validatePassword]],
            confirmPassword: [""],
            emailId: [emailId, [Validators.required]]
        });
        this.updateUserDetailsForm.get('confirmPassword').setValidators([Validators.required, LoginValidators.confirmPassword(this.updateUserDetailsForm.get('newPassword'))])
    }
    updateCustomerPassword() {
        this.errorMessage = null;
        this.successMessage = null;
        this.customerToUpdate = this.updateUserDetailsForm.value as Customer;

        this.changePasswordService.updatePassword(this.customerToUpdate).subscribe(
            (response) => {
                this.successMessage = response;
                this.updateUserDetailsForm.reset();
                this.infoMessage = "Will be logged out in 5 seconds";
                sessionStorage.clear();
                setTimeout(()=> this.router.navigate(['/']), 5000)
            }, error => this.errorMessage = <any>error
        )
    }
}