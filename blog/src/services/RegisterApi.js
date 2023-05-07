import axios from "axios";

// Sabit URL
const REGISTER_URL = "admin/api/v1";

// CLASS
class RegisterApi {

    // CREATE
    // http://localhost:2222/admin/api/v1/create
    createApi(registerDto) {
        return axios.post(`${REGISTER_URL}/create`, registerDto)
    }

    // LIST
    // http://localhost:2222/admin/api/v1/list
    listApi() {
       return axios.get(`${REGISTER_URL}/list`);
    }

    // FIND
    // http://localhost:2222/admin/api/v1/find
    // http://localhost:2222/admin/api/v1/find/0
    // http://localhost:2222/admin/api/v1/find/-1
    // http://localhost:2222/admin/api/v1/find/1
    findByIdApi(id) {
        return axios.get(`${REGISTER_URL}/find/${id}`)
    }

    // DELETE
    // http://localhost:2222/admin/api/v1/delete/1
    deleteApi(id) {
        return axios.delete(`${REGISTER_URL}/find/${id}`)
    }

    // UPDATE
    // http://localhost:2222/admin/api/v1/update/1
    updateApi(id, registerDto) {
        return axios.put(`${REGISTER_URL}/find/${id}`,registerDto)
    }

} //end class RegisterDto

export default new RegisterApi();