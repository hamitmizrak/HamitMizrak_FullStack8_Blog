// rcc
import React, { Component } from 'react'
import Header from './component/main/Header'
import Body from './component/main/Body'
import Footer from './component/main/Footer'
import { Route, Router, Switch } from 'react-router-dom'

// admin list,create,view
import list from './component/admin/list'
import create from './component/admin/create'
import view from './component/admin/view'

export default class Blog extends Component {
  render() {
    return (
      <React.Fragment>
        {/* Router> Switch >Router */}
        <Router> 
          <Header logo="fa-solid fa-blog" />
          <div className="container">
            <Switch>
               <Route path="/" exact component={list}>Anasayfa</Route>
               <Route path="/index" component={list}>Anasayfa</Route>
               <Route path="/create" component={create}>Create</Route>
               <Route path="/update/:username" component={create}>Update</Route>
               <Route path="/view/:username" component={view}>View</Route>
            </Switch>
            <Body />
          </div>
          <Footer site="Hamit Mızrak" />
        </Router>
      </React.Fragment>
    ) //end return
  } //end render
} // end class
