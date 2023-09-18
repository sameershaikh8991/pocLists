import { Component, OnInit } from '@angular/core';
import { ProductService } from '../product.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from '../product';
import { FormGroup,FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-update-product',
  templateUrl: './update-product.component.html',
  styleUrls: ['./update-product.component.css']
})
export class UpdateProductComponent implements OnInit {
  
  fileSelected!: File;
  updateProduct: FormGroup;

constructor(private productService : ProductService,
  private route:ActivatedRoute,private routers:Router,private formBuilder: FormBuilder){

    this.updateProduct = this.formBuilder.group({
      productName: [''], 
      productDesc : [''], 
      productPrice : [''],
    });
}

  id!: number;
  product : Product = new Product();

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.productService.getProductById(this.id).subscribe(data =>{
      this.product = data;
    })
    
  }

  goToProductList(){
    this.routers.navigate([""]);
  }

  update(){
    this.productService.updateProduct(this.fileSelected,
      this.updateProduct.get('productName')?.value,
      this.updateProduct.get('productDesc')?.value,
      this.updateProduct.get('productPrice')?.value,
      this.id = this.route.snapshot.params['id']).subscribe(data =>{
      console.log("updated",data);
      this.goToProductList();

    })
  }


  onFileSelected(event: any) {

    this.fileSelected = event.target.files[0];
  }


}
