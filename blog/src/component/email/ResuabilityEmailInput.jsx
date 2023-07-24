// rfc 
// Resuability: kendini tekrar eden bloklar için

import React from 'react'

//Stateless (Function Component)
export default function ResuabilityEmailInput(props) {


    //object destructing
    const {  type, id, name, placeholder, onChangeInput, focus,value,error } = props;

    // bootstrap validation 
    // valid: validation(Doğrulama)
    // invalid-feedback :hatayı göstermiyor 
    // 1.YOL ternary
    // const className = name ? "is-invalid form-control mb-3" : "form-control mb-3";
    // 2.YOL &&
    const className = name && "is-invalid form-control mb-3";

    //RETURN
    return (
        <React.Fragment>
            <div className="form-group mb-3">
                <input
                    type={type} id={id} name={name}
                    className={className} placeholder={placeholder}
                    autoFocus={focus} 
                    onChange={onChangeInput}
                    value={value}/>
                <div className={"text-danger"} >{error}</div>
            </div>
        </React.Fragment>
    ) //end return
} // end function
