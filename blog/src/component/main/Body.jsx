import React, { Component } from 'react'

// i18n için
import { withTranslation } from 'react-i18next'

// Bayrak için
import OtherLanguageReusability from '../../internationalization/OtherLanguageReusability';

// Context
import { LoginAuthenticationContext } from "../../context/LoginContext";
import toast, {Toaster} from "react-hot-toast";

class Body extends Component {

    // Componentte görünür adı      
    static displayName = "Body";

    // CONTEXT ALMAK
    static contextType = LoginAuthenticationContext;

    constructor(props) {
        super(props);
        //STATE
        this.state = {}
        //BIND
    }

    //CDM
    //componentDidMount() {}

    //RENDER
    render() {

        // CONTEXT
        const { state, logout } = this.context;
        const { isLogin, username, rolesId, rolesName } = state;

        return (
            <React.Fragment>
                {/*Toast*/}
                {this.context.state.isLogin &&  toast(rolesName)}

                Anasayfa 5555
                Lorem ipsum dolor sit amet consectetur adipisicing elit. Rem laudantium nemo est, blanditiis, quos
                dolor, animi magni nesciunt officiis facilis tenetur possimus eos omnis pariatur enim quis voluptate
                debitis necessitatibus?
                reiciendis fuga animi itaque ducimus?

                <Toaster position="top-right"  reverseOrder={true}/>
            </React.Fragment>
        ) //end return
    } //end render
} //end class
//i18n sarmaladı
export default withTranslation()(Body);