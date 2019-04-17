package art.dev.backend.controllers;

import art.dev.backend.domains.Project;
import art.dev.backend.services.ProjectService;
import art.dev.backend.services.ValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@CrossOrigin
@RequestMapping("/api/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    ValidationErrorService errorService;

    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) {

        ResponseEntity<?> responseEntity = errorService.validateErrors(result);
        if (responseEntity != null) return responseEntity;
        Project projectCreated = projectService.saveOrUpdate(project);
        return new ResponseEntity<Project>(projectCreated, HttpStatus.CREATED);

    }

    @GetMapping("")
    public Iterable<Project> getAllProjects() {
        return projectService.findAllProjects();
    }

    @GetMapping("/{identifier}")
    public ResponseEntity<?> getProjectByIdentifier(@PathVariable String identifier) {
        Project project = projectService.findProjectByIdentifier(identifier);
        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }

    @DeleteMapping("/{identifier}")
    public ResponseEntity<?> deleteProjectByIdentifier(@PathVariable String identifier) {
        Long projectId = projectService.deleteProjectByIdentifier(identifier);
        return new ResponseEntity<Long>(projectId, HttpStatus.OK);
    }
}
