import React, {Component} from 'react'
import {withTranslation} from "react-i18next";

class Register extends Component {
    constructor(props) {
        super(props);
        //STATE
        this.state = {}
        //BIND
    }

    //CDM

    //RENDER
    render() {
        return (
            <React.Fragment>
                Register
            </React.Fragment>
        ) // end return
    } // end render
} // end class

// Higher Order Component:  Monad Componanet
export default withTranslation()(Register);
