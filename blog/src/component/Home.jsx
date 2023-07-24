import React, { Component } from 'react'

import Body from "./main/Body";

// i18n için
import { withTranslation } from 'react-i18next'

// Bayrak için
import OtherLanguageReusability from '../internationalization/OtherLanguageReusability';

// Context
import { LoginAuthenticationContext } from "../context/LoginContext";

class Home extends Component {

    // Componentte görünür adı      
    static displayName = "Home";

    // CONTEXT ALMAK
    static contextType=LoginAuthenticationContext;
    
    constructor(props) {
        super(props);
        //STATE
        this.state = {}
        //BIND
    }

    // CDM
    componentDidMount() {}

    //RENDER
    render() {

    // CONTEXT
    const { state, logout } = this.context;
    const { isLogin, username, rolesId, rolesName } = state;

        return (
            <React.Fragment>
                <Body/>
            </React.Fragment>
        ) // end return
    } // end render
} // end class

// Higher Order Component:  Monad Componanet
export default withTranslation()(Home);