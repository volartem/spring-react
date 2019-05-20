import React, {Component} from 'react';
import Dashboard from './components/Main';
import Header from './components/Layouts/Header'
import Footer from "./components/Layouts/Footer";
import {BrowserRouter, Route} from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/css/bootstrap-grid.css';
import {Provider} from "react-redux";
import store from "./store";
import UpdateProject from "./components/Project/UpdateProject";
import CreateProject from "./components/Project/CreateProject";
import Register from "./components/User/Register";
import Login from "./components/User/Login";
import setJWToken from "./securityUtils/setJWToken";
import {SET_CURRENT_USER} from "./actions/types";
import jwt_decode from "jwt-decode";
import {logout} from "./actions/securityActions";


const jwtToken = localStorage.jwtToken;

if (jwtToken) {
    setJWToken(jwtToken);
    const decoded_jwtToken = jwt_decode(jwtToken);
    store.dispatch({
        type: SET_CURRENT_USER,
        payload: decoded_jwtToken
    });

    const currentTime = Date.now() / 1000;
    if (decoded_jwtToken.exp < currentTime) {
        store.dispatch(logout());
        window.location.href = "/";
    }
}


class App extends Component {
    render() {
        return (
            <Provider store={store}>
                <BrowserRouter>
                    <div className="App">
                        <Header/>
                        <Route exact path={'/'} component={Dashboard}/>
                        <Route exact path="/register" component={Register} />
                        <Route exact path="/login" component={Login} />
                        <Route exact path={'/create'} component={CreateProject}/>
                        <Route exact path="/update/:id" component={UpdateProject} />
                        {/*<Footer/>*/}
                    </div>
                </BrowserRouter>
            </Provider>
        );
    }
}

export default App;
