import { Component, OnInit } from '@angular/core';
import { CustViewRecProdService } from './custviewrecprod.service';

import { Product } from '../../../shared/models/product';
import { CustomerSharedService } from '../customer-shared-service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-custviewrecprod',
  templateUrl: './custviewrecprod.component.html',
  styleUrls: ['./custviewrecprod.component.css']
})
export class CustViewRecProdComponent implements OnInit{
  successMessage: string;
  errorMessage: string;
  productList: Product[];
  cp: number = 1;

  searchText: string;

  viewDetails: boolean = false;
  selectedProduct: Product;

  productListToDisplay: Product[] = [];
  constructor(private viewRecProductService: CustViewRecProdService,
    private sharedService: CustomerSharedService,
    private router: Router,
    private route: ActivatedRoute) {

  }

  ngOnInit() {
    this.getAllProduct();
  }
  getAllProduct() {
    this.viewRecProductService.getRecProducts()
      .subscribe(products => {
        this.productList = products;
        this.productListToDisplay = this.productList;
      }
      );


  }
  setSelectedProduct(product: Product) {
    this.viewDetails = true;
    this.selectedProduct = product;
  }

}
