import axios from "axios";

// Sabit URL
const EMAIL_URL = "/email/api/v1"

// CLASS
class EmailApi {

    // EMAIL API
    blogSendEmail(emailDto) {
        return axios.post(`${EMAIL_URL}/basic/email`, emailDto);
    } // end Function
} //end class

export default new EmailApi();
