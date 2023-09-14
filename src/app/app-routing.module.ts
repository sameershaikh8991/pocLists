import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProductListComponent } from './product-list/product-list.component';
import { AddProductComponent } from './add-product/add-product.component';
import { UpdateProductComponent } from './update-product/update-product.component';

const routes: Routes = [
  // {path :"product",component : ProductListComponent},
  {path :'' ,component:ProductListComponent},
  {path :'add-product' ,component:AddProductComponent},
  {path :'update-product/:id' ,component:UpdateProductComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
