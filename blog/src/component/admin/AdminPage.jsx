// react
import React, { Component } from 'react'

// Dil secenegi
import { withTranslation } from 'react-i18next';

// AdminPage
import AdminSpecialPage from "./AdminSpecialPage";

// LoginAuthenticationContext
import {LoginAuthenticationContext} from "../../context/LoginContext";

// CLASS
class adminPage extends Component {

    // Componentte görünür adı
    static displayName = "Admin_page";

    // CONTEXT ALMAK
    static contextType = LoginAuthenticationContext;

    // CONSTRUCTOR
    constructor(props) {
        super(props);
        // BIND
    }

    // CDM
    componentDidMount() {}

    // RENDER
    render() {

        // CONTEXT
        const { state, logout } = this.context;
        const { isLogin, username, rolesId, rolesName } = state;

        //RETURN
        return (
            <>
                <h1 className="mt-5 display-1">{rolesName}</h1>
                {/*1.YOL*/}
                {/*<AdminSpecialPage userInformation={this.props.match.params.username  }/>*/}
                <AdminSpecialPage username={username} rolesname={rolesName} rolesid={rolesId}/>
            </>
        )
    }
}

// export default UserRegister
// Higher Order Component: monad componenti başka bir componentin içine  ekleyip oradanda yeni sonuclar elde etmek
export default withTranslation()(adminPage)
