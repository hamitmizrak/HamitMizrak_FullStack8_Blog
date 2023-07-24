import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import reportWebVitals from './reportWebVitals';

// Blog app eklendi
import Blog from './BlogRouter';

// Dil seçeneği i18n ekledim
import './internationalization/i18nlanguage'

// Context için gerekli
import LoginContext from "./context/LoginContext";

// REACTDOM
const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <React.StrictMode>
        <React.Fragment>
            <LoginContext>{/** Context ile en usten en alta veri gonder*/}
                <Blog/>
            </LoginContext>
        </React.Fragment>
    </React.StrictMode>
);

reportWebVitals();
