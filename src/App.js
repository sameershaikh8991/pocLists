
import './App.css';
import { BrowserRouter as Router,Route,Routes } from 'react-router-dom';
import ListProducts from './myComponents/ListProducts';
import HeaderComponent from './myComponents/HeaderComponent';
import AddProductComponent from './myComponents/AddProductComponent';

function App() {
  return (
    <div >

      <HeaderComponent/>
      <Router>
        <Routes>
                <Route exact path='/' element={<ListProducts />}></Route>
                <Route exact path='/product' element={<ListProducts />}></Route>
                <Route exact path='/add-product' element={<AddProductComponent />}></Route>
                <Route  path="/edit-product/:id" element={<AddProductComponent />}></Route>
              
        </Routes>

    </Router>
    </div>
  );
}

export default App;
