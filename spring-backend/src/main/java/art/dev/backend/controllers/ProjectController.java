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
import java.security.Principal;


@RestController
@CrossOrigin
@RequestMapping("/api/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    ValidationErrorService errorService;

    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result, Principal principal) {

        ResponseEntity<?> responseEntity = errorService.validateErrors(result);
        if (responseEntity != null) return responseEntity;
        Project projectCreated = projectService.saveOrUpdate(project,principal.getName());
        return new ResponseEntity<>(projectCreated, HttpStatus.CREATED);

    }

    @GetMapping("")
    public Iterable<Project> getAllProjects(Principal principal) {
        return projectService.findAllProjectsByUserCreated(principal.getName());
    }

    @GetMapping("/{identifier}")
    public ResponseEntity<?> getProjectByIdentifier(@PathVariable String identifier, Principal principal) {
        Project project = projectService.findProjectByIdentifier(identifier, principal.getName());
        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }

    @DeleteMapping("/{identifier}")
    public ResponseEntity<?> deleteProjectByIdentifier(@PathVariable String identifier, Principal principal) {
        Long projectId = projectService.deleteProjectByIdentifier(identifier, principal.getName());
        return new ResponseEntity<Long>(projectId, HttpStatus.OK);
    }
}
