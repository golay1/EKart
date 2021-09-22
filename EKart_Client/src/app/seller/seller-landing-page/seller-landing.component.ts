import { Component } from "@angular/core";


@Component({
    selector:"seller-landing",
    templateUrl:'./seller-landing.component.html',
    styleUrls:['./seller-landing.component.css']
})
export class SellerLandingComponent{
    activity:string="login";
    
    userActivity(option:string){
        this.activity=option;
    }
}