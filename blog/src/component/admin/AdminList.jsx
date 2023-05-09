import React, { Component } from 'react'
import RegisterApi from '../../services/RegisterApi';
import { withTranslation } from "react-i18next";

class AdminList extends Component {
    constructor(props) {
        super(props);
        //STATE
        this.state = {
            registerList: [],
        }
        //BIND
        this.create = this.create.bind(this);
        this.update = this.update.bind(this);
        this.view = this.view.bind(this);
        this.delete = this.delete.bind(this);
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

    //FUNCTION
    // FUNCTION
    // create function
    create() {
        // PHP
        this.props.history.push("/create")
    }

    view(id) {
        // PHP
        this.props.history.push("/view/" + id)
    }

    update(id) {
        // PHP
        this.props.history.push(`/update/${id}`)
    }

    delete(id) {
        RegisterApi.deleteApi(id).then((response) => {
            this.setState({
                registerList:this.state.registerList.filter(
                    registerList=>registerList.id!=id
                )
            });
        }).catch(() => {
            console.log("Delete Wrong")
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
                            <button className="btn btn-primary" onClick={this.create}>Ekle</button>
                            {/* <button className="btn btn-primary" onClick={()=>this.create()}>Ekle</button> */}
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
                                this.state.registerList.map(temp =>
                                    <tr key={temp.id}>
                                        <td>{temp.id}</td>
                                        <td>{temp.name}</td>
                                        <td>{temp.surname}</td>
                                        <td>{temp.email}</td>
                                        <td>{temp.password}</td>
                                        <td><i style={{ "cursor": "pointer" }} class="text-primary fa-solid fa-pen-to-square" onClick={() => this.update(temp.id)} ></i></td>
                                        <td><i style={{ "cursor": "pointer" }} class="text-warning fa-solid fa-expand" onClick={() => { this.view(temp.id) }}></i></td>
                                        <td><i style={{ "cursor": "pointer" }} class="text-danger fa-solid fa-trash" onClick={() => {
                                            if (window.confirm("Silmek istediğinizden emin misiniz?")) {
                                                this.delete(temp.id);
                                            } else {
                                                window.alert("Silinmedi !!!")
                                            }
                                        }}></i></td>
                                    </tr>
                                )
                            }
                        </tbody>
                    </table>
                </div>
            </React.Fragment>
        ) //end return
    } //end render
} //end class AdminList

//i18n sarmaladı
export default withTranslation()(AdminList)