// rcc
import React, { Component } from 'react'
// Cannot read properties of undefined (reading 'location') => 
// Router Hangi Router olmalıdır. 1-)BrowserRouter 2-)HashRouter
// 1-) BrowserRouter  ==> http://localhost:3000/
// 2-) HashRouter     ==> http://localhost:3000/#
// BrowserRouter: backend trafiğini harekete geçiriyor.
// HashRouter: Backendte giden gelen trafiği kapatarak sistemi daha güvenli olmasını sağlıyor.
import { HashRouter as Router, Redirect, Route, Switch } from "react-router-dom"


// Header Body Footer
import Header from './component/main/Header'
import Body from './component/main/Body'
import Footer from './component/main/Footer'

// Dil için
import { withTranslation } from 'react-i18next'
import './internationalization/i18nlanguage.js'

// Component
import Home from './component/Home'
import Register from './component/Register'
import Login from './component/Login'

class Blog extends Component {
  constructor(props) {
    super(props);
    this.state = {}
    //BIND
  }
  //CDM
  render() {
    return (
      <React.Fragment>
        {/* Router> Switch >Router */}
        <Router>
          <Header logo="fa-solid fa-blog" homePageLink="http://localhost:3000/" />
          <div className="container">
            <Switch>
              <Route path="/" exact component={Home}></Route>
              <Route path="/index" component={Home}></Route>
              <Route path="/register" component={Register}></Route>
              <Route path="/login" component={Login}></Route>
              <Redirect to="/"/>
            </Switch>
          </div>
          <Body/>
          <Footer site="@Copy; Bütün Haklar Saklıdır. Hamit Mızrak" />
        </Router>
      </React.Fragment>
    ) //end return
  } //end render
} // end class

// Higher Order Component:  Monad Componanet
// Monad Componanet => bir başka componentin çıktısı başka componentin girdisi
 export default withTranslation()(Blog);