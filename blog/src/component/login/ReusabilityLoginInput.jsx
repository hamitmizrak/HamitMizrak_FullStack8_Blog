//rfc => TAB (function Component)
import React, { Component } from 'react'

export default function ReusabilityLoginInput(props) {
    //obhject destructing
    const { label,type, name,id,placeholder, onChangeInput,error,focus } = props;
    //daha sade örünmesini sağladım
    const className = name ? "is-invalid form-control mb-3" : "form-control mb-3";
    //return
    return (
        <>
            <div className="form-group">
                <label htmlFor='username'>{label}</label>
                <input type={type} id={id} name={name} className={className}
                    placeholder={placeholder}  onChange={onChangeInput} autoFocus={focus} />
                <div className="invalid-feedback">{error}</div>
            </div>
        </>
    )
}



