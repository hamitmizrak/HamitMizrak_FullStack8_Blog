// rcc
import React, { Component } from 'react'
import Header from './component/main/Header'
import Body from './component/main/Body'
import Footer from './component/main/Footer'

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
