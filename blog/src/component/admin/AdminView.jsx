import React, {Component} from 'react';
import {withTranslation} from "react-i18next";

class AdminView extends Component {
    render() {
        return (
            <div>
                View
            </div>
        );
    }
}

//i18n sarmaladı
export default withTranslation()(AdminView)