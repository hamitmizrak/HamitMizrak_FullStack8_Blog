import React, { Component } from 'react'

// dil için
import { withTranslation } from 'react-i18next'

//resim
import cardPicture from "../../image/mountain.jpg"
import RegisterApi from '../../services/RegisterApi';

class AdminView extends Component {

    constructor(props) {
        super(props);
        // STATE
        this.state = {
            id: null,
            name: null,
            surname: null,
            password: null,
            email: null,
            systemDate: null
        }

        //BIND
        this.list = this.list.bind(this);
    }

    //CDM
    componentDidMount() {
        RegisterApi.findByIdApi(2).then((response) => {
            //console.log(response.data);
            //console.log(response.data.surname);
            this.setState({
                name: response.data.name,
                surname: response.data.surname,
                password: response.data.password,
                email: response.data.email,
                systemDate: response.data.systemDate,
            })
        }).catch((error) => {
            console.log(error);
            console.log(error.name);
            console.log(error.message);
        })
    }

    //FUNCTION
    list() {
        this.props.history.push("/admin");
    }

    //RENDER
    render() {
        //RETURN
        return (
            <React.Fragment>
                <div className="card w-75" style={{ marginTop: "5rem" }} >
                    <img className="img-fluid w-50 mx-auto" src={cardPicture} alt="Title" />
                    <div className="card-body">
                        <h4 className="card-title">View</h4>
                        <p className="card-text"> <b>ID:</b> {this.state.id}</p>
                        <p className="card-text"><b>NAME:</b>  {this.state.name}</p>
                        <p className="card-text"><b>SURNAME:</b> {this.state.surname}</p>
                        <p className="card-text"><b>EMAİL:</b> {this.state.email}</p>
                        <p className="card-text"><b>PASSWORD:</b> {this.state.password}</p>
                        <button className="btn btn-primary" onClick={this.list}>Liste</button>

                    </div>
                </div>
            </React.Fragment>
        ) //end return
    }//end  render
}//end  class

export default withTranslation()(AdminView)