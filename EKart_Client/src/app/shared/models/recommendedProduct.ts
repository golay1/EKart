import { Product } from "./product";
import { Seller } from "./seller";

export class RecommendedProduct {
    recommendationId: number;
    recommendationTimeStamp: Date;
    recommendationStatus: string;
    product: Product;
    seller: Seller; 
}