import React,{ useState } from 'react'

export default function About() {

    const [myStyle, setMyStyle] = useState({
        color : 'black',
        backgroundColor: "white"
    })


    const [text, setText] = useState('Enable Dark mode');

  const toggleStyle = () =>{

        if(myStyle.color =='white'){
            setMyStyle({
                color : 'black',
                backgroundColor: "white"
            })
            setText("Enable Dark mode")
        }
        else{
            setMyStyle({
                color : 'white',
                backgroundColor: "black"
            })
            setText("Disable Dark mode")
        }
    
    }

  return (

    <div className="py-5 text-center container" style={myStyle}>
        
        <div className="col-lg-6 col-md-8 mx-auto">
            <h1 className="fw-light">About Us</h1>
            <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Hic quis sunt similique expedita reiciendis placeat, cumque ea aut cum. Excepturi rem placeat tempore quam necessitatibus accusamus cupiditate cumque praesentium rerum.</p>
            <p>
            <button className="btn btn-primary my-2" onClick={toggleStyle} >{text}</button>
            </p>
    
        </div>
    
  </div>
  )
}
