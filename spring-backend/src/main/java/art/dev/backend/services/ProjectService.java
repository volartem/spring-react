package art.dev.backend.services;

import art.dev.backend.domains.Project;
import art.dev.backend.exceptions.ProjectIdException;
import art.dev.backend.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;


    public Project saveOrUpdate(Project project) {

        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        } catch (Exception e) {
            throw new ProjectIdException("Project with projectIdentifier " + project.getProjectIdentifier().toUpperCase()
                    + " already exists!");
        }
    }


    public Project findProjectByIdentifier(String idetifier) {

        Project project = projectRepository.findByProjectIdentifier(idetifier.toUpperCase());
        if (project == null) {
            throw new ProjectIdException("Project identifier '" + idetifier + "' does not exist");
        }
        return project;
    }


    public Iterable<Project> findAllProjects() {
        return projectRepository.findAll();
    }
}
