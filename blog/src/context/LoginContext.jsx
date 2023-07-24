import React, { Component } from 'react'

//Context tanımladım
export const LoginAuthenticationContext = React.createContext();

class LoginContext extends Component {
    // Componentte görünür adı
    static displayName = "Login_Context"

    state = {
        // kullanıcı bilgileri: isLogin: false olacak
        isLogin: false,
        username: undefined,
        displayName: undefined,
        image: undefined,
        password: undefined,
        rolesName:undefined,
        rolesId:undefined,
        name:undefined,
        surname:undefined,
        email:undefined,
    }

    // LOGIN
    // is login ise => isLogin: true olacak
    isLogin = (authenticationState) => {
        this.setState({
            // 1.YOL
            // username: authenticationState.username,
            // displayName: authenticationState.displayName,
            // image: authenticationState.image,
            // password: authenticationState.password,
            // 2.YOL: bütün fieldleri => ...authenticationState ile yapabilirim.
            ...authenticationState,
            isLogin: true
        })
    }

    // LOGOUT
    logout = () => {
        this.setState({
            isLogin: false,
            username: undefined,
            displayName: undefined,
            image: undefined,
            password: undefined,
            rolesName:undefined,
            rolesId:undefined,
            name:undefined,
            surname:undefined,
            email:undefined
        })
    }

    render() {
        return (
            <LoginAuthenticationContext.Provider value={{
                //split operator ... => bütün state hepsini al istenen yere gönderecek
                state: { ...this.state },
                isLogin: this.isLogin,
                logout: this.logout
            }}>
                {this.props.children}
            </LoginAuthenticationContext.Provider>
        )
    }
} //end LoginContext
export default LoginContext
