import i18n from 'i18next';
import {initReactI18next} from 'react-i18next';

i18n.use(initReactI18next).init({
    resources: {
        en:
            {
                translations: {
                    'Register': 'Register',
                    'Login': 'Login',
                    'name': 'Name',
                    'surname': 'Surname',
                    'email': 'email address',
                    'password': 'password',
                    'Password mismatch': 'Password mismatch',
                    'submit': 'submit',
                    'cleaner': 'cleaner',
                    'logout': "Logout",
                    'homepage': "Home Page",
                    'company': "Company Name",
                    'product': "Product",
                    'useFullLink': "useFullLink",
                    'contact': "Contact",
                    'home': "Home",
                    'search': "search",
                    'admin': "Admin Page",
                    'create': "Create",
                    'isRead':' are you reading ?'

                }
            },
        tr:
            {
                translations: {
                    'Register': 'Üye Kayıt',
                    'Login': 'Üye Girişi',
                    'name': 'Kullanıcı Adı',
                    'surname': 'Kullanıcı Soyadı',
                    'email': 'Kullanıcı Email',
                    'password': 'Kullanıcı Şifresi',
                    'Password mismatch': 'Aynı Şifreyi tekrar giriniz',
                    'submit': 'Gönder',
                    'cleaner': 'Temizle',
                    'logout': "Çıkış Yap",
                    'homepage': "Anasayfa",
                    'company': "Şirket Adı",
                    'product': "Ürün",
                    'useFullLink': "Yararlı Linkler",
                    'contact': "İletişim",
                    'home': "Anasayfa",
                    'search': "Arama..",
                    'admin': "Yönetim Sayfası",
                    'create': "Ekle",
                    'isRead':' Okudun mu ?'
                }
            }
    },
    fallbackLng: 'tr',    //fallbackLng: 'en', fall back function    
    ns: ['translations'], //kelimeleri nerede alsın
    defaultNS: 'translations',
    keySeparator: false,
    interpolation: {escapeValue: false, formatSeparator: ','},
    react: {
        wait: true
    }
});
export default i18n;