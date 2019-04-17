import React, {Component} from 'react';
import Dashboard from './components/Main';
import Header from './components/Layouts/Header'
import Footer from "./components/Layouts/Footer";
import {BrowserRouter, Route} from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/css/bootstrap-grid.css';
import Form from './components/Project/Form'
import {Provider} from "react-redux";
import store from "./store";


class App extends Component {
    render() {
        return (
            <Provider store={store}>
                <BrowserRouter>
                    <div className="App">
                        <Header/>
                        <Route exact path={'/'} component={Dashboard}/>
                        <Route exact path={'/create-project'} component={Form}/>
                        <Footer/>
                    </div>
                </BrowserRouter>
            </Provider>
        );
    }
}

export default App;
