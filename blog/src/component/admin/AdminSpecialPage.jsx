// React
import React, {Component} from 'react';

// Dil seceneği
import {withRouter} from "react-router-dom";

// Context
import {LoginAuthenticationContext} from "../../context/LoginContext";
import Header from "../main/Navbar";
import Body from "../main/Body";
import ProfileCard from "../profile/ProfileCard";

// AdminPage.jsx =>  <AdminSpecialPage userInformation={this.props.match.params.username  }/>
// buradaki userInformation verileri almak istediğimizde ister userInformation ister withRouter ile gödnerebiliriz.

class AdminSpecialPage extends Component {

    // Componentte görünür adı      
    static displayName = "Admin_Special_Page";

    // CONTEXT ALMAK
    static contextType = LoginAuthenticationContext;

    // CONSTRUCTOR
    constructor(props) {
        super(props);
        //BIND
    }

    // CDM
    componentDidMount() {}

    // RENDER
    render() {

        // CONTEXT
        const {state, logout} = this.context;
        const {isLogin, username, rolesId, rolesName} = state;

        /*AdminPage username bilgisi aldık*/
        const usernamePath = this.props.match.params.username;

        let message = "Düzenleme yapamazsınınız sisteme login veya ilgili kullanıcı değilsiniz";
        if (usernamePath === username) {
            message = "düzenleme yapabilirsiniz";
        }

        const failedRoleAdminLink = (
            <div className="alert alert-danger">{message} </div>
        );

        const successRoleAdminLink = (
            <React.Fragment>
                <div className="alert alert-primary">{message}- <span
                    className="text-success">Kullanıcı:</span> {usernamePath} - {rolesName}</div>

            </React.Fragment>
        );

        // RETURN
        return (
            <>
                {/*Eğer sistemde kullanıcı varsa ve ilgili kullanıcıysa*/}
                {(usernamePath != username) ? failedRoleAdminLink : successRoleAdminLink}
               <ProfileCard />
            </>
        ); //end return
    } //end render
} //end AdminSpecialPage

// withRouter ekledim
// export default AdminSpecialPage;
export default withRouter(AdminSpecialPage);