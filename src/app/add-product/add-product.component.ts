import { Component, OnInit } from '@angular/core';
import { Product } from '../product';
import { ProductService } from '../product.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent  implements OnInit{

  product : Product = new Product();

  constructor(private productService : ProductService,private router:Router) {

  }

  saveProduct(){
   this.productService.addProduct(this.product).subscribe(data =>{
    console.log("saved..");
    this.goToProductList();
   },
  error => console.log(error));  
  }

  goToProductList(){
    this.router.navigate([""]);
  }

  onSubmit(){
    console.log(this.product);
    this.saveProduct();
  }

  ngOnInit(): void {
    
  }

}
