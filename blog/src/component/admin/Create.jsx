import React, {Component} from 'react';
import {withTranslation} from "react-i18next";

class Create extends Component {
    render() {
        return (
            <div>
                Create
            </div>
        );
    }
}
//i18n sarmaladı
export default withTranslation()(Create)