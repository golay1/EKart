import { Address } from "./address";
import { Cart } from './cart';


export class Customer {
    // private String emailId;
    // private String name;
    // private String password;
    // private String phoneNumber;
    // private List<Address> addresses;
    emailId: string;
    name: string;
    password: string;
    newPassword: string;
    phoneNumber: string;
    addresses: Address[];
    customerCarts: Cart[];
    //check for customer carts
}