import React, {Component} from 'react';
import Dashboard from './components/Main';
import Header from './components/Layouts/Header'
import Footer from "./components/Layouts/Footer";
import {BrowserRouter, Route } from  'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/css/bootstrap-grid.css';
import Form from './components/Project/Form'

class App extends Component {
    render() {
        return (
            <BrowserRouter>
                <div className="App">
                    <Header/>
                        <Route exact path={'/'} component={Dashboard} />
                        <Route exact path={'/create-project'} component={Form} />
                    <Footer/>
                </div>
            </BrowserRouter>
        );
    }
}

export default App;
