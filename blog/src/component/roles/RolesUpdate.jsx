// React
import React, {Component} from 'react';

// i18n için
import {withTranslation} from "react-i18next";

// ResuabilityRolesInput
import ResuabilityRolesInput from './ResuabilityRolesInput';

// Services
import RegisterRolesApi from "../../services/RegisterRolesApi";

class RolesUpdate extends Component {

    // Componentte görünür adı      
    static displayName = "RolesUpdate"

    constructor(props) {
        super(props);
        //STATE
        this.state = {
            rolesDto: {},
            roleName: null,
            isRead: false,// Okumadan gönder butonu aktifleşmesin.
            spinnerData: false, // veri gönderirken loading olsun ve aynı anda birden fazla kayıt olunması
            validationErrors: {}, // backentten gelen hataları yakalamak 
            id: this.props.match.params.id, // update id parametresini almak

        }
        //BIND
        this.onChangeInputValue = this.onChangeInputValue.bind(this);
        this.rolesUpdateSubmit = this.rolesUpdateSubmit.bind(this);
        this.onChangeIsRead = this.onChangeIsRead.bind(this);
        this.cleanerData = this.cleanerData.bind(this);
    }

    //CDM
    componentDidMount() {
        RegisterRolesApi.findByIdApi(this.state.id).then(
            (response) => {
                const findRolesDto = response.data;
                //console.log(findRolesDto);
                this.setState({
                    roleName: findRolesDto.roleName,
                })
            }
        ).catch((error) => {
            console.log("Roles isn't find")
        });
    }

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

    // Okumadan Submit Butonu aktif olmasın
    onChangeIsRead = (event) => {
        this.setState({
            isRead: event.target.checked,
        })
    }

    // cleaner
    cleanerData(rolesId) {
        // PHP
        this.props.history.push(`/roles/update/${rolesId}`)
    }

    // SUBMIT 
    rolesUpdateSubmit = async (event) => {
        //browser sen dur bir şey yapma
        event.preventDefault();

        const {roleName} = this.state;
        const rolesDto = {
            roleName
        }
        console.log(rolesDto);

        // Spinner Data: çalışsın
        this.setState({spinnerData: true})
        //   updateApi(id, rolesDto) {
        RegisterRolesApi.updateApi(this.state.id, rolesDto).then((response) => {
            if (response.status === 200) {
                // Veri gönderene kadar spinner(loading) çalışmasın
            }
            //PHP
            this.props.history.push("/roles/list");
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

    // RENDER
    render() {
        // i18n
        const {t} = this.props;

        //this.state.validationErrors.name
        const {
            validationErrors, isRead, spinnerData,
            roleName, id
        } = this.state;

        //RETURN
        return (
            <React.Fragment>
                <h1 className="text-center display-3">{this.props.t('update')}</h1>
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
                    <ResuabilityRolesInput
                        type="text" focus={true}
                        name="roleName" id="roleName"
                        placeholder={t('name')}
                        label={t('name')}
                        onChangeInput={this.onChangeInputValue}
                        error={validationErrors.roleName}
                        value={roleName}
                    />


                    {/*READING*/}
                    <div className="form-group mt-3">
                        <label htmlFor="name" className='me-2'>{t('isRead')}</label>
                        <input
                            type="checkbox" className='form-check-label'
                            id="is_read" name="is_read"
                            onChange={this.onChangeIsRead}/>
                    </div>

                    {/* CLEAR */}
                    <button className="btn btn-danger mt-4 me-4" onClick={() => this.cleanerData(id)}>Temizle</button>

                    {/* SUBMIT */}
                    <button
                        className="btn btn-primary mt-4"
                        onClick={this.rolesUpdateSubmit}

                        // hem okuma yapılmadan kapansın 
                        // hemde çoklu isteklerde kapatılsın
                        disabled={(!isRead) || (spinnerData)}
                    >
                        {/* 1.YOL (ternary) Spinner data  */}
                        {/* {(this.state.spinnerData) ? <div className="spinner-border spinner-border-sm text-warning me-2" role="status"></div>:"" }   */}
                        {/* 2.YOL (&&) Spinner data  */}
                        {(spinnerData) &&
                            <div className="spinner-border spinner-border-sm text-warning me-2" role="status"></div>}
                        Gönder
                    </button>
                </form>
            </React.Fragment>
        ); //end return 
    } //end render
} //end RolesUpdate

//i18n sarmaladı
export default withTranslation()(RolesUpdate)
