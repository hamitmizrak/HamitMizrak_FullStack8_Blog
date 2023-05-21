import React, { Component } from 'react'

// dil için
import { withTranslation } from 'react-i18next'

//resim
import cardPicture from "../../image/mountain.jpg"
import RegisterApi from '../../services/CustomerApi';

class RegisterView extends Component {

    constructor(props) {
        super(props);
        // STATE
        this.state = {
            id: this.props.match.params.id,
            // 1.YOL
            // name: null,
            // surname: null,
            // password: null,
            // email: null,
            // systemDate: null

            //2.YOL
            registerDto: {}
        }

        //BIND
        this.list = this.list.bind(this);
    }

    //CDM
    componentDidMount() {
        RegisterApi.findByIdApi(this.state.id).then((response) => {
            //console.log(response.data);
            //console.log(response.data.surname);
            this.setState({
                //1.YOL
                // name: response.data.name,
                // surname: response.data.surname,
                // password: response.data.password,
                // email: response.data.email,
                // systemDate: response.data.systemDate,
                //2.YOL
                registerDto: response.data
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
        const { id, name, surname, email, password, createdDate } = this.state.registerDto;
        //RETURN
        return (
            <React.Fragment>
                <div className="card " style={{ marginTop: "5rem" }} >
                    <img className="img-fluid w-50 mx-auto" src={cardPicture} alt="Title" />
                    <div className="card-body mx-auto">
                        <h4 className="card-title">View</h4>
                        <p className="card-text"> <b>ID:</b> {this.state.id}</p>
                        <p className="card-text"><b>NAME:</b>  {this.state.registerDto.name}</p>
                        <p className="card-text"><b>SURNAME:</b> {surname}</p>
                        <p className="card-text"><b>EMAİL:</b> {email}</p>
                        <p className="card-text"><b>PASSWORD:</b> {password}</p>
                        <p className="text-danger"><b>DATE:</b> {createdDate}</p>
                        <button className="btn btn-outline-primary" onClick={this.list}>Liste</button>
                    </div>
                </div>
            </React.Fragment>
        ) //end return
    }//end  render
}//end  class

export default withTranslation()(RegisterView)