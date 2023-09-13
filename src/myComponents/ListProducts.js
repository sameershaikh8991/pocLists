import React, { useState, useEffect } from 'react'
import productService from '../services/productService';
import { Link } from 'react-router-dom';

export default function ListProducts() {

    const [products, setProducts] = useState([]);

    useEffect(() => {

        getAllProduct();

    }, [])

    const getAllProduct = () => {
        productService.getAllProducts().then((response) => {
            setProducts(response.data)
            console.log(response.data);
        }).catch(error => {
            console.log(error);
        })

    }

    const deleteProduct = (productId) => {

        productService.deleteProduct(productId).then((response) => {
            console.log(response.data)
            getAllProduct();
        }).catch((error) => {
            console.log(error);
        })



    }

    

    return (
        <div className="container">
            <h2 style={{textAlign:"center",marginTop: "74px",marginBottom: "35px"}}>List of Product</h2>
            <div style={{textAlign: "center"}}>
                <Link to="/add-product" className="btn btn-primary mb-4" >Add Product</Link>

            </div>
            <table className="table table-bordered">
                <thead>
                    <tr>
                        <th scope="col" className='text-center'>Product Id</th>
                        <th scope="col" className='text-center'>Product Image</th>
                        <th scope="col" className='text-center'>Product Name</th>
                        <th scope="col" className='text-center'>Product Desc</th>
                        <th scope="col" className='text-center'>Price</th>
                        <th scope="col" className='text-center'>Action</th>
                    </tr>
                </thead>
                <tbody>

                    {products.length === 0 ? (
                        <tr>
                            <td colSpan="5" className='text-center'>No content to show</td>
                        </tr>
                    ) : (
                        products.map(product => (
                            <tr key={product.productId}>
                                <td>{product.productId}</td>
                                <td><img src={product.productImage}  alt="we will add soon" /></td>
                                <td>{product.productName}</td>
                                <td>{product.productDesc}</td>
                                <td>{product.productPrice}</td>
                                <td>
                                    <Link className="btn btn-success mx-3" to={`/edit-product/${product.productId}`}>Update</Link>

                                    <button className="btn btn-danger" onClick={() => deleteProduct(product.productId)}>Delete</button>

                                </td>
                            </tr>
                        ))
                    )}
                </tbody>
            </table>
        </div>



    )
}
