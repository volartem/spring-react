package art.dev.backend.controllers;

import art.dev.backend.domains.Project;
import art.dev.backend.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;


    @PostMapping("")
    public ResponseEntity<Project> createNewProject(@RequestBody Project project) {

        Project projectCreated = projectService.saveOrUpdate(project);
        return new ResponseEntity<Project>(projectCreated, HttpStatus.CREATED);

    }
}
