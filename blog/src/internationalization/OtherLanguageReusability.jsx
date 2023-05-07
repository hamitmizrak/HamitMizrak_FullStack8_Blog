//rfc => React Function Component.
//rcc => React Class Component.
//rsc => React Stateles Component (state olmayan).
import React from 'react';

//TR import edelim
//adı tr olsun
import tr from "../image/flag/Turkish.png"
import en from "../image/flag/English.jpg"

// Dil secenegi
import { withTranslation } from 'react-i18next';
import OtherLanguageServices from "./OtherLanguageServices";

//Funksiyon komponent
function OtherLanguageReusability(props) {

    //Bayraklar
    const internationalizationLanguage = language => {
        const { i18n } = props;
        i18n.changeLanguage(language);

        //Hem java tarafından hemde frontend tarafından değişiklik yaptık.
        OtherLanguageServices.headerLanguageServices(language);
    }

    //render
    return (
        <React.Fragment>
            <ul className="navbar-nav ml-auto mt-2 mt-lg-0">
                <li className="nav-item">
                    <a className="dropdown-item44" href="#" onClick={() => internationalizationLanguage('tr')}><img src={tr} style={{ height: "15px", width: "40px;" }} className="me-2 mt-2" alt="TR" /> </a>
                </li>
                <li className="nav-item">
                    <a className="dropdown-item44" href="#" onClick={() => internationalizationLanguage('en')}><img src={en} style={{ height: "15px", width: "20px" }} className="me-2 mt-2" alt="EN" /></a>
                </li>
                <div className="container">
                </div>
            </ul>
        </React.Fragment>
    );
}
// export default UserRegister
//  Higher Order Component: monad componenti başka bir componentin içine  ekleyip oradanda yeni sonuclar elde etmek
export default withTranslation()(OtherLanguageReusability)

