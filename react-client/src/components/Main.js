import React, {Component} from "react";
import ProjectItem from "./Project/Item";
import CreateButton from "./Project/CreateButton";

class Dashboard extends Component {
    render() {
        return (
            <div className="projects">
                <div className="container">
                    <div className="row">
                        <div className="col-md-12">
                            <b className="display-4 text-center">Projects</b>
                            <br/>
                            <CreateButton/>
                            <br/>
                            <hr/>
                            <ProjectItem />
                            <ProjectItem/>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default Dashboard;