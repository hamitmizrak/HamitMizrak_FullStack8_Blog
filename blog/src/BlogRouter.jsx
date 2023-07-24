// rcc
//router
//import {BrowserRouter as Router, Redirect, Route, Switch} from "react-router-dom"
//import {BrowserRouter, Redirect, Route, Switch} from "react-router-dom"
import React, {Component} from 'react'

// BrowserRouter as => Router
// BrowserRouter yerine HashRouter yapalım
// BrowserRouter URL ==> http://localhost:3000/
// HashRouter    URL ==> http://localhost:3000/#/
// BrowserRouter: backent işlemlerimizi harekete geçiyor bizde sisitem açıklığı olmasın diye HashRouter yapalım
// HashRouter: Backente giden ve gelen verileri göstermemeye yarar.  URL için #/ yapmalıyız
// React BrowserRouter veya HashRouter #/ çözmek için
// <a href="#/" /> Yerine
// <Link  to="/" /> kullan
// Cannot read properties of undefined (reading 'location') => 
// Router Hangi Router olmalıdır. 1-)BrowserRouter 2-)HashRouter
// 1-) BrowserRouter  ==> http://localhost:3000/
// 2-) HashRouter     ==> http://localhost:3000/#
// BrowserRouter: backend trafiğini harekete geçiriyor.
// HashRouter: Backendte giden gelen trafiği kapatarak sistemi daha güvenli olmasını sağlıyor.
import {HashRouter as Router, Redirect, Route, Switch} from "react-router-dom"

// Header Body Footer
import Header from './component/main/Navbar'
import Footer from './component/main/Footer'

// Dil için
import {withTranslation} from 'react-i18next'
import './internationalization/i18nlanguage.js'

// Component
import Home from './component/Home'

import Login from './component/login/Login'
import BlogCreate from './component/blog/BlogCreate'
import BlogUpdate from './component/blog/BlogUpdate'
import BlogView from './component/blog/BlogView'
import BlogList from "./component/blog/BlogList";
import RegisterCreate from "./component/register/RegisterCreate";
import RegisterUpdate from "./component/register/RegisterUpdate";
import RolesCreate from "./component/roles/RolesCreate";
import AdminPage from "./component/admin/AdminPage";

// Context ekledim
import {LoginAuthenticationContext} from "./context/LoginContext";
import RolesList from "./component/roles/RolesList";
import RolesUpdate from "./component/roles/RolesUpdate";

// Class
// Lifting state up olması için functioncomponent(stateless) yerine classcomponent(statefull)
class BlogRouter extends Component {
    constructor(props) {
        super(props);

        //lifting state up: state en üst tabakadan en alta props olarak gönder
        //NOT: undefined aslında sistemi null yapıya çekmek için kullandım
        this.state = {
            //isLogin: false, //LoginContext yazdım diye kaldırdım
            //username: undefined  //LoginContext yazdım diye kaldırdım
        }
        //BIND
    }

    //NOT: systemLoginIn ve systemLogoutOut yerine LoginContext yazdım ki her yerde login /logout ulaşayım.
    ///Kullanıcı sisteme girdiğinde Login
    //eğer aynı referans olursa tek yazabilirsin yani username,isLogin:true yazabilirsin
    /**systemLoginIn = (username) => {
        this.setState({
            username:username,
            isLogin: true
        })
    }*/

    ///Logout
    // systemde eğer logout olursak state hali yani ilk haline dönüştürmek gerekiyor.
    /**systemLogoutOut = () => {
        this.setState({
            isLogin: false,
            username: undefined
        })
    }*/

    // CONTEXT
    static contextType=LoginAuthenticationContext;

    //CDM
    render() {
        //const { isLogin, username } = this.state; //LoginContext yazdım diye kaldırdım
        const isLogin=this.context.state.isLogin;
        const username=this.context.state.username;

        //RETURN
        return (
            <React.Fragment>
                {/* Router> Switch >Router */}

                <Router>
                    {/* lifting state up: almak için props Header.jsx göndereceğim. */}
                    <Header logo="fa-solid fa-blog" homePageLink="http://localhost:3000/index" username={username} isLogin={isLogin} systemLogoutOut={this.systemLogoutOut}/>
                    <div className="container">
                        <Switch>
                            <Route path="/" exact component={Home}></Route>
                            <Route path="/index" exact component={Home}></Route>

                            {/* eğer üye ise tekrar /login gitmesin  */}
                            {/*... objelerimiz olan histoy,location props gibi göndermek için kullanıyoruz*/}
                            {/*Router üzerinden obje ve parametre gönderdim*/}
                            {(isLogin) ? '' :
                                <Route path="/login" component={(reactObjectRouterPropsRedirect) => { return <Login {...reactObjectRouterPropsRedirect} systemLoginIn={this.systemLoginIn} /> }}></Route>
                            }

                            {/*AdminSpecialPage veri göndermek : AdminPage */}
                            <Route path="/admin/:username" component={ props=>{return <AdminPage {...props} username={username} />;}}></Route>

                            <Route path="/roles/list" component={RolesList}></Route>
                            <Route path="/roles/create" component={RolesCreate}></Route>
                            <Route path="/roles/update/:id" component={RolesUpdate}></Route>

                            <Route path="/register/create" component={RegisterCreate}></Route>
                            <Route path="/register/update/:id" component={RegisterUpdate}></Route>

                            <Route path="/blog/list" component={BlogList}></Route>
                            <Route path="/blog/create" component={BlogCreate}></Route>
                            <Route path="/blog/update/:id" component={BlogUpdate}></Route>
                            <Route path="/blog/view/:id" component={BlogView}></Route>
                            <Redirect to="/"/>
                        </Switch>
                    </div>
                    {/* <Body/> */}
                    <Footer site="@Copy; Bütün Haklar Saklıdır. Hamit Mızrak"/>
                </Router>

            </React.Fragment>
        ) //end return
    } //end render
} // end class

// Higher Order Component:  Monad Componanet
// Monad Componanet => bir başka componentin çıktısı başka componentin girdisi
export default withTranslation()(BlogRouter);