import React, { Component } from "react";
import { Link } from 'react-router-dom';


class ProjectItem extends Component {
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
                                <Link className={'alert alert-success'} to={`/update/${this.props.project.projectIdentifier}`}>
                                    <li className="btn ">
                                        <i className="fa fa-edit pr-1"> Update Project Info</i>
                                    </li>
                                </Link>
                                <a href="" className={'alert alert-danger'}>
                                    <li className="btn ">
                                        <i className="fa fa-minus-circle pr-1"> Delete Project</i>
                                    </li>
                                </a>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default ProjectItem;