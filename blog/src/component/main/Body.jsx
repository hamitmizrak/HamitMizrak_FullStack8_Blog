import React, {Component} from 'react'
import {withTranslation} from 'react-i18next'

class Body extends Component {
    constructor(props) {
        super(props);
        //STATE
        this.state = {}
        //BIND
    }

//CDM
    render() {
        return (
            <React.Fragment>
                Lorem ipsum dolor sit amet consectetur adipisicing elit. Rem laudantium nemo est, blanditiis, quos
                dolor, animi magni nesciunt officiis facilis tenetur possimus eos omnis pariatur enim quis voluptate
                debitis necessitatibus?
                reiciendis fuga animi itaque ducimus?
            </React.Fragment>
        ) //end return
    } //end render
} //end class
//i18n sarmaladı
export default withTranslation()(Body);