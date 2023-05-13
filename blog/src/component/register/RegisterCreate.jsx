import React, { Component } from 'react';
import { withTranslation } from "react-i18next";
import RegisterApi from '../../services/RegisterApi';
import ResuabilityRegisterInput from '../../resuability/ResuabilityRegisterInput';

class RegisterCreate extends Component {
  constructor(props) {
    super(props);
    //STATE
    this.state = {
      registerDto: {},
      name: null,
      surname: null,
      email: null,
      password: null,
      isRead: false,// Okumadan gönder butonu aktifleşmesin.
      spinnerData: false, // veri gönderirken loading olsun ve aynı anda birden fazla kayıt olunması
      validationErrors: {}, // backentten gelen hataları yakalamak  
    }
    //BIND
    this.onChangeInputValue = this.onChangeInputValue.bind(this);
    this.registerCreateSubmit = this.registerCreateSubmit.bind(this);
    this.onChangeIsRead = this.onChangeIsRead.bind(this);
  }

  //CDM
  // componentDidMount() {

  // }

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
    const backendError = {...this.state.validationErrors }
    backendError[name] = undefined;

    // STATE
    this.setState({
      [name]: value, //state name,surname,email,password
      backendError, // state Backendten gelen hataları yakalamak,
    })
  }

  // Okumadan Submit Butonu aktif olmasın
  onChangeIsRead = (event) => {
    this.setState({
      isRead: event.target.checked,
    })
  }

  // SUBMIT 
  registerCreateSubmit = async (event) => {
    //browser sen dur bir şey yapma
    event.preventDefault();

    const { name, surname, email, password } = this.state;
    const registerDto = {
      name, surname, email, password
    }
    console.log(registerDto);

    // Spinner Data: çalışsın
    this.setState({ spinnerData: true })
    RegisterApi.createApi(registerDto).then((response) => {
      if (response.status === 200) {
        // Veri gönderene kadar spinner(loading) çalışmasın
      }
      //PHP
      this.props.history.push("/admin");
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



  // RENDER
  render() {
    // i18n
    const { t } = this.props;

    //this.state.validationErrors.name
    const { validationErrors, isRead, spinnerData } = this.state;
    const { name, surname, email, password } = validationErrors;

    //RETURN
    return (
      <React.Fragment>
        <h1 className="text-center display-3">{this.props.t('create')}</h1>
        <form action="" method="post">

          {/* <div className="form-group mt-3">
            <label htmlFor="name">{t('name')}</label>
            <input
              type="text" id="name" name="name"
              className="form-control" required="true"
              placeholder={t('name')} autoFocus={true}
              onChange={this.onChangeInputValue}
            />
            <div 
            className={this.state.validationErrors.name&&'invalid-feedback44 text-danger'} >
              {this.state.validationErrors.name}
            </div>
          </div> */}
          <ResuabilityRegisterInput
            type="text" focus={true}
            name="name" id="name"
            placeholder={t('name')}
            label={t('name')}
            onChangeInput={this.onChangeInputValue}
            error={name} />

          <ResuabilityRegisterInput
            type="text" focus={false}
            name="surname" id="surname"
            placeholder={t('surname')}
            label={t('surname')}
            onChangeInput={this.onChangeInputValue}
            error={surname} />

          <ResuabilityRegisterInput
            type="email" focus={false}
            name="email" id="email"
            placeholder={t('email')}
            label={t('email')}
            onChangeInput={this.onChangeInputValue}
            error={email} />

          <ResuabilityRegisterInput
            type="password" focus={false}
            name="password" id="password"
            placeholder={t('password')}
            label={t('password')}
            onChangeInput={this.onChangeInputValue}
            error={password} />

          {/*READING*/}
          <div className="form-group mt-3">
            <label htmlFor="name" className='me-2'>{t('isRead')}</label>
            <input
              type="checkbox" className='form-check-label'
              id="is_read" name="is_read"
              onChange={this.onChangeIsRead} />
          </div>

          {/* CLEAR */}
          <button className="btn btn-danger mt-4 me-4">Temizle</button>

          {/* SUBMIT */}
          <button
            className="btn btn-primary mt-4"
            onClick={this.registerCreateSubmit}

            // hem okuma yapılmadan kapansın 
            // hemde çoklu isteklerde kapatılsın
            disabled={(!isRead) || (spinnerData)}
          >
            {/* 1.YOL (ternary) Spinner data  */}
            {/* {(this.state.spinnerData) ? <div className="spinner-border spinner-border-sm text-warning me-2" role="status"></div>:"" }   */}
            {/* 2.YOL (&&) Spinner data  */}
            {(spinnerData) && <div className="spinner-border spinner-border-sm text-warning me-2" role="status"></div>}
            Gönder
          </button>
        </form>
      </React.Fragment>
    ); //end return 
  } //end render
} //end RegisterCreate

//i18n sarmaladı
export default withTranslation()(RegisterCreate)