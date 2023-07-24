import React, { Component } from 'react';

// i18n için
import { withTranslation } from 'react-i18next';

// Context
import { LoginAuthenticationContext } from "../../context/LoginContext";

// Email
import Email from '../email/Email';

// Arrow function Date
let newDateYear = () => {
    return new Date().getFullYear();
}

// FUNCTION COMPONENT(Stateless)
class Footer extends Component {

    // Componentte görünür adı      
    static displayName = "Footer"

    constructor(props) {
        super(props);

        //STATE
        this.state = {}

        //BIND
    } //end constructor

    //CDM
    //componentDidMount() {}

    // CONTEXT ALMAK
    static contextType = LoginAuthenticationContext;

    //RENDER
    render() {

        //CONTEXT
        const { state, logout } = this.context;
        const { isLogin, username } = state;

        // i18n
        const { t } = this.props;

        /*Footer Top Side*/
        let footerTopSide=(
            <section className="d-flex justify-content-center justify-content-lg-between p-4 border-bottom">
                <div className="me-5 d-none d-lg-block">
                    <span>Get connected with us on social networks:</span>
                </div>
                <div>
                    <a href="blog/src/component/main/Footer" className="me-4 link-secondary">
                        <i className="text-primary fab fa-facebook-f"></i>
                    </a>
                    <a href="blog/src/component/main/Footer" className="me-4 link-secondary">
                        <i className="text-warning fab fa-twitter"></i>
                    </a>
                    <a href="blog/src/component/main/Footer" className="me-4 link-secondary">
                        <i className="text-primary  text-primary  fab fa-google"></i>
                    </a>
                </div>
            </section>
        );

        /*Footer Left*/
        let footerLeftSide=(
            <div className="col-md-4 col-lg-4 col-xl-4 mx-auto mb-md-0 mb-4">
                <h6 className="text-uppercase fw-bold mb-4">{this.props.t('contact')}</h6>
                <p><i className="fas fa-home me-3 text-secondary"></i> New York, NY 10012, US</p>
                <p>
                    <i className="fas fa-envelope me-3 text-secondary"></i>
                    info@example.com
                </p>
                <p><i className="fas fa-phone me-3 text-secondary"></i> + 01 234 567 88</p>
                <p><i className="fas fa-print me-3 text-secondary"></i> + 01 234 567 89</p>
            </div>
        );

        /*Footer Middle*/
        let footerMiddleSide=(
            <div className="col-md-2 col-lg-2 col-xl-4 mx-auto mb-4">
                <h6 className="text-uppercase fw-bold mb-4">
                    <i className="fas fa-gem me-3 text-secondary"></i>{this.props.t('company')}
                </h6>
                <p>Company INC Lorem ipsum dolor sit amet, consectetur adipisicing elit. Assumenda
                    beatae distinctio eligendi fugiat iusto neque quidem quo sapiente voluptates
                    voluptatibus?</p>
            </div>
        );

        /*Footer Right*/
        const footerRightSide=(
            <div className="col-md-6 col-lg-6 col-xl mx-auto mb-4">
                {/* Email Class */}
                <Email />
            </div>
        );

        //RETURN
        return (
            <React.Fragment>
                <footer className="text-center text-lg-start bg-dark text-white fixed-bottom44"
                    style={{ "marginTop": "20rem" }}>

                    {footerTopSide}

                    <section className="">
                        <div className="container text-center text-md-start mt-5">
                            <div className="row mt-3">

                                {footerLeftSide}
                                {footerMiddleSide}
                                {footerRightSide}

                            </div>
                        </div>
                    </section>

                    <div className="text-center p-4" style={{ "backgroundColor": "rgba(0, 0, 0, 0.025)" }}>
                        2020
                        © {newDateYear()} Copyright:
                        <a className="text-reset fw-bold" href="blog/src/component/main/Footer">{this.props.site}</a>
                    </div>
                </footer>
            </React.Fragment>
        );  /* end return*/
    } /* end render*/
} /* end class*/


//i18n sarmaladı
export default withTranslation()(Footer)

