import { Component, OnInit } from '@angular/core';
import { Product } from '../product';
import { ProductService } from '../product.service';
import { Router } from '@angular/router';
import { SafeUrl } from '@angular/platform-browser';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  [x: string]: any;

  products : Product[] | undefined;

  constructor(private productService:ProductService, private router : Router){
    
  }

  ngOnInit(): void {
    this.getProduct();
      
  }
  getProduct() {
  
  this.productService.getProductList().subscribe( data =>{
    this.products =data;
    console.log("products",this.products.values);
  })
  }

  updateProduct(id:number){
    this.router.navigate(['update-product',id])
  }

  createImageUrl(blob: Blob): any {
    console.log("img",blob);
    if (blob instanceof Blob) {
      const objectURL = URL.createObjectURL(blob);
      console.log("img",objectURL)
      return this['sanitizer'].bypassSecurityTrustUrl(objectURL);
    } else {
      return ""; // Handle the error as needed
    }

    console.log(blob);

  }


  sanitizeImage(imageData: Blob): SafeUrl {
   
    const objectURL = URL.createObjectURL(imageData);
    return this['sanitizer'].bypassSecurityTrustUrl(objectURL);
  }

  deleteProduct(id:number){
    this.productService.delete(id).subscribe( data =>{
      console.log("deleted");
      this.getProduct();
    });
  }
}
