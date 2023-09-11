 
import React ,{ useState } from 'react'

export default function TextForm(props) {


    const handelUpClick = () =>{
        // console.log("uppercase");
        let newText = text.toUpperCase();
        setText(newText);
    }

    const handelOnChange = (event) =>{
        // console.log("on click");
        setText(event.target.value)

    }

    const [text, setText] = useState('Enter text here');
  return (
    <>
    <div className="container">
        <h2>{props.heading}</h2>
        <div className="mb-3">
            <textarea className="form-control" id="myBox" value={text} onChange={handelOnChange} rows="5"></textarea>
        </div>
        <button type="button" className="btn btn-primary" onClick={handelUpClick}>Analyze</button>
    </div>

    <div className="container my-3">
        <h4>Your text summary</h4>
        <p>No of words:{text.split(" ").length}</p>
        <p>No of char: {text.length}</p>
    </div>
    </>
  )
}
