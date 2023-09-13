import axios from "axios";


const PRODUCT_BASE_URL = "http://localhost:9099/api/v1/product";

class ProductService{

    getAllProducts(){
        return axios.get(PRODUCT_BASE_URL);
    }

    createProduct(product){
        return axios.post(PRODUCT_BASE_URL+"/save",product);
    }

    getProductById(productId){
        return axios.get(PRODUCT_BASE_URL +"/"+productId);
    }

    updateProduct(productId,product){
        return axios.put(PRODUCT_BASE_URL +"/"+productId,product);
    }
    deleteProduct(productId){
        return axios.delete(PRODUCT_BASE_URL +"/"+productId);
    }
}


export default new ProductService();