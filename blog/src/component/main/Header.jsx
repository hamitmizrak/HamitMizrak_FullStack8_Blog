import React, { Component } from 'react'
import { withTranslation } from 'react-i18next';
import { Link } from 'react-router-dom';


class Header extends Component {
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
            // <div>Header</div>
            //  <>Header</>
            <React.Fragment>
                <nav className="navbar navbar-expand-sm navbar-dark bg-dark fixed-top">
                    <div className="container">
                        <Link className='navbar-brand' to="/"><i className={this.props.logo}></i></Link>
                        <a className="navbar-brand" href="blog/src/component/main/Header#"></a>
                        <button className="navbar-toggler d-lg-none" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavId" aria-controls="collapsibleNavId"
                            aria-expanded="false" aria-label="Toggle navigation">
                            <span className="navbar-toggler-icon"></span>
                        </button>
                        <div className="collapse navbar-collapse" id="collapsibleNavId">
                            <ul className="navbar-nav me-auto mt-2 mt-lg-0">
                                <li className="nav-item">
                                    <a className="nav-link active" href="blog/src/component/main/Header#" aria-current="page">Home <span className="visually-hidden">(current)</span></a>
                                </li>
                                <li className="nav-item">
                                    <a className="nav-link" href="blog/src/component/main/Header#">Link</a>
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
                                   <Link className="nav-link" to={`/login`}>Login</Link> 
                                </li>
                                <li className="nav-item">
                                    {/* Register Page */}
                                  <Link className="nav-link" to={`/register`}>Register</Link> 
                                </li>
                            </ul>
                            <ul className="navbar-nav ml-auto mt-2 mt-lg-0">
                                <li className="nav-item">
                                    {/* Login Page */}
                                   <Link className="nav-link" to={`/login`}>Login</Link> 
                                </li>
                                <li className="nav-item">
                                    {/* Register Page */}
                                  <Link className="nav-link" to={`/register`}>Register</Link> 
                                </li>
                            </ul>
                            <form className="d-flex my-2 my-lg-0">
                                <input className="form-control me-sm-2" type="text" placeholder="Search" />
                                <button className="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                            </form>
                        </div>
                    </div>
                </nav>
            </React.Fragment>
        )// end return
    }//end render
} //end class

//i18n sarmaladı
export default withTranslation()(Header);
