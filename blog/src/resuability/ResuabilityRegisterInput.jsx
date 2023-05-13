// rfc 
// Resuability: kendini tekrar eden bloklar için

import React from 'react'

//Stateless (Function Component)
export default function ResuabilityRegisterInput(props) {

    //object destructing
    const { label, type, id, name, placeholder, onChangeInput, error, focus } = props;

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
            <div className="form-group">
                <label htmlFor={id}>{label}</label>
                <input type={type} id={id} name={name}
                    className={className} placeholder={placeholder}
                    autoFocus={focus} onChange={onChangeInput} />
                <div className={"text-danger"} >{error}</div>
            </div>
        </React.Fragment>
    ) //end return
} // end function
