import React, { Component } from 'react'
import RegisterApi from '../../services/RegisterApi';

export default class list extends Component {
  constructor(props) {
    super(props);
    //STATE
    this.state = {
      registerList: [],
    }
    //BIND
  }

  //CDM
  componentDidMount() {
    RegisterApi.listApi().then((response) => {
      this.setState({
        registerList: response.data
      })
    }).catch((error) => {

    });
  }


  //RENDER
  render() {

    //RETURN
    return (
      <React.Fragment>
     
        <h1 className="text-center display-4">Register List</h1>
        <div className="container">
          <div className="row">
            <div className="mx-auto">
              <button className="btn btn-primary">Ekle</button>
              </div>
          </div>
          <table className="table table-hover table-striped">
            <thead>
              <tr>
                <th>ID</th>
                <th>NAME</th>
                <th>SURNAME</th>
                <th>EMAİL</th>
                <th>PASSWORD</th>
                <th>Güncelle</th>
                <th>Göster</th>
                <th>Sil</th>
              </tr>
            </thead>
            <tbody>
             {
              this.state.registerList.map(temp=>
                <tr key={temp.id}>
                  <td>{temp.id}</td>
                  <td>{temp.name}</td>
                  <td>{temp.surname}</td>
                  <td>{temp.email}</td>
                  <td>{temp.password}</td>
                  <td><i class="text-primary fa-solid fa-pen-to-square"></i></td>
                  <td><i class="text-warning fa-solid fa-expand"></i></td>
                  <td><i class="text-danger fa-solid fa-trash"></i></td>
                </tr>
                )
             }
            </tbody>
          </table>
        </div>
      </React.Fragment>
    ) //end return
  } //end render 
} //end class List

