import { Component, OnInit } from '@angular/core';
import { Product } from '../product';
import { ProductService } from '../product.service';
import { Router } from '@angular/router';
import { FormControl, FormGroup,FormBuilder,Validator, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent  implements OnInit{


  addProduct: FormGroup;

  constructor(private productService : ProductService,private router:Router,  private formBuilder: FormBuilder,) {

    this.addProduct = this.formBuilder.group({
      // Other form controls...
      productName: ['', Validators.required], 
      productDesc : ['', Validators.required], 
      productPrice : ['', Validators.required], 
      productImage : ['', Validators.required], 
    });

  }

  product : Product = new Product();

  saveProduct(){

    if (this.addProduct.valid) {
      this.productService.addProduct(this.product).subscribe(data =>{
        console.log("saved..");
    
        this.goToProductList();
       },
      error => console.log(error)); 

    }

    else{
      alert('Please fill the form ');
    }


    
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
