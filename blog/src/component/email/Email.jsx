import React, {Component} from 'react';

// i18n için
import {withTranslation} from 'react-i18next';

// Context
import {LoginAuthenticationContext} from "../../context/LoginContext";

// Resuability EmailINput
import ResuabilityEmailInput from "./ResuabilityEmailInput";

// Email Services
import EmailApi from "../../services/EmailApi";

// FUNCTION COMPONENT(Stateless)
class Email extends Component {

    // Componentte görünür adı      
    static displayName = "Footer"

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
                {name: 'Seçiniz', value: null},
                {name: 'Frontend', value: "Html5"},
                {name: 'Backend', value: "java"},
                {name: 'Database', value: "Hibernate"},
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
        const {name, value} = event.target;
        console.log(name + " " + value);

        // Backendten gelen hataları yakalamak,
        // const backendError={}
        // ... anlamı kopyalama yapar.
        const backendError = {...this.state.validationErrors}
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
        const {emailTo, emailSubject, emailText} = this.state;
        const emailDto = {
            emailTo, emailSubject, emailText
        }
        console.log(emailDto);

        // Spinner Data: çalışsın
        this.setState({spinnerData: true})
        EmailApi.blogSendEmail(emailDto).then((response) => {
            if (response.status === 200) {
                // Veri gönderene kadar spinner(loading) çalışmasın
            }
            //PHP
            this.props.history.push("/login");
        }).catch((error) => {
            console.error(error);

            // backentten gelen hataları yakala
            if (error.response.data.validationErrors) {
                this.setState({validationErrors: error.response.data.validationErrors})//end state
                console.log(error.response.data.validationErrors)
            } //end if

            this.setState({spinnerData: false})
        });
    }


    // Konuyu liste olarak al
    // validation
    // error eklemelisin.
    // i18n
    // security

    // CONTEXT ALMAK
    static contextType = LoginAuthenticationContext;

    // RENDER
    render() {

        //CONTEXT
        const {state, logout} = this.context;
        const {isLogin, username} = state;

        // i18n
        const {t} = this.props;

        //State
        const {spinnerData, validationErrors, options, dropDownValue} = this.state;
        const {emailTo, emailSubject, emailText,} = validationErrors;

        //RETURN
        return (
            <React.Fragment>
                <h6 className="text-uppercase fw-bold mb-4">{t('email')}</h6>
                <form>

                    <ResuabilityEmailInput
                        type="email" focus={false}
                        name="emailTo" id="emailTo"
                        placeholder={t('email_address')}
                        onChangeInput={this.onChangeInputValue}
                        error={emailTo}/>

                    <ResuabilityEmailInput
                        type="text" focus={false}
                        name="emailSubject" id="emailSubject"
                        placeholder={t('email_subject')}
                        onChangeInput={this.onChangeInputValue}
                        error={emailSubject}/>

                    <div className="form-group mb-3">
                        <select className="form-control"
                                name="emailSubject" id="emailSubject"
                                onChange={this.onChangeInputValue}
                                value={dropDownValue}>
                            {
                                options.map(item => (
                                    <option key={item.value} value={item.value}>{item.name}</option>
                                ))
                            }
                        </select>
                        <div className={"text-danger"}>{emailSubject}</div>
                    </div>

                    <div className="form-group mb-3">
                        <textarea
                            className="form-control"
                            placeholder={t('email_text')}
                            cols="20" rows="4"
                            name="emailText" id="emailText"
                            onChange={this.onChangeInputValue}/>
                        <div className={"text-danger"}>{emailText}</div>
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
            </React.Fragment>
        );
    } //end render
} //end class


//i18n sarmaladı
export default withTranslation()(Email)

