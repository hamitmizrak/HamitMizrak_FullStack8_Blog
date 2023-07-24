import React, {Component} from 'react'

// i18n için
import {withTranslation} from 'react-i18next'

// Toast
import toast, {Toaster} from 'react-hot-toast';

// css
import './profile_card.css'

// Bayrak için
import OtherLanguageReusability from '../../internationalization/OtherLanguageReusability';

// Context
import {LoginAuthenticationContext} from "../../context/LoginContext";

class ProfileCard extends Component {


    // Componentte görünür adı
    static displayName = "Profile Card";

    // CONTEXT ALMAK
    static contextType = LoginAuthenticationContext;

    constructor(props) {
        super(props);
        //STATE
        this.state = {}
        //BIND
    }

    //CDM
    componentDidMount() {
         //toast('Admin1242');
    }

    //RENDER
    render() {
        // CONTEXT
        const {state, logout} = this.context;
        const {isLogin, username, rolesId, rolesName} = state;

        /*
        const notify = () => toast('Admin');

        const notify = () => toast.success('Admin');

        const notify = () => toast.error('Admin');

        const notify = () => toast.promise(
            saveSettings(settings),
            {
                loading: 'Saving...',
                success: <b>Settings saved!</b>,
                error: <b>Could not save.</b>,
            }
        );

        const notify = () => toast(
             "This toast is super big. I don't think anyone could eat it in one bite.\n\nIt's larger than you expected. You eat it but it does not seem to get smaller.",
             {
                 duration: 6000,
             }
         );

        const notify = () => toast.success('Look at my styles.', {
            style: {
                border: '1px solid #713200',
                padding: '16px',
                color: '#713200',
            },
            iconTheme: {
                primary: '#713200',
                secondary: '#FFFAEE',
            },
        });

        const notify = () => toast.custom((t) => (
            <div
                className={`${
                    t.visible ? 'animate-enter' : 'animate-leave'
                } max-w-md w-full bg-white shadow-lg rounded-lg pointer-events-auto flex ring-1 ring-black ring-opacity-5`}>
                <div className="flex-1 w-0 p-4">
                    <div className="flex items-start">
                        <div className="flex-shrink-0 pt-0.5">
                            <img
                                className="h-10 w-10 rounded-full"
                                src="https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-1.2.1&ixqx=6GHAjsWpt9&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=2.2&w=160&h=160&q=80"
                                alt=""
                            />
                        </div>
                        <div className="ml-3 flex-1">
                            <p className="text-sm font-medium text-gray-900">
                                Emilia Gates
                            </p>
                            <p className="mt-1 text-sm text-gray-500">
                                Sure! 8:30pm works great!
                            </p>
                        </div>
                    </div>
                </div>
                <div className="flex border-l border-gray-200">
                    <button
                        onClick={() => toast.dismiss(t.id)}
                        className="w-full border border-transparent rounded-none rounded-r-lg p-4 flex items-center justify-center text-sm font-medium text-indigo-600 hover:text-indigo-500 focus:outline-none focus:ring-2 focus:ring-indigo-500">
                        Close
                    </button>
                </div>
            </div>
        ))

         const notify = () => toast('Admin');
        <button onClick={notify}>Make me a toast 44</button>
         <Toaster position="top-right"  reverseOrder={true}/>
        */

        return (
            <React.Fragment>

                <section class="section about-section gray-bg" id="about">
                    <div class="container">
                        <div class="row align-items-center flex-row-reverse">
                            <div class="col-lg-6">
                                <div class="about-text go-to">

                                    {/*top-left top-center top-right bottom-left bottom-center bottom-right*/}

                                    <h3 class="dark-color">{username}</h3>
                                    <h6 class="theme-color lead">{rolesId} {rolesName}</h6>
                                    <p>I <mark>design and develop</mark> services for customers of all sizes,
                                        specializing in creating stylish, modern websites, web services and online
                                        stores. My passion is to design digital user experiences through the bold
                                        interface and meaningful interactions.
                                    </p>
                                    <div class="row about-list">
                                        <div class="col-md-6">
                                            <div class="media">
                                                <label>Birthday</label>
                                                <p>4th april 1998</p>
                                            </div>
                                            <div class="media">
                                                <label>Age</label>
                                                <p>22 Yr</p>
                                            </div>
                                            <div class="media">
                                                <label>Residence</label>
                                                <p>Canada</p>
                                            </div>
                                            <div class="media">
                                                <label>Address</label>
                                                <p>California, USA</p>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="media">
                                                <label>E-mail</label>
                                                <p>info@domain.com</p>
                                            </div>
                                            <div class="media">
                                                <label>Phone</label>
                                                <p>820-885-3321</p>
                                            </div>
                                            <div class="media">
                                                <label>Skype</label>
                                                <p>skype.0404</p>
                                            </div>
                                            <div class="media">
                                                <label>Freelance</label>
                                                <p>Available</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="about-avatar">
                                    <img src="https://bootdey.com/img/Content/avatar/avatar7.png" title="" alt=""></img>
                                </div>
                            </div>
                        </div>
                        <div class="counter">
                            <div class="row">
                                <div class="col-6 col-lg-3">
                                    <div class="count-data text-center">
                                        <h6 class="count h2" data-to="500" data-speed="500">500</h6>
                                        <p class="m-0px font-w-600">Happy Clients</p>
                                    </div>
                                </div>
                                <div class="col-6 col-lg-3">
                                    <div class="count-data text-center">
                                        <h6 class="count h2" data-to="150" data-speed="150">150</h6>
                                        <p class="m-0px font-w-600">Project Completed</p>
                                    </div>
                                </div>
                                <div class="col-6 col-lg-3">
                                    <div class="count-data text-center">
                                        <h6 class="count h2" data-to="850" data-speed="850">850</h6>
                                        <p class="m-0px font-w-600">Photo Capture</p>
                                    </div>
                                </div>
                                <div class="col-6 col-lg-3">
                                    <div class="count-data text-center">
                                        <h6 class="count h2" data-to="190" data-speed="190">190</h6>
                                        <p class="m-0px font-w-600">Telephonic Talk</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </React.Fragment>
        ) //end return
    } //end render
} //end class
//i18n sarmaladı
export default withTranslation()(ProfileCard);