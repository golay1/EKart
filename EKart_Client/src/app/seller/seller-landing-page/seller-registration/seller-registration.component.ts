import { Component, OnInit } from "@angular/core";
import { Seller } from "../../../shared/models/seller";
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { LoginValidators } from "../../../shared/validators/login.validator";
import { SellerRegistrationService } from "./seller-registration.service";

@Component(
    {
        selector: "seller-registration",
        templateUrl: "./seller-registration.component.html"
    }
)
export class SellerRegistrationComponent implements OnInit {

    seller: Seller;
    registerUserForm: FormGroup;
    errorMessage: string;
    successMessage: string;
    constructor(private fb: FormBuilder, private registerService: SellerRegistrationService) {

    }

    ngOnInit() {
        this.seller = new Seller();
        this.createForm();

    }
    createForm() {

        this.registerUserForm = this.fb.group({
            emailId: ['', [Validators.required, LoginValidators.validateEmailId], null],
            name: ['', [Validators.required, LoginValidators.validateName], null],
            phoneNumber: ['', [Validators.required, LoginValidators.validatePhoneNumber], null],
            password: ['', [Validators.required, LoginValidators.validatePassword], null],
            confirmPassword: ["", [Validators.required], null],
            address: ['', [Validators.required], null]
        });
        this.registerUserForm.get('confirmPassword').setValidators([Validators.required, LoginValidators.confirmPassword(this.registerUserForm.get('password'))]);
    }

    registerUser() {
        console.log(this.registerUserForm)
        this.errorMessage = null;
        this.successMessage = null;
        this.seller = this.registerUserForm.value as Seller;

        this.registerService.registerSeller(this.seller).subscribe(
            (response) => {console.log(response);this.successMessage = response; this.registerUserForm.reset()}
            , error => this.errorMessage = <any>error

        )
    }

}