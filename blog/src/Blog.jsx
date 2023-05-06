// rcc
import React, { Component } from 'react'
import Header from './main/Header'
import Body from './main/Body'
import Footer from './main/Footer'

export default class Blog extends Component {
  render() {
    return (
      <React.Fragment>
        <Header logo="fa-solid fa-blog"/>
        <Body/>
        <Footer site="Hamit Mızrak"/>
      </React.Fragment>
    )
  }
}
