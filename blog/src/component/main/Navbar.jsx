import React, { Component } from 'react'

// navbar.css
import './navbar.css';

//i18n için
import { withTranslation } from 'react-i18next';

//Link için
import { Link } from 'react-router-dom';

// Bayrak için
import OtherLanguageReusability from '../../internationalization/OtherLanguageReusability';

// CLASS
class Navbar extends Component {
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
            // <div>Navbar</div>
            //  <>Navbar</>
            <React.Fragment>
                <div className="navbar">
                    <nav className="navbar navbar-expand-sm navbar-dark bg-dark fixed-top">
                        <div className="container">
                            <a href={this.props.homePageLink} className='navbar-brand' to="/"><i className={this.props.logo}></i></a>
                            <a className="navbar-brand" href="blog/src/component/main/Header#"></a>
                            <button className="navbar-toggler d-lg-none" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavId" aria-controls="collapsibleNavId"
                                aria-expanded="false" aria-label="Toggle navigation">
                                <span className="navbar-toggler-icon"></span>
                            </button>
                            <div className="collapse navbar-collapse" id="collapsibleNavId">
                                <ul className="navbar-nav me-auto mt-2 mt-lg-0">
                                    <li className="nav-item">
                                        <a className="nav-link active" href="blog/src/component/main/Header#" aria-current="page">{this.props.t('home')} <span className="visually-hidden">(current)</span></a>
                                    </li>
                                    <li className="nav-item">
                                        <a className="nav-link" href="blog/src/component/main/Header#">{this.props.contact}</a>
                                    </li>
                                    <li className="nav-item dropdown">
                                        <a className="nav-link dropdown-toggle" href="blog/src/component/main/Header#" id="dropdownId" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Dropdown</a>
                                        <div className="dropdown-menu" aria-labelledby="dropdownId">
                                            <a className="dropdown-item" href="blog/src/component/main/Header#">Action 1</a>
                                            <a className="dropdown-item" href="blog/src/component/main/Header#">Action 2</a>
                                        </div>
                                    </li>
                                </ul>
                                <ul className="navbar-nav ml-auto mt-2 mt-lg-0">
                                    <li className="nav-item">
                                        {/* Login Page */}
                                        <Link className="nav-link" to={'/admin'}>{this.props.t('admin')}</Link>
                                    </li>
                                    <li className="nav-item">
                                        {/* Login Page */}
                                        <Link className="nav-link" to={'/login'}>{this.props.t('Login')}</Link>
                                    </li>
                                    <li className="nav-item">
                                        {/* Register Page */}
                                        <Link className="nav-link" to={`/register`}>{this.props.t('Register')}</Link>
                                    </li>
                                </ul>
                                {/* Dil için  */}
                                <OtherLanguageReusability />
                                <form className="d-flex my-2 my-lg-0">
                                    <input className="form-control me-sm-2" type="text" placeholder={this.props.t('search')} />
                                    <button className="btn btn-outline-success my-2 my-sm-0" type="submit">{this.props.t('search')}</button>
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

//i18n sarmaladı
export default withTranslation()(Navbar);
