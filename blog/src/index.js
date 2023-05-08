import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import reportWebVitals from './reportWebVitals';

// Blog app eklendi
import Blog from './Blog';

// Dil seçeneği i18n ekledim
import './internationalization/i18nlanguage'

// REACTDOM
const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <React.StrictMode>
        <React.Fragment>
            <Blog/>
        </React.Fragment>
    </React.StrictMode>
);

reportWebVitals();
