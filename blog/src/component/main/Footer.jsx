import React, { Component } from 'react';
import { withTranslation } from 'react-i18next';
import RegisterApi from "../../services/RegisterApi";
import EmailApi from "../../services/EmailApi";
import ResuabilityEmailInput from "../../resuability/ResuabilityEmailInput";
import ResuabilityRegisterInput from "../../resuability/ResuabilityRegisterInput";

// Arrow function Date
let newDateYear = () => {
    return new Date().getFullYear();
}

// FUNCTION COMPONENT(Stateless)
class Footer extends Component {
    constructor(props) {
        super(props);

        //STATE
        this.state = {
            emailTo: null,
            emailSubject: null,
            emailText: null,
            spinnerData: false, // veri gönderirken loading olsun ve aynı anda birden fazla kayıt olunması
            validationErrors: {}, // backentten gelen hataları yakalamak
            options: [ // Select options email
                { name: 'Seçiniz', value: null },
                { name: 'Frontend', value: "Html5" },
                { name: 'Backend', value: "java" },
                { name: 'Database', value: "Hibernate" },
            ],
            dropDownValue: null,
        }

        //BIND
        this.onChangeInputValue = this.onChangeInputValue.bind(this);
        this.emailSendSubmit = this.emailSendSubmit.bind(this);
    } //end constructor

    //CDM
    //componentDidMount() {}

    //FUNCTION
    // Input
    onChangeInputValue = (event) => {
        // 1.YOL
        // const name = event.target.name;
        // const value = event.target.value;
        // 2.YOL
        const { name, value } = event.target;
        console.log(name + " " + value);

        // Backendten gelen hataları yakalamak,
        // const backendError={}
        // ... anlamı kopyalama yapar.
        const backendError = { ...this.state.validationErrors }
        backendError[name] = undefined;

        // STATE
        this.setState({
            [name]: value, //state name,surname,email,password
            backendError, // state Backendten gelen hataları yakalamak,
        })
    }

    // EMAIL
    emailSendSubmit = async (event) => {
        //browser sen dur bir şey yapma
        event.preventDefault();

        //STATE
        const { emailTo, emailSubject, emailText } = this.state;
        const emailDto = {
            emailTo, emailSubject, emailText
        }
        console.log(emailDto);

        // Spinner Data: çalışsın
        this.setState({ spinnerData: true })
        EmailApi.blogSendEmail(emailDto).then((response) => {
            if (response.status === 200) {
                // Veri gönderene kadar spinner(loading) çalışmasın
            }
            //PHP
            this.props.history.push("/");
        }).catch((error) => {
            console.error(error);

            // backentten gelen hataları yakala
            if (error.response.data.validationErrors) {
                this.setState({ validationErrors: error.response.data.validationErrors })//end state
                console.log(error.response.data.validationErrors)
            } //end if

            this.setState({ spinnerData: false })
        });
    }


    // Email DropDownList Handle
    handleDropdownChange = async (event) => {
        //browser sen dur bir şey yapma
        event.preventDefault();
        this.setState({
            dropDownValue: event.target.value,
        })
        console.log(this.state.dropDownValue)
    }


    // Konuyu liste olarak al
    // validation
    // error eklemelisin.
    // i18n
    // security

    //RENDER
    render() {

        // i18n
        const { t } = this.props;

        //State
        const { spinnerData, validationErrors, options, dropDownValue } = this.state;
        const { emailTo, emailSubject, emailText, } = validationErrors;

        //RETURN
        return (
            <React.Fragment>
                <footer className="text-center text-lg-start bg-dark text-white fixed-bottom44"
                    style={{ "marginTop": "20rem" }}>
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

                    <section className="">
                        <div className="container text-center text-md-start mt-5">
                            <div className="row mt-3">
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

                                <div className="col-md-2 col-lg-2 col-xl-4 mx-auto mb-4">
                                    <h6 className="text-uppercase fw-bold mb-4">
                                        <i className="fas fa-gem me-3 text-secondary"></i>{this.props.t('company')}
                                    </h6>
                                    <p>Company INC Lorem ipsum dolor sit amet, consectetur adipisicing elit. Assumenda
                                        beatae distinctio eligendi fugiat iusto neque quidem quo sapiente voluptates
                                        voluptatibus?</p>
                                </div>

                                <div className="col-md-6 col-lg-6 col-xl mx-auto mb-4">
                                    <h6 className="text-uppercase fw-bold mb-4">{t('email')}</h6>

                                    <form action="">
                                        <ResuabilityEmailInput
                                            type="email" focus={true}
                                            name="emailTo" id="emailTo"
                                            placeholder={t('email_address')}
                                            onChangeInput={this.onChangeInputValue}
                                            error={emailTo} />

                                        <ResuabilityEmailInput
                                            type="text" focus={false}
                                            name="emailSubject" id="emailSubject"
                                            placeholder={t('email_subject')}
                                            onChangeInput={this.onChangeInputValue}
                                            error={emailSubject} />

                                        {/* <div className="form-group mb-3">
                                            <select className="custom-select"  name="emailSubject" id="emailSubject" onChange={this.handleDropdownChange} value={dropDownValue}>
                                                {
                                                    options.map(item => (
                                                        <option key={item.value} value={item.value}>{item.name}</option>
                                                    ))
                                                }
                                            </select>
                                            <div className={"text-danger"} >{emailSubject}</div>
                                        </div> */}

                                        <div className="form-group mb-3">
                                            <textarea
                                                className="form-control"
                                                placeholder={t('email_text')}
                                                cols="20" rows="4"
                                                name="emailText" id="emailText"
                                                onChange={this.onChangeInputValue}
                                            ></textarea>
                                            <div className={"text-danger"} >{emailText}</div>
                                        </div>

                                        <div className="form-group mb-3">
                                            <button className="btn btn-danger me-3">Temizle</button>
                                            <button
                                                className="btn btn-primary"
                                                onClick={this.emailSendSubmit}
                                                // hemde çoklu isteklerde kapatılsın
                                                disabled={(spinnerData)}>
                                                {(spinnerData) &&
                                                    <div className="spinner-border spinner-border-sm text-warning me-2"
                                                        role="status"></div>}
                                                Gönder
                                            </button>
                                        </div>
                                    </form>
                                </div>
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
        );
    }
}


//i18n sarmaladı
export default withTranslation()(Footer)

