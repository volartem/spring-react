import React, {Component} from 'react';
import Dashboard from './components/Main';
import Header from './components/Layouts/Header'
import Footer from "./components/Layouts/Footer";
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/css/bootstrap-grid.css';


class App extends Component {
    render() {
        return (
            <div className="App">
                <Header/>
                <Dashboard/>
                <Footer/>
            </div>
        );
    }
}

export default App;
