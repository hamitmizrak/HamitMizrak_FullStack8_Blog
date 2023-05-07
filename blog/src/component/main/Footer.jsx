import React from 'react'
import { withTranslation } from 'react-i18next';

// Arrow function Date
let newDateYear = () => {
    return new Date().getFullYear();
}

// FUNCTION COMPONENT(Stateless)
function Footer(props) {
    return (
        <React.Fragment>
           
            <footer className="text-center text-lg-start bg-dark text-white fixed-bottom44" style={{"marginTop":"20rem"}}>
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
                        <a href="blog/src/component/main/Footer" className="me-4 link-secondary">
                            <i className="text-danger  fab fa-instagram"></i>
                        </a>
                        <a href="blog/src/component/main/Footer" className="me-4 link-secondary">
                            <i className="text-success fab fa-linkedin"></i>
                        </a>
                        <a href="blog/src/component/main/Footer" className="me-4 link-secondary">
                            <i className="fab fa-github"></i>
                        </a>
                    </div>
                </section>

                <section className="">
                    <div className="container text-center text-md-start mt-5">
                        <div className="row mt-3">
                            <div className="col-md-3 col-lg-4 col-xl-3 mx-auto mb-4">
                                <h6 className="text-uppercase fw-bold mb-4">
                                    <i className="fas fa-gem me-3 text-secondary"></i>{props.t('company')}
                                </h6>
                              
                                <p>
                                    Here you can use rows and columns to organize your footer content. Lorem ipsum
                                    dolor sit amet, consectetur adipisicing elit.
                                </p>
                            </div>

                            <div className="col-md-2 col-lg-2 col-xl-2 mx-auto mb-4">

                                <h6 className="text-uppercase fw-bold mb-4">
                                {props.t('product')}
                                </h6>
                                <p>
                                    <a href="blog/src/component/main/Footer#!" className="text-reset">Angular</a>
                                </p>
                                <p>
                                    <a href="blog/src/component/main/Footer#!" className="text-reset">React</a>
                                </p>
                                <p>
                                    <a href="blog/src/component/main/Footer#!" className="text-reset">Vue</a>
                                </p>
                                <p>
                                    <a href="blog/src/component/main/Footer#!" className="text-reset">Laravel</a>
                                </p>
                            </div>

                            <div className="col-md-3 col-lg-2 col-xl-2 mx-auto mb-4">

                                <h6 className="text-uppercase fw-bold mb-4">
                                {props.t('useFullLink')}
                                </h6>
                                <p>
                                    <a href="blog/src/component/main/Footer#!" className="text-reset">Pricing</a>
                                </p>
                                <p>
                                    <a href="blog/src/component/main/Footer#!" className="text-reset">Settings</a>
                                </p>
                                <p>
                                    <a href="blog/src/component/main/Footer#!" className="text-reset">Orders</a>
                                </p>
                                <p>
                                    <a href="blog/src/component/main/Footer#!" className="text-reset">Help</a>
                                </p>
                            </div>

                            <div className="col-md-4 col-lg-3 col-xl-3 mx-auto mb-md-0 mb-4">
                                <h6 className="text-uppercase fw-bold mb-4">{props.t('contact')}</h6>
                                <p><i className="fas fa-home me-3 text-secondary"></i> New York, NY 10012, US</p>
                                <p>
                                    <i className="fas fa-envelope me-3 text-secondary"></i>
                                    info@example.com
                                </p>
                                <p><i className="fas fa-phone me-3 text-secondary"></i> + 01 234 567 88</p>
                                <p><i className="fas fa-print me-3 text-secondary"></i> + 01 234 567 89</p>
                            </div>
                        </div>
                    </div>
                </section>

                <div className="text-center p-4" style={{ "backgroundColor": "rgba(0, 0, 0, 0.025)" }} >
                    2020
                    © {newDateYear()} Copyright:
                    <a className="text-reset fw-bold" href="blog/src/component/main/Footer">{props.site}</a>
                </div>
            </footer>
        </React.Fragment>
    ) //end return
} //end function

//i18n sarmaladı
export default withTranslation()(Footer)

