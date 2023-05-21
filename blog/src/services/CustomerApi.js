import axios from "axios";

// Sabit URL
const REGISTER_URL = "customer/api/v1";

// CLASS
class CustomerApi {

    // CREATE
    // http://localhost:2222/customer/api/v1/create
    createApi(customerDto) {
        return axios.post(`${REGISTER_URL}/create`, customerDto)
    }

    // LIST
    // http://localhost:2222/customer/api/v1/list
    listApi() {
        return axios.get(`${REGISTER_URL}/list`);
    }

    // FIND
    // http://localhost:2222/customer/api/v1/find
    // http://localhost:2222/customer/api/v1/find/0
    // http://localhost:2222/customer/api/v1/find/-1
    // http://localhost:2222/customer/api/v1/find/1
    findByIdApi(id) {
        return axios.get(`${REGISTER_URL}/find/${id}`)
    }

    // DELETE
    // http://localhost:2222/customer/api/v1/delete/1
    deleteApi(id) {
        return axios.delete(`${REGISTER_URL}/delete/${id}`)
    }

    // UPDATE
    // http://localhost:2222/customer/api/v1/update/1
    updateApi(id, customerDto) {
        return axios.put(`${REGISTER_URL}/update/${id}`, customerDto)
    }

} //end class customerDto

export default new CustomerApi();