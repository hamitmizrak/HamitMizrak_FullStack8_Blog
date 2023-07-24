import axios from "axios";

// Sabit URL
const ROLE_URL = "roles/api/v1";
// hamitmizrak1@gmail.com Hm4444@.
// CLASS
class RegisterRolesApi {

    // CREATE
    // http://localhost:2222/roles/api/v1/create
    createApi(roleDto) {
        return axios.post(`${ROLE_URL}/create`, roleDto)
    }

    // LIST
    // http://localhost:2222/roles/api/v1/list
    listApi() {
        return axios.get(`${ROLE_URL}/list`);
    }

    // FIND
    // http://localhost:2222/roles/api/v1/ind
    // http://localhost:2222/roles/api/v1/find/0
    // http://localhost:2222/roles/api/v1/find/-1
    // http://localhost:2222/roles/api/v1/find/1
    findByIdApi(id) {
        return axios.get(`${ROLE_URL}/find/${id}`)
    }

    // DELETE
    // http://localhost:2222/roles/api/v1/delete/1
    deleteApi(id) {
        return axios.delete(`${ROLE_URL}/delete/${id}`)
    }

    // UPDATE
    // http://localhost:2222/roles/api/v1/update/1
    updateApi(id, userDto) {
        return axios.put(`${ROLE_URL}/update/${id}`, userDto)
    }

       ////////////////////////////////////////////////////////////// 
    //Email üzerinden Role Bulmak
    userEmailFindRoles(emailAddress) {
        //return axios.get(`${ROLE_URL}/roles/${emailAddress}`) //@RequestParam
        return axios.get(`${ROLE_URL}/roles/${emailAddress}`) //@PathVariable
    }

} //end class userDto

export default new RegisterRolesApi();