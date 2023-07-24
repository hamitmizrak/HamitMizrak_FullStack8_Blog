//rcc => TAB

//axios
import axios from "axios";

//Const URL
const LOGIN_URL = "/login/api/v1/authentication";

class UserLoginServices  {
    //Login için
    onClickLoginService(loginObject){
        // Header için 3 parametre veriliyor.
        // return axios.post(REGISTER_API_BASE_URL, register, { headers: { 'accept-language': 'tr' } });
        return axios.post(
            LOGIN_URL,
            {headers: {"Accept": "application/json", "Content-Type": "application/json"}},
            {auth:loginObject}) ;
    }

    
}

export default new UserLoginServices;