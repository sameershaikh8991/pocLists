import React, { useState,useEffect } from 'react'
import productService from '../services/productService';
import {Link, useHistory,useParams } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';

const AddProductComponent = () => {

    const[productName,setproductName] = useState('');
    const[productDesc,setproductDesc] = useState('');
    const[productPrice,setproductPrice] = useState('');
    const[productImage,setproductImage] = useState('');
    const history = useNavigate();

    const {id} = useParams();

    const saveOrUpdateProduct = (e) =>{
        e.preventDefault();

        const product = {productName,productDesc,productPrice,productImage};

        if(id){
            productService.updateProduct(id,product).then((response) =>{
                history("/");
            }).catch((error) =>{
                console.log(error);
            })

        }
        else{
            console.log(product);    
            productService.createProduct(product).then((response) =>{
                console.log(response.data);
                history("/");
    
            }).catch((e) =>{
                console.log(e);
            });
        }
    }

    useEffect(() => {
        productService.getProductById(id).then((response) =>{
            setproductName(response.data.productName);
            setproductDesc(response.data.productDesc);
            setproductPrice(response.data.productPrice);
            setproductImage(response.data.productImage);
        }).catch((error) =>{
            console.log(error);
        })
      
    }, [])


    const title=() =>{
        if(id){
            return<h2 className='text-center'>Update Product</h2>
        }
        else{
            return<h2 className='text-center'>Add Product</h2>
        }
    }
    

    return (
        <div>

            <div className="container">
              {
                title()
              }
                <form>
                    <div className="form-group">
                        <label htmlFor="productName">Product Name</label>
                        <input type="text"
                         className="form-control" 
                         id="productName" 
                         placeholder="Enter product name"
                         value={productName}
                         onChange={(e) => setproductName(e.target.value)} />
                    </div>

                    <div className="form-group">
                        <label htmlFor="productDesc">Product Desc</label>
                        <input type="text" 
                        className="form-control" 
                        id="productDesc" 
                        placeholder="product Desc" 
                        value={productDesc}
                        onChange={(e) => setproductDesc(e.target.value)}/>
                    </div>

                    <div className="form-group">
                        <label htmlFor="productPrice">Product price</label>
                        <input type="number" 
                        className="form-control" 
                        id="productDesc" 
                        placeholder="product price" 
                        value={productPrice}
                        onChange={(e) => setproductPrice(e.target.value)}/>
                    </div>

                    <div className="form-group">
                        <label htmlFor="productPrice">Product image url</label>
                        <input type="text" 
                        className="form-control" 
                        id="productImage" 
                        placeholder="product Image" 
                        value={productImage}
                        onChange={(e) => setproductImage(e.target.value)}/>
                    </div>

                    <button type="submit" className="btn btn-primary" onClick={(e) => saveOrUpdateProduct(e)}>Submit</button>
                </form>
            </div>
        </div>
    )
}

export default AddProductComponent