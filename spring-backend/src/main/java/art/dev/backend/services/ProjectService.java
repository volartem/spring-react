package art.dev.backend.services;

import art.dev.backend.domains.Project;
import art.dev.backend.domains.User;
import art.dev.backend.exceptions.ProjectIdException;
import art.dev.backend.repositories.ProjectRepository;
import art.dev.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    UserRepository userRepository;

    public Project saveOrUpdate(Project project, String username) {

        if (project.getId() != null) {
            Project existingProject = projectRepository.findByProjectIdentifier(project.getProjectIdentifier());

            // check if project exists for another users
            if (existingProject != null && !existingProject.getUserCreated().equals(username)) {
                throw new ProjectIdException("Project identifier '" + existingProject.getProjectIdentifier() + "' has already existed.");
            }

            // update
            if (existingProject != null) {
                existingProject.setDescription(project.getDescription());
                existingProject.setProjectName(project.getProjectName());
                existingProject.setStartDate(project.getStartDate());
                existingProject.setEndDate(project.getEndDate());
                existingProject.setUpdatedAt(new Date());
                project = existingProject;
            }
        }
        try {
            // save or create new
            User user = userRepository.findByUsername(username);
            if (user != null) {
                project.setUser(user);
                project.setUserCreated(user.getUsername());
            }
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);

        } catch (Exception e) {
            throw new ProjectIdException("Project with projectIdentifier " + project.getProjectIdentifier().toUpperCase()
                    + " already exists!");
        }

    }


    public Project findProjectByIdentifier(String identifier, String username) {

        Project project = projectRepository.findByProjectIdentifier(identifier.toUpperCase());
        if (project == null) {
            throw new ProjectIdException("Project identifier '" + identifier + "' does not exist");
        }

        if (!project.getUserCreated().equals(username)) {
            throw new ProjectIdException("Project identifier '" + identifier + "' does not exist for current user");
        }
        return project;
    }

    public Iterable<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    public Iterable<Project> findAllProjectsByUserCreated(String username) {
        return projectRepository.findAllByUserCreated(username);
    }

    public Long deleteProjectByIdentifier(String identifier, String username) {
        Project currentProject = findProjectByIdentifier(identifier, username);
        Long id = currentProject.getId();
        projectRepository.delete(currentProject);
        return id;
    }
}
