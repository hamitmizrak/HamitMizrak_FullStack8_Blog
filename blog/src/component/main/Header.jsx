import React, { Component } from 'react'
import { withTranslation } from 'react-i18next';

class Header extends Component {
    constructor(props) {
        super(props);
        //STATE
        this.state={

        }

        //BIND
    }

    //RENDER
    render() {
        return (
            // <div>Header</div>
            //  <>Header</>
            <React.Fragment>
                <nav class="navbar navbar-expand-sm navbar-dark bg-dark fixed-top">
                    <div class="container">
                        <a class="navbar-brand" href="blog/src/component/main/Header#"><i class={this.props.logo}></i></a>
                        <button class="navbar-toggler d-lg-none" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavId" aria-controls="collapsibleNavId"
                            aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                        <div class="collapse navbar-collapse" id="collapsibleNavId">
                            <ul class="navbar-nav me-auto mt-2 mt-lg-0">
                                <li class="nav-item">
                                    <a class="nav-link active" href="blog/src/component/main/Header#" aria-current="page">Home <span class="visually-hidden">(current)</span></a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="blog/src/component/main/Header#">Link</a>
                                </li>
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" href="blog/src/component/main/Header#" id="dropdownId" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Dropdown</a>
                                    <div class="dropdown-menu" aria-labelledby="dropdownId">
                                        <a class="dropdown-item" href="blog/src/component/main/Header#">Action 1</a>
                                        <a class="dropdown-item" href="blog/src/component/main/Header#">Action 2</a>
                                    </div>
                                </li>

                            </ul>
                            <form class="d-flex my-2 my-lg-0">
                                <input class="form-control me-sm-2" type="text" placeholder="Search" />
                                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                            </form>
                        </div>
                    </div>
                </nav>

            </React.Fragment>
        )// end return
        
    }//end render
} //end class

//i18n sarmaladı
export default withTranslation()(Header);
