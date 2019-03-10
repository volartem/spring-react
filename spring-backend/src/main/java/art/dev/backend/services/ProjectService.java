package art.dev.backend.services;

import art.dev.backend.domains.Project;
import art.dev.backend.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;


    public Project saveOrUpdate(Project project) {

        return projectRepository.save(project);
    }

}
