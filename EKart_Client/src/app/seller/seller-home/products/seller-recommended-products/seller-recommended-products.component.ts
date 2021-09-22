import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/shared/models/product';
import { Seller } from 'src/app/shared/models/seller';
import { SellerRecommendedProductsService } from './seller-recommended-products.service';

@Component({
  selector: 'recommended-products',
  templateUrl: './seller-recommended-products.component.html'
})
export class SellerRecommendedProductsComponent implements OnInit {
  errorMessage: string = "";
  successMessage: string = "";
  productRecommendList: Product;
  productArray: Product[]; 
  seller: Seller;

  cp: number = 1;
  

  constructor(private SellerRecommendedProductService: SellerRecommendedProductsService) {
  }

  ngOnInit() {
    this.SellerRecommendedProductService.getRecommendedProduct().subscribe(
      productRecommendList => {
        this.productRecommendList = productRecommendList;
    })

    this.seller = JSON.parse(sessionStorage.getItem("seller"));
  }

  deleteRecommendedProduct(productId: string) {
    this.SellerRecommendedProductService.deleteRecommendedProduct(this.seller.emailId, productId).subscribe(
      error => {
        this.errorMessage = ""
        this.successMessage = "Recommended Product deleted Sucessfully. Reloading page."; 
      }
    )
    window.scrollTo(0,0); //scrolls to top of page to see success message
    setTimeout(function(){window.location.reload();}, 3000); //reloads the page after 3 seconds
  }
}

