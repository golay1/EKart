
import { Component, OnInit, Input } from "@angular/core";


import { SellerViewOrderService } from "./seller-view-order.service";
import { Orders } from "../../../shared/models/order";
import { Seller } from "../../../shared/models/seller";
import { Product } from '../../../shared/models/product';
import { Address } from 'src/app/shared/models/address';



@Component({
    selector: 'seller-view-order',
    templateUrl: './seller-view-order.component.html'

})

export class SellerViewOrderComponent {
    errorMessage: string = "";
    successMessage: string = "";
    @Input()
    currentProduct: Product
    seller: Seller;
    statusList: string[];
    orderData: Orders[];
    newOrder: Orders = null;
    displayOrders: boolean = false;
    updateCheck: boolean = false;
    displayProduct: Product = null;
    addressToDisplay: Address;


    constructor(private sellerViewOrderService: SellerViewOrderService) {

    }

    ngOnInit() {
        this.seller = JSON.parse(sessionStorage.getItem("seller"));
        this.displayProduct=null;
        this.addressToDisplay = null;
        this.newOrder = null;
        this.sellerViewOrderService.getAllOrders(this.seller.emailId)
            .subscribe(response => {
                this.orderData = response
                this.displayOrders = this.orderData.length != 0;
            })
    }
    modifyVariablesOnClick(order: Orders) {
        this.displayProduct=null;
        this.addressToDisplay = null;
        console.log(order)
        this.sellerViewOrderService.getAllOrderStatus()
            .subscribe(response => {
                this.statusList = response
            }, error => this.errorMessage = <any>error

            )
        this.newOrder = Object.assign({}, order);   //clone the order to {} and assigned to newOrder
        this.currentProduct = Object.assign({}, order.product) //clone the order to {} and assigned to current product
        this.newOrder.product = this.currentProduct;
        this.displayOrders = false
    }
    updateStatus(order2: Orders) {
        this.displayProduct=null;
        this.addressToDisplay = null;
        this.errorMessage = null;
        this.successMessage = null;
        this.displayOrders = true;
        this.newOrder = null;
        this.sellerViewOrderService.updateOrder(order2.orderId, order2.orderStatus)
            .subscribe(response => {
                this.successMessage = response
                this.displayOrders = true
                for (let order of this.orderData) {
                    if (order.orderId == order2.orderId) {
                        order.orderStatus = order2.orderStatus
                    }
                }
            }, error => this.errorMessage = <any>error
            )
    }

    displayProductDetails(product: Product){
        this.addressToDisplay = null;
        this.displayProduct=product;
    }

    displayAddressDetails(addressId: number){
        this.displayProduct=null;
        this.addressToDisplay = null;
        this.sellerViewOrderService.getAddress(addressId).subscribe((address)=>{
            this.addressToDisplay = address;
        }, (error)=>{
            this.errorMessage = error;
        });
    }

    cancel(){
        this.displayOrders = true;
        this.newOrder = null;
        this.displayProduct = null;
        this.addressToDisplay = null;
    }

}
