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

  fileSelected!: File;

  constructor(private productService : ProductService,private router:Router,  private formBuilder: FormBuilder,) {

    this.addProduct = this.formBuilder.group({
      // Other form controls...
      productName: ['', Validators.required], 
      productDesc : ['', Validators.required], 
      productPrice : ['', Validators.required], 
      // productImage : ['', Validators.required], 
    });

  }

  product : Product = new Product();

  saveProduct() {
    if (this.addProduct.valid) {
    
      this.productService.addProduct( 
        this.fileSelected,
        this.addProduct.get('productName')?.value,
        this.addProduct.get('productDesc')?.value,
        this.addProduct.get('productPrice')?.value).subscribe(
        (data) => {
          console.log('Product saved successfully.');
          this.goToProductList();
        }
      );
    } else {
      alert('Please fill in the form correctly.');
    }
  }


  goToProductList(){
    this.router.navigate([""]);
  }

  onSubmit(){
    console.log(this.product);
    this.saveProduct();
  }


  onFileSelected(event: any) {

    this.fileSelected = event.target.files[0];
  }



  ngOnInit(): void {
    
  }

}
