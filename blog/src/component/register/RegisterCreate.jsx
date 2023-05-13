import React, { Component } from 'react';
import { withTranslation } from "react-i18next";
import RegisterApi from '../../services/RegisterApi';

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
    //const name = event.target.name;
    //const value = event.target.value;
    // 2.YOL
    const { name, value } = event.target;
    console.log(name + " " + value);

    // STATE
    this.setState({
      [name]: value, //state name,surname,email,password
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

    RegisterApi.createApi(registerDto).then((response) => {
      this.props.history.push("/admin");
    }).catch((error) => {
      console.error(error);
    });
  }

  // Okumadan Submit Butonu aktif olmasın
  onChangeIsRead = (event) => {
    this.setState({
      isRead: event.target.checked,
    })
  }

  // RENDER
  render() {
    // i18n
    const { t } = this.props;

    //RETURN
    return (
      <React.Fragment>

        <h1 className="text-center display-3">{this.props.t('create')}</h1>
        <form action="" method="post">

          <div className="form-group mt-3">
            <span htmlFor="name">{t('name')}</span>
            <input
              type="text" id="name" name="name"
              className="form-control" required={true}
              placeholder={t('name')} autoFocus={true}
              onChange={this.onChangeInputValue}
            />
            <span className="text-danger"></span>
          </div>

          <div className="form-group mt-3">
            <span htmlFor="name">{t('surname')}</span>
            <input
              type="text" id="surname" name="surname"
              className="form-control" required={true}
              placeholder={t('surname')} autoFocus={false}
              onChange={this.onChangeInputValue}
            />
            <span className="text-danger"></span>
          </div>

          <div className="form-group mt-3">
            <span htmlFor="name">{t('email')}</span>
            <input
              type="email" id="email" name="email"
              className="form-control" required={true}
              placeholder={t('email')} autoFocus={false}
              onChange={this.onChangeInputValue} />
            <span className="text-danger"></span>
          </div>

          <div className="form-group mt-3">
            <span htmlFor="name">{t('password')}</span>
            <input
              type="password" id="password" name="password"
              className="form-control" required={true}
              placeholder={t('password')} autoFocus={false}
              onChange={this.onChangeInputValue} />
            <span className="text-danger"></span>
          </div>

          {/*READING*/}
          <div className="form-group mt-3">
            <span htmlFor="name" className='me-2'>{t('isRead')}</span>
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
            disabled={!this.state.isRead}
          >Gönder</button>
        </form>
      </React.Fragment>
    ); //end return 
  } //end render
} //end RegisterCreate

//i18n sarmaladı
export default withTranslation()(RegisterCreate)