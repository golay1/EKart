import { Component, OnInit } from '@angular/core';
import { FormControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Product } from '../../../../shared/models/product';
import { SellerAddProductService } from './seller-add-product.service';
import { Seller } from '../../../../shared/models/seller';

@Component({
  selector: 'add-product',
  templateUrl: './seller-add-products.component.html'
})
export class SellerAddProductsComponent implements OnInit {
  productToAdd: any;
  errorMessage: string = "";
  productCategoryList: String[];
  addNewproduct: Product;
  productAddForm: FormGroup;
  seller: Seller;
  message: string = "";
  ngOnInit() {
    this.addNewproduct = new Product();
    this.seller = JSON.parse(sessionStorage.getItem("seller"));
    this.createForm();
    this.addProductService.getProductCategories().subscribe((categories: string[])=>{
      this.productCategoryList = categories
    }, () =>{
      this.errorMessage = "Product category was not able to load, Try after some time"
    })
  }

  constructor(private fb: FormBuilder, private addProductService: SellerAddProductService) {
    
  }

  createForm() {
    this.productAddForm = this.fb.group({
      description: new FormControl("", [Validators.required, Validators.minLength(10)]),
      name: ["", Validators.required],
      category: ["", Validators.required],
      brand: ["", Validators.required],
      price: ["", [Validators.required, Validators.min(0)]],
      quantity: ["", [Validators.required, Validators.min(0)]],
      discount: ["", [Validators.required, Validators.min(0)]] 
    });
  }

  AddProduct() {

    this.message = null;
    let productToAdd: Product = this.productAddForm.value as Product;
    productToAdd.sellerEmailId = this.seller.emailId;
    this.addProductService.addProduct(this.productAddForm.value)
      .subscribe((response) => {
        console.log(response)
        this.message = response.successMessage
        let productId: number
        productId = +(this.message.split(":")[1])
        productToAdd.productId = productId
        let productList: Product[] = JSON.parse(sessionStorage.getItem("sellerProducts"))
        productList.push(productToAdd)
        sessionStorage.setItem("sellerProducts", JSON.stringify(productList));
        this.productAddForm.reset();
      },
        error => this.errorMessage = <any>error
      ) 
  }
}
