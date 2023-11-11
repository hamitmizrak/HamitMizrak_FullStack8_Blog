## Google Chrome Plugin
- React Developer Tools: https://chrome.google.com/webstore/detail/react-developer-tools/fmkadmapgofadopljbjfkapdkoienihi
- React Redux DevTools : https://chrome.google.com/webstore/detail/redux-devtools/lmhkpmbekcpmknklioeibfkpmmfibljd


---------------------------------------------------------------------------------------------------------------------------
## React 
- 2011 yılında Facebook tarafından geliştirildi.
- Open source (Açık kaynak kodludur)
- React ES6
- Facebook, Whatsapp, Instagram, Netflix, BBC, Uber, Tesla, CloudFlare, ...
- SPA bir yapıya sahiptir.
- Reusability
- MVC M:Java View: React JS
- React'ta herşey nesnedir.
- Stateless: Durumsuz (State yoktur) Function Component: useState()
- Stafull  : Durumlu  (State vardır) Class Component: this.state={} this.setState({})
- Virtual DOM: Yazdığımız yeni kodları görüp hemen çalıştırmaya yarar (Virtual Dom Diff Algoritması)

---------------------------------------------------------------------------------------------------------------------------
## Bilinmesi Gerekenler 
- Babel   : ES5 ve üstündeki sürümleri ES5'e compiler yapıyor.
- WebPack : Uygulamamızı tek bir JS dosyası haline getirebiliyor. 

---------------------------------------------------------------------------------------------------------------------------
## React Life Cycle
- constructor
- Render
- CDM  (ComponentDidMound)     Servis /Api hazırlık
- CDU  (ComponentDidUpdate)    Componentlerin güncellenmesinde kullanıyoruz.
- CWUM (ComponentWillUnMount)  Componentlerin içeriklerini temizleme.

---------------------------------------------------------------------------------------------------------------------------
## React Kurulumu
npx create-react-app blog
cd blog
npm start

---------------------------------------------------------------------------------------------------------------------------
## package.json
npm install axios               --save       (Froentend ile backent arasında veri alış verişini sağlıyor)
npm install react-router-dom    --save       (Sayfalar arasında geçiş)
npm install i18next             --save       (Dil için)
npm install react-i18next       --save       (Dil için)
npm install redux react-redux   --save       (Auth)
npm install redux-thunk         --save       (middle Ware : Ara katman )
npm install secure-ls           --save       (Local Storageta bilgileri saklamak istioyrsak)

DİKKAT: react ile react-dom aynı sürüm olmalıdır.

-- OTHER-- 
npm install bootstrap --save      (Bootstrap kullanmak için)
npm install node-sass --save      (Sass kullanmak için)
npm install jquery --save          (Jquery Kullanmak Icin)
npm install popper.js --save        (Popper Js Kullanmak Icin)
npm install @popperjs/core --save   (Popper Js Kullanmak Icin)
npm install fontawesome --save      (Font Awesome Kullanmak Icin)
npm install sweetalert2 --save      (Alert ve Confirm Kullanmak Icin)
npm install moment --save          (Tarih Saat Formatlarını Değiştirmek Icin)
npm install lodash --save          (Array, Object gibi işler yapmanız için)
npm install node-sass --save       (Scss dosyalarını sass olarak dönüştürmen
npm install nodemon            --save-dev    (Backend için)

---------------------------------------------------------------------------------------------------------------------------
## package.json
npm uninstall axios 
npm uninstall react-router-dom 

---------------------------------------------------------------------------------------------------------------------------
## package.json 
{
  "name": "frontend_11",
  "version": "0.1.0",
  "private": true,
  "dependencies": {
    "@testing-library/jest-dom": "^5.17.0",
    "@testing-library/react": "^13.4.0",
    "@testing-library/user-event": "^13.5.0",
    "axios": "^1.5.1",
    "i18next": "^23.5.1",
    "react": "^18.2.0",
    "react-dom": "^18.2.0",
    "react-i18next": "^13.3.0",
    "react-redux": "^8.1.3",
    "react-router-dom": "^6.16.0",
    "react-scripts": "5.0.1",
    "redux": "^4.2.1",
    "redux-thunk": "^2.4.2",
    "secure-ls": "^1.2.6",
    "web-vitals": "^2.1.4"
  },
  "scripts": {
    "start": "react-scripts start",
    "build": "react-scripts build",
    "test": "react-scripts test",
    "eject": "react-scripts eject"
  },
  "eslintConfig": {
    "extends": [
      "react-app",
      "react-app/jest"
    ]
  },
  "browserslist": {
    "production": [
      ">0.2%",
      "not dead",
      "not op_mini all"
    ],
    "development": [
      "last 1 chrome version",
      "last 1 firefox version",
      "last 1 safari version"
    ]
  },
  "proxy": "http://localhost:4444/"
}


npm i
proxy: DNS değiştirildiğinde hızlıca entegre edebilmek içindir.

---------------------------------------------------------------------------------------------------------------------------
## HTML+CSS+JS                               JSX
for                                         HtmlFor
style="background-color:black"              style={{backgroundColor:"black"}}
class                                       className
onclick                                     onClick
tag                                         tag kapanışa dikkat
/* */                                       {/* */}
<div> </div>                                <></>
{ }                                         {}

JSX: Javascript XML (Js in Html)
JSX: Reactta söz dizimidir.
JSX: React elementleri oluşturmaya yarar.
Reactta JSX'e erişebiliriz.
import logo from './logo.svg'; // Import edildi


---------------------------------------------------------------------------------------------------------------------------
# Html To JSX
Transform: https://transform.tools/html-to-jsx


---------------------------------------------------------------------------------------------------------------------------
# .gitignore
/node_modules
/build


---------------------------------------------------------------------------------------------------------------------------
Props: (Basit Ölçekli uygulama veya basit veri gönderimi)
- Basit uygulamaya örnek: bir kaç tane componentler(fuction, class) varsa bu kategoriye yerleştirebilirz.
- Bir Componenttten başka bir omponentte veri göndermenin en kolay yoludur. 
- Ancak veri göndermek kolay verileri yönetmek zordur.
- key value olarak çalışıyor.
- classlarda constructor yardımıyla handling ediyoruz. constructor(props){super(props)}
- functionlarda parametre yardımıyla alıyoruz ==> function Header({t, i18n, props})


Context API: (Orta Ölçekli uygulamalarda)
- Orta uygulamaya örnek:  componentler(fuction, class) sayısı fazla olmaya başlıyorsa varsa bu kategoriye yerleştirebilirz.
- Her bir componentte görünmesini istediğiniz verileri varsa bunu kullanabilirsiniz 
- yani Provider ile tüm state tutan component ile sağlayabiliriz.


Redux: (Büyük Ölçekli uygulamalarda)
- Büyük uygulamaya örnek:  componentler(fuction, class) sayısı fazla olmaya başlıyorsa varsa bu kategoriye yerleştirebilirz.
- yani Provider ile tüm state tutan component ile sağlayabiliriz.

Prop drilling: Küçük uygulamalarda (props) bir compontten başka bir componentte oradan başka bir componentte veri göndermek istiyorsak bunu kullanıyoruz



