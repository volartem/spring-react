package art.dev.backend.repositories;

import art.dev.backend.domains.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

    @Override
    Iterable<Project> findAllById(Iterable<Long> iterable);

    @Override
    Iterable<Project> findAll();

    Project findByProjectIdentifier(String identifier);

    List<Project> findAllByUserCreated(String username);

    Project findByProjectIdentifierAndUserCreated(String identifier, String username);

}
