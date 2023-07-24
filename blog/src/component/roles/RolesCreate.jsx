// React
import React, { Component } from 'react';

// i18n için
import { withTranslation } from "react-i18next";

// ResuabilityRegisterInput
import ResuabilityRegisterInput from '../register/ResuabilityRegisterInput';

// Services
import RegisterRolesApi from "../../services/RegisterRolesApi";

// CLASS
class RolesCreate extends Component {

  // Componentte görünür adı      
  static displayName = "RolesCreate"

  constructor(props) {
    super(props);
    //STATE
    this.state = {
      registerDto: {},
      roleName: null,
      spinnerData: false, // veri gönderirken loading olsun ve aynı anda birden fazla kayıt olunması
      validationErrors: {}, // backentten gelen hataları yakalamak  
    }
    //BIND
    this.onChangeInputValue = this.onChangeInputValue.bind(this);
    this.registerCreateSubmit = this.registerCreateSubmit.bind(this);
  }

  //CDM
  componentDidMount() {}

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

  // SUBMIT 
  registerCreateSubmit = async (event) => {
    //browser sen dur bir şey yapma
    event.preventDefault();

    const { roleName } = this.state;
    const registerDto = {
      roleName
    }
    console.log(registerDto);

    // Spinner Data: çalışsın
    this.setState({ spinnerData: true })
    RegisterRolesApi.createApi(registerDto).then((response) => {
      if (response.status === 200) {
        // Veri gönderene kadar spinner(loading) çalışmasın
      }
      //PHP
      this.props.history.push("/roles/list");
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
    const { validationErrors, spinnerData } = this.state;
    const { roleName } = validationErrors;

    //RETURN
    return (
      <React.Fragment>
        <h1 className="text-center display-3">{this.props.t('create')}</h1>
        <form action="#" method="post">

          <ResuabilityRegisterInput
            type="text" focus={true}
            name="roleName" id="roleName"
            placeholder={t('name')}
            label={t('roles')}
            onChangeInput={this.onChangeInputValue}
            error={roleName} />

          {/* CLEAR */}
          <button className="btn btn-danger mt-4 me-4">Temizle</button>

          {/* SUBMIT */}
          <button
            className="btn btn-primary mt-4"
            onClick={this.registerCreateSubmit}

            // hem okuma yapılmadan kapansın 
            // hemde çoklu isteklerde kapatılsın
            disabled={(spinnerData)}>
            {(spinnerData) && <div className="spinner-border spinner-border-sm text-warning me-2" role="status"></div>}
            Gönder
          </button>
        </form>
      </React.Fragment>
    ); //end return 
  } //end render
} //end RegisterCreate

//i18n sarmaladı
export default withTranslation()(RolesCreate)