import React, {Component} from 'react';
import {Link} from 'react-router-dom';

class CreateButton extends Component {
    render() {
        return (
            <React.Fragment>
                <Link to={'/create'} className={'btn btn-info btn-lg'} >
                    Create project
                </Link>
            </React.Fragment>
        );
    }
}

export default CreateButton;