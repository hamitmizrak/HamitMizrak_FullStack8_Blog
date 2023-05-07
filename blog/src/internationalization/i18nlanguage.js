import i18n from 'i18next';
import { initReactI18next } from 'react-i18next';

i18n.use(initReactI18next).init({
    resources: {
        en:
        {
            translations: {
                'Login Register': 'Login Register',
                'Login': 'Login',
                'username': 'Username',
                'email': 'email address',
                'password': 'password',
                'Password mismatch': 'Password mismatch',
                'submit': 'submit',
                'logout': "Logout",
                'homepage':"Home Page",

            }
        },
        tr:
        {
            translations: {
                'Login Register': 'Üye Kayıt',
                'Login': 'Üye Girişi',
                'username': 'Kullanıcı adını giriniz',
                'email': 'email adresini Giriniz',
                'password': 'Şifrenizi Giriniz',
                'Password mismatch': 'Aynı Şifreyi tekrar giriniz',
                'submit': 'Gönder',
                'logout': "Çıkış Yap",
                'homepage':"Anasayfa",
            }
        }
    },
    fallbackLng: 'tr',    //fallbackLng: 'en', fall back function    
    ns: ['translations'], //kelimeleri nerede alsın
    defaultNS: 'translations',
    keySeparator: false,
    interpolation: { escapeValue: false, formatSeparator: ',' },
    react: {
        wait: true
    }
});
export default i18n;