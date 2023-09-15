import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from './product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {


  private baseUrl ="http://localhost:9099/api/v1/product";
  constructor(private httpClient : HttpClient) {


   }

   getProductList():Observable<Product[]>{
    return this.httpClient.get<Product[]>(`${this.baseUrl}`);
  }

  addProduct(file: File,
    productName: string,
    productDesc: string,
    productPrice: number):Observable<any>{

    // console.log("product",product)
    const formData: FormData = new FormData();
    formData.append('file', file); // Append the file with the name 'file'
    formData.append('productName', productName);
    formData.append('productDesc', productDesc);
    formData.append('productPrice', String(productPrice)); 


    console.log("my file:",formData.get('file'));
    return this.httpClient.post(`${this.baseUrl}`+"/save",formData);
  }

  getProductById(id:number):Observable<Product>{
    return this.httpClient.get<Product>(`${this.baseUrl}/${id}`);
  }

  updateProduct(id:number,product:Product):Observable<Product>{
    return this.httpClient.put<Product>(`${this.baseUrl}/${id}`,product);
  }
  delete(id:number):Observable<string>{
    return this.httpClient.delete<string>(`${this.baseUrl}/${id}`,{ responseType: 'text' as 'json' });
  }
}
