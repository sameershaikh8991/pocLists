
import './App.css';
import About from './components/About';
import Navbar from './components/Navbar';
import React ,{ useState } from 'react';

import {
  createBrowserRouter,
  RouterProvider,
  Route,
  Link,
} from "react-router-dom";

let name ="sameer"
function App() {
  const [mode, setMode] = useState('light');

  const toggleMode =() =>{
    if(mode == 'light'){
      setMode('dark');

      document.body.style.backgroundColor ="grey";
    }
    else{
      setMode('light');
      document.body.style.backgroundColor ="white";
    }
  }
  return (
    <>
      <Navbar title="iText" mode={mode} toggleMode ={toggleMode}/>
      <div className="container my-3">
      {/* <TextForm heading="Enter the text to analyze"/> */}
      <About />
      </div>

</>
  );
}

export default App;
