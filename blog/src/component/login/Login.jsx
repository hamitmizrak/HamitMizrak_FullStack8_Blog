// rcc==> TAB
// react component ==> Class(statefull) ve Function(stateless) olmak üzere
// Class statefull(durumlu) yani veri bilgileri tutup backentte göndermek
// Classlarda mutlaka render eklemeliyiz.
// import axios from 'axios';
import React, {Component} from 'react';

// Dil secenegi
import {withTranslation} from 'react-i18next';

// axios
import axios from 'axios';

//Resuability
import UserLoginServices from "../../services/UserLoginServices";
import ReusabilityLoginInput from "./ReusabilityLoginInput";
import {LoginAuthenticationContext} from "../../context/LoginContext";
import RegisterApi from '../../services/RegisterApi';
import RegisterRolesApi from '../../services/RegisterRolesApi';

// Toast
import toast, {Toaster} from 'react-hot-toast';
import {Link} from "react-router-dom";

// class UserRegister (export en alta yazdım).
class Login extends Component {

    // Componentte görünür adı
    static displayName = "Login";

    // CONTEXT ALMAK
    static contextType = LoginAuthenticationContext;

    // CONSTRUCTOR
    constructor(props) {
        super(props);
        //default value(Class Componentte olmalıdır)
        //input name ile buradaki değerler aynı olmalıdır
        this.state = {
            username: null,
            password: null,
            error: null,
            spinnerData: false,
            rolesName: null,
            rolesId: null,
            name: null,
            surname: null,
            email: null
        }
        //bind function
        this.onClickLogin = this.onClickLogin.bind(this);
        this.onChangeInput = this.onChangeInput.bind(this);
    } //end constructor

    // cdm ==> TAB
    // constructor sonrasında didMount çalışır
    // interceptors: araya girme
    componentDidMount() {
        this.requestInterceptors = axios.interceptors.request.use((request) => {
            console.log("request interceptors: ")
            this.setState({spinnerData: true})
            return request;
        });

        this.responseInterceptors = axios.interceptors.response.use((response) => {
            console.log("response interceptors: ")
            this.setState({spinnerData: false})
            return response;
        }, (error) => {
            this.setState({spinnerData: false});
            throw error;
        })
    } //end componentDidMount


    // herseferinde interceptor çalışmaması ve sistem performanslı çalışması ve memory leak olmaması ve
    // her seferinde componentDidMoun çalışmamaması için componentWillUnmount kullandım
    componentWillUnmount() {
        axios.interceptors.request.eject(this.requestInterceptors);
        axios.interceptors.response.eject(this.responseInterceptors);
    }

    loggedRequest(config) {
        return new Promise((resolve, reject) => {
            axios.request(config)
                .then((res) => {
                    // log success, config, res here
                    resolve(res);
                })
                .catch(err => {
                    // same, log whatever you want here
                    reject(err);
                })
        })
    } //end loggedRequest


    /*fonkisyonlar*/
    /*1.YOL*/
    /* Submit Button: async await */
    onClickLogin = async (event) => {
        /*browsera bizim yerimize submit yapmasını kapattım. */
        event.preventDefault();
        //Dikkat Basic authentication için username,password bekliyor.
        const {username, password} = this.state;
        const loginObject = {username, password}
        this.setState({error: null});

        //Header gelen username LoginPage gönderme yapıyorum. BlogRouter.jsx
        //const {systemLoginIn}=this.props; // Context ekledim diye kaldırdım
        const {isLogin} = this.context;

        //1.YOL: senkdron gidiyor
        //kodun cevap aldıktan sonra response oluyor.
        //return axios.post('/api/1.0/auth', {}, { auth: loginObject})
        // //credentions içinde ==> username ,password
        /*
         const res = await axios.post(
         '/api/v1/auth',
         {headers: {"Accept": "application/json", "Content-Type": "application/json"}},
         {auth:loginObject} ,   this.props.history.push('/home'))
          .catch((err)=>{this.setState({error: err.response.data.message });})
          */

        try {
            const response = await UserLoginServices.onClickLoginService(loginObject);
            //React Router Redirect
            this.props.history.push('/');

            //Header gelen username LoginPage gönderme yapıyorum. BlogRouter.jsx
            //systemLoginIn(username); //Context ekledim diye kaldırdım

            //@ManyToMany ==> email üzerinden rol bilgisini almak email(User) Roles 
            let emailOnRoleName = undefined;
            const emailRoles = await RegisterRolesApi.userEmailFindRoles(this.state.username).then((temp) => {
                emailOnRoleName = temp.data.roleName;
                this.state.rolesId = temp.data.rolesId;
                this.state.surname = temp.data.surname;
                this.state.email = temp.data.email;
            }).catch((error) => {
                console.log(error)
            });

            //Context dolayı ekledim
            const autState = {
                username: username,
                password: password,
                displayName: response.data.name,
                image: response.data.image,
                rolesName: emailOnRoleName,
                rolesId: this.state.rolesId,
            }
            isLogin(autState);
            //console.log(autState)
            //console.log(autState.rolesName())
            //console.log(response.data)

        } catch (err) {
            this.setState({error: err.response.data.message})
        }

        //2.YOL promise: Asenkron gidiyor
        //servise bağlamak : promise success sonrasında(then)
        //then ==> status 200 olursa
        //catch ==> status 4XX ,5XX
        //fat arrow veya arrow function
        // axios post: Asenkron çalışır
        //LoginServices.createRegister(BODY).then((response)=>{
        //    this.setState({submitCloseMultipleRequest:false})
        // }).catch(error =>{
        //     this.setState({submitCloseMultipleRequest:false});
        //  });
    } //end onClickLogin

    /*Input ==> onChange: inputlardaki her bir hareketi yakalama*/
    onChangeInput = (event) => {
        const {name, value} = event.target;
        //state içeriğini tekrar güncelledim => apiError
        this.setState({
            [name]: value,
            error: null
        })
    } //end onChangeInput


    //render
    render() {
        const activePassive = this.state.username && this.state.password;
        // RETURN
        return (
            <React.Fragment>
                <div className="container">
                    <div className="row">
                        <form>
                            <h1 className="mt-5 text-center">{this.props.t('Login Register')}</h1>
                            <ReusabilityLoginInput
                                type="text" label={this.props.t('email')}
                                name="username" id="username"
                                placeholder="Kullanıcı Email"
                                onChangeInput={this.onChangeInput}/>

                            <ReusabilityLoginInput type="password" label={this.props.t('password')}
                                                   name="password" id="password"
                                                   placeholder="Kullanıcı Şifresi"
                                                   onChangeInput={this.onChangeInput}/>

                            {/* state hatayı bootstrap ile alert ekrana basma */}
                            {this.state.error ? <div className="alert alert-danger" role="alert">
                                {this.state.error}
                            </div> : ""}

                            <button type="reset" className="btn btn-danger me-4">Temizle</button>
                            <button type="submit" className="btn btn-primary"
                                    onClick={this.onClickLogin}
                                    disabled={!activePassive || this.state.spinnerData}>{this.state.spinnerData ?
                                <span className="spinner-border spinner-border-sm"></span> : ''} Gönder
                            </button>
                        </form>
                    </div>
                </div>
            </React.Fragment>
        )
    } //end render
} //end LoginPage

// export default UserRegister
// Higher Order Component: monad componenti başka bir componentin içine  ekleyip oradanda yeni sonuclar elde etmek
export default withTranslation()(Login)
