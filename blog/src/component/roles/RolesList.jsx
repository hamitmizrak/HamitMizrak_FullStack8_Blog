// React
import React, { Component } from 'react'

// i18n için
import { withTranslation } from "react-i18next";

//Services
import RegisterRolesApi from "../../services/RegisterRolesApi";

class RolesList extends Component {
    // Componentte görünür adı      
    static displayName = "Roles_List"

    constructor(props) {
        super(props);
        //STATE
        this.state = {
            rolesList: [],
        }
        //BIND
        this.create = this.create.bind(this);
        this.update = this.update.bind(this);
        this.delete = this.delete.bind(this);
    }

    //CDM
    componentDidMount() {
        RegisterRolesApi.listApi().then(
            (response) => {
                this.setState({
                    rolesList: response.data
                })
            }).catch((error) => {
                console.log("List Wrong")
            });
    }


    // FUNCTION
    // PUSH CREATE
    create() {
        // PHP
        this.props.history.push("/roles/create")
    }

    // PUSH UPDATE
    update(rolesId) {
        // PHP
        this.props.history.push(`/roles/update/${rolesId}`)
    }

    // PUSH DELETE
    delete(rolesId) {
        RegisterRolesApi.deleteApi(rolesId).then(
            (response) => {
                this.setState({
                    rolesList: this.state.rolesList.filter(
                        rolesList => rolesList.rolesId != rolesId
                    )
                });
            }).catch(() => {
                console.log("Roles Delete Wrong")
            });
    }


    //RENDER
    render() {

        //RETURN
        return (
            <React.Fragment>
                <h1 className="text-center display-4">Roles List</h1>
                <div className="container">
                    <div className="row">
                        <div className="mx-auto">
                            <button className="btn btn-primary" onClick={this.create}>Ekle</button>
                        </div>
                    </div>
                    <table className="table table-hover table-striped">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>ROLENAME</th>
                                <th>Güncelle</th>
                                <th>Sil</th>
                            </tr>
                        </thead>
                        <tbody>
                            {

                                this.state.rolesList.map(temp =>
                                    <tr key={temp.rolesId}>
                                        <td>{temp.rolesId}</td>
                                        <td>{temp.roleName}</td>
                                        <td><i style={{ "cursor": "pointer" }} className="text-primary fa-solid fa-pen-to-square" onClick={() => this.update(temp.rolesId)} ></i></td>
                                        <td><i style={{ "cursor": "pointer" }} className="text-danger fa-solid fa-trash" onClick={() => {
                                            if (window.confirm("Blogu Silmek istediğinizden Emin misiniz?")) {
                                                this.delete(temp.rolesId);
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
} //end class roleList

//i18n sarmaladı
export default withTranslation()(RolesList)