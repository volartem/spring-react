import React, {Component} from "react";
import {Link} from 'react-router-dom';
import {connect} from "react-redux";
import {getProject, deleteProject} from "../../actions/projectActions";
import PropTypes from "prop-types";


class ProjectItem extends Component {

    deleteItemHandler(identifier) {
        // TODO: need adequate confirm modal
        this.props.deleteProject(identifier);
    }

    render() {
        return (
            <div className="container">
                <div className="card card-body bg-light mb-3">
                    <div className="row">
                        <div className="col-2">
                            <span className="mx-auto">{this.props.project.projectIdentifier}</span>
                        </div>
                        <div className="col-lg-6 col-md-4 col-8">
                            <h3>{this.props.project.projectName}</h3>
                            <p>{this.props.project.description}</p>
                        </div>
                        <div className="col-md-4 d-none d-lg-block">
                            <ul className="list-group">
                                <a href="#" className={'alert alert-primary'}>
                                    <li className="btn ">
                                        <i className="fa fa-flag-checkered pr-1"> Project Board </i>
                                    </li>
                                </a>
                                <Link className={'alert alert-success'}
                                      to={`/update/${this.props.project.projectIdentifier}`}>
                                    <li className="btn ">
                                        <i className="fa fa-edit pr-1"> Update Project Info</i>
                                    </li>
                                </Link>
                                <li className="btn alert alert-danger"
                                    onClick={this.deleteItemHandler.bind(this, this.props.project.projectIdentifier)}>
                                    <i className="fa fa-minus-circle pr-1"> Delete Project</i>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

ProjectItem.propTypes = {
    deleteProject: PropTypes.func.isRequired,
    getProject: PropTypes.func.isRequired,
};


export default connect(
    null,
    {getProject, deleteProject}
)(ProjectItem);