import React, {Component} from 'react';
import Form from "./Form";
import {getProject} from "../../actions/projectActions";
import PropTypes from "prop-types";
import {connect} from "react-redux";


class UpdateProject extends Component {
    constructor() {
        super();

        this.state = {
            project: {
                projectName: "",
                projectIdentifier: "",
                description: "",
                start_date: "",
                end_date: "",
            },
            errors: {}
        };
    }

    componentDidMount() {
        const {id} = this.props.match.params;
        this.props.getProject(id, this.props.history);

    }

    componentWillReceiveProps(nextProps) {
        // if (nextProps.errors) {
        //     this.setState({ errors: nextProps.errors });
        // }
        if (nextProps.project) {
            this.setState({project: nextProps.project});
        }

    }

    render() {
        return (
            <div>
                <h5 className="display-4 text-center">Update Project</h5>
                <Form project={this.state.project} history={this.props.history}/>
            </div>
        );
    }

}

UpdateProject.propTypes = {
    getProject: PropTypes.func.isRequired,
    project: PropTypes.object.isRequired,
};
const mapStateToProps = state => ({
    project: state.all.project,
    errors: state.errors
});

export default connect(
    mapStateToProps,
    {getProject}
)(UpdateProject);