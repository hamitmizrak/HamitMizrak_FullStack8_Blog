import axios from "axios";

// Sabit URL
const REGISTER_URL = "user/api/v1";

// CLASS
class RegisterApi {

    // CREATE
    // 1=SUPER_ADMIN
    // 2=ADMIN
    // 3=WRITER
    // 4=USER
    // http://localhost:2222/user/api/v1/create/3
    createApi(userDto) {
        return axios.post(`${REGISTER_URL}/create/4`, userDto)
    }

    // LIST
    // http://localhost:2222/user/api/v1/list
    listApi() {
        return axios.get(`${REGISTER_URL}/list`);
    }

    // FIND
    // http://localhost:2222/user/api/v1/find
    // http://localhost:2222/user/api/v1/find/0
    // http://localhost:2222/user/api/v1/find/-1
    // http://localhost:2222/user/api/v1/find/1
    findByIdApi(id) {
        return axios.get(`${REGISTER_URL}/find/${id}`)
    }

    // DELETE
    // http://localhost:2222/user/api/v1/delete/1
    deleteApi(id) {
        return axios.delete(`${REGISTER_URL}/delete/${id}`)
    }

    // UPDATE
    // http://localhost:2222/user/api/v1/update/1
    updateApi(id, userDto) {
        return axios.put(`${REGISTER_URL}/update/${id}`, userDto)
    }

} //end class userDto
export default new RegisterApi();