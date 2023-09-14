import { Component, OnInit } from '@angular/core';
import { ProductService } from '../product.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from '../product';

@Component({
  selector: 'app-update-product',
  templateUrl: './update-product.component.html',
  styleUrls: ['./update-product.component.css']
})
export class UpdateProductComponent implements OnInit {

constructor(private productService : ProductService,
  private route:ActivatedRoute,private routers:Router){

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
    this.productService.updateProduct(this.id,this.product).subscribe(data =>{
      console.log("updated");
      this.goToProductList();

    })
  }


}
