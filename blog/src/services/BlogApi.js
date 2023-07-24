import axios from "axios";

// Sabit URL
const BLOG_URL = "/blog/api/v1";

// CLASS
class BlogApi {

    // CREATE
    // http://localhost:2222/blog/api/v1/create
    blogCreateApi(blogDto) {
        // URL, PARAMETRE
        return axios.post(`${BLOG_URL}/create`, blogDto)
    }

    // LIST
    // http://localhost:2222/blog/api/v1/list
    blogListApi() {
        return axios.get(`${BLOG_URL}/list`);
    }

    // FIND
    // http://localhost:2222/blog/api/v1/find
    // http://localhost:2222/blog/api/v1/find/0
    // http://localhost:2222/blog/api/v1/find/-1
    // http://localhost:2222/blog/api/v1/find/1
    blogFindByIdApi(id) {
        return axios.get(`${BLOG_URL}/find/${id}`)
    }

    // DELETE
    // http://localhost:2222/blog/api/v1/delete/1
    blogDeleteApi(id) {
        return axios.delete(`${BLOG_URL}/delete/${id}`)
    }

    // UPDATE
    // http://localhost:2222/blog/api/v1/update/1
    blogUpdateApi(id, blogDto) {
        return axios.put(`${BLOG_URL}/update/${id}`, blogDto)
    }

} //end class blogDto

export default new BlogApi();