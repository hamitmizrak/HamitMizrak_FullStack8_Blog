import React, { Component } from 'react'

// navbar.css
import './navbar.css';

// i18n için
import { withTranslation } from 'react-i18next';

// Link için
import { Link } from 'react-router-dom';

// Bayrak için
import OtherLanguageReusability from '../../internationalization/OtherLanguageReusability';

// Context
import { LoginAuthenticationContext } from "../../context/LoginContext";

// import et ==> import {Link} from "react-router-dom";
// React BrowserRouter veya HashRouter #/ çözmek için
// <a href="#/" /> Yerine
// <Link  to="/" /> kullan

// CLASS
class Navbar extends Component {

    // Componentte görünür adı      
    static displayName = "Navbar";

    // CONTEXT ALMAK
    static contextType = LoginAuthenticationContext;

    constructor(props) {
        super(props);
        //STATE
        this.state = {}
        //BIND
    }

    //CDM
    /*
    Lifting State up için: burdaki state bir üst dizin olan BlogRouter.jsx taşıdım.
    Ancak Lifting State(props) yerine Context kullandım
    state={
        isLogin:true,
        username:'Hamit'
    }
    */

    //RENDER
    render() {
        // state
        // login , logout
        // Lifting State up için: burdaki state bir üst dizin olan BlogRouter.jsx taşıdım
        // const {isLogin, username} = this.state;
        // Lifting olması için önceden state alıyordum artık props'tan alıyorum.
        // const { isLogin, username, systemLogoutOut } = this.props; //Context ekledim diye kaldırdım

        // CONTEXT
        const { state, logout } = this.context;
        const { isLogin, username, rolesId, rolesName } = state;

        // Login ve Register Button
        let loginRegisterLink = (
            <ul className="navbar-nav ml-auto mt-2 mt-lg-0">
                <li className="nav-item">
                    {/* Login Page */}
                    <Link className="nav-link" to={'/login'}>{this.props.t('Login')}</Link>
                </li>
                <li className="nav-item">
                    {/* Register Page */}
                    <Link className="nav-link" to={`/register/create`}>{this.props.t('Register')}</Link>
                </li>
            </ul>
        ); //end loginRegisterLink

        // role admin ve SuperAdmin (Sadece Admin ile SuperAdmin görebilir)
        let rolesRegisterLink = (
            <li className="nav-item dropdown">
                <a className="nav-link dropdown-toggle" href=""
                    id="dropdownId" data-bs-toggle="dropdown" aria-haspopup="true"
                    aria-expanded="false">Role</a>
                <div className="dropdown-menu" aria-labelledby="dropdownId">
                    <Link className="dropdown-item" to={'/roles/list'}>Role List</Link>
                    <Link className="dropdown-item" to={'/roles/create'}>Role Create</Link>
                </div>
            </li>
        );

        // Eğer login ise navbarda gösterilen Login Register yerine username ve logout gelsin
        // KULLANICI GİRİŞ YAPMIŞSA
        if (isLogin) {
            loginRegisterLink = (
                <React.Fragment>
                    <ul className="navbar-nav  mt-2 mt-lg-0">
                        <li className="nav-item">
                            <Link className="nav-link" to={'/blog/list'}>{this.props.t('blog')}</Link>
                        </li>

                        {/* RolesId:1 (SuperAdmin) veya RolesId=2 (Admin) ise Role sayfasını göster */}
                        {(rolesId == 1 || rolesId == 2) && rolesRegisterLink}

                        <li className="nav-item dropdown">
                            <Link className="nav-link dropdown-toggle text-danger" id="dropdownId" data-bs-toggle="dropdown" aria-haspopup="true"
                                aria-expanded="false" to={`/admin/${username}`}>{username.substring(0, 6).toUpperCase()}</Link>
                            <div className="dropdown-menu" aria-labelledby="dropdownId">
                                <Link className="dropdown-item"  to={`/admin/${username}`}>{username.substring(0, 6).toUpperCase()}</Link>
                                <h6 className="dropdown-header">Section header</h6>
                                <div className="dropdown-divider"></div>
                                <Link className="dropdown-item" onClick={logout} style={{ cursor: "pointer" }} to={'/'}>{this.props.t('logout')}</Link>
                            </div>
                        </li>
                    </ul>
                </React.Fragment>
            )
        } //end isLogin

        //RETURN
        return (
            <React.Fragment>
                <div className="navbar">
                    <nav className="navbar navbar-expand-sm navbar-dark bg-dark fixed-top">
                        <div className="container">
                            <Link className="navbar-brand" to={'/'}><i className={this.props.logo}></i></Link>
                            <button className="navbar-toggler d-lg-none" type="button" data-bs-toggle="collapse"
                                data-bs-target="#collapsibleNavId" aria-controls="collapsibleNavId"
                                aria-expanded="false" aria-label="Toggle navigation">
                                <span className="navbar-toggler-icon"></span>
                            </button>
                            <div className="collapse navbar-collapse" id="collapsibleNavId">
                                <ul className="navbar-nav me-auto mt-2 mt-lg-0">
                                    <li className="nav-item">
                                        <Link className="nav-link active" to={'/'}>{this.props.t('home')}</Link>
                                    </li>
                                    <li className="nav-item">
                                        <Link className="nav-link " to={'/'}>{this.props.contact}</Link>
                                    </li>
                                    <li className="nav-item dropdown">
                                        <a className="nav-link dropdown-toggle" href=""
                                            id="dropdownId" data-bs-toggle="dropdown" aria-haspopup="true"
                                            aria-expanded="false">Dropdown</a>
                                        <div className="dropdown-menu" aria-labelledby="dropdownId">
                                            <Link className="dropdown-item " to={'/'}>1</Link>
                                            <Link className="dropdown-item " to={'/'}>2</Link>
                                        </div>
                                    </li>
                                </ul>

                                {/* değişken eklerek kısaltma ekledim */}
                                {loginRegisterLink}

                                {/* Dil için  */}
                                <OtherLanguageReusability />
                                <form className="d-flex my-2 my-lg-0">
                                    <input className="form-control me-sm-2" type="text"
                                        placeholder={this.props.t('search')} />
                                    <Link className="btn btn-outline-success my-2 my-sm-0" to={'/'}>{this.props.t('search')}</Link>
                                </form>
                            </div>
                        </div>
                    </nav>
                </div>
            </React.Fragment>
        )// end return
    }//end render
} //end class

//defaultProps
Navbar.defaultProps = {
    aboutMe: "Hakkımızda",
    contact: "İletişim"
}

//Header.propTypes: props türü
/*
Header.propTypes = {
  homepage: PropTypes.string.isRequired
};
*/

// i18n sarmaladı
export default withTranslation()(Navbar);
