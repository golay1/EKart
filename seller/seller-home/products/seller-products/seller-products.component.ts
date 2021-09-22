import { Component, OnInit, Input } from '@angular/core';
import { Product } from '../../../../shared/models/product';
import { SellerProductsService } from './seller-products.service';
import { Seller } from '../../../../shared/models/seller';


@Component({
  selector: 'app-seller-products',
  templateUrl: './seller-products.component.html'
})

export class SellerProductsComponent implements OnInit {

  errorMessage: string = "";
  successMessage: string = "";
  seller: Seller
  product
  productCategoryList: string[]
  @Input()
  productRecieved: Product
  productList: Product[]
  productToBeModified: Product
  displayProducts: Boolean

  constructor(private SellerProductsService: SellerProductsService) { }

  ngOnInit() {
    this.SellerProductsService.getProductCategories()
      .subscribe(productCategoryList => {
        this.productCategoryList = productCategoryList
      })
    // .catch(product =>{
    //   this.errorMessage = product.errorMessage;
    // })
    this.productList = JSON.parse(sessionStorage.getItem("sellerProducts"));
    this.displayProducts = true
    this.seller = JSON.parse(sessionStorage.getItem("seller"));

  }

  viewProductDetails(product: Product) {
    this.productToBeModified = new Product
    this.productToBeModified.productId = product.productId
    this.productToBeModified.name = product.name
    this.productToBeModified.description = product.description
    this.productToBeModified.discount = product.discount
    this.productToBeModified.category = product.category
    this.productToBeModified.brand = product.brand
    this.productToBeModified.price = product.price
    this.productToBeModified.quantity = product.quantity
    this.displayProducts = false
  }

  modifyProductDetails() {
    this.SellerProductsService.updateProductDetails(this.productToBeModified).subscribe(
      (response) => {
        this.successMessage = response.successMessage;

        console.log("response____"+response)
        console.log("response____"+this.successMessage)


        this.errorMessage = ""
        for (let product1 of this.productList) {
          if (product1.productId == this.productToBeModified.productId) {
            product1.name = this.productToBeModified.name
            product1.description = this.productToBeModified.description
            product1.discount = this.productToBeModified.discount
            product1.category = this.productToBeModified.category
            product1.brand = this.productToBeModified.brand
            product1.price = this.productToBeModified.price
            product1.quantity = this.productToBeModified.quantity
            // Object.assign(product1, this.productToBeModified);
          }
        }
        this.displayProducts = true
        sessionStorage.setItem("sellerProducts", JSON.stringify(this.productList));
      }, error => {
        this.errorMessage = <any>error
        console.log(error)
        this.successMessage = "";
        this.displayProducts = true;
      }
    )
  }

  removeProduct(product: Product) {
    product.sellerEmailId = this.seller.emailId;
    this.SellerProductsService.removeProductFromSeller(product).subscribe(
      (response) => {
        console.log(JSON.stringify(response));
        this.successMessage = response.successMessage;
        this.errorMessage = ""
        let newProductList: Product[] = []
        for (let product1 of this.productList) {
          if (product1.productId != product.productId) {
            newProductList.push(product1)
          }
        }
        this.productList = newProductList
        sessionStorage.setItem("sellerProducts", JSON.stringify(this.productList));
      }, error => {
        this.errorMessage = <any>error
        this.successMessage = "";
      }
    )

  }

  recommendProduct(productId: string){
    this.successMessage = "";

    this.SellerProductsService.recommendNewProduct(this.seller.emailId, productId).subscribe(
      error => {
        this.errorMessage = <any>error
        this.successMessage = "The item has been sucessfully recommended!";
      }
    )
    if(this.successMessage==""){this.errorMessage="Item has already been recommended!"}
  }
}
