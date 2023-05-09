import React, {Component} from 'react';
import {withTranslation} from "react-i18next";

class AdminCreate extends Component {
    render() {
        return (
            <div>
                Create
            </div>
        );
    }
}
//i18n sarmaladı
export default withTranslation()(AdminCreate)