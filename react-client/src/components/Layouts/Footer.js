import React, {Component} from "react";
import logo from "../../media/logo.svg";
import '../../styles/Header.css';


class Footer extends Component {
    render() {
        return (
            <footer className="App-header" style={{marginTop: '10px', }}>
                <div className={'container'}>
                    <div className={'row'}>
                        <div className={'col-md-2'}>
                            <img src={logo} className="App-logo" alt="logo"/>
                        </div>
                        <div className={'col-md-8'}>
                            Created by <a
                            className="App-link"
                            href="https://github.com/volartem"
                            target="_blank"
                            rel="noopener noreferrer"> Volartem</a>
                        </div>
                        <div className={'col-md-2'}>
                            <img src={logo} className="App-logo" alt="logo"/>
                        </div>
                    </div>
                </div>


            </footer>
        );
    }
}

export default Footer;