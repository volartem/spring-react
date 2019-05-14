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
                        <Footer/>
                    </div>
                </BrowserRouter>
            </Provider>
        );
    }
}

export default App;
