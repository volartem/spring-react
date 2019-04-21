import React, {Component} from 'react';
import Form from "./Form";

class CreateProject extends Component {

    project = {
        projectName: "",
        projectIdentifier: "",
        description: "",
        start_date: "",
        end_date: ""
    };

    render() {
        return (
            <div>
                <h5 className="display-4 text-center">Create Project</h5>
                <Form project={this.project} history={this.props.history}/>
            </div>
        );
    }
}

export default CreateProject;