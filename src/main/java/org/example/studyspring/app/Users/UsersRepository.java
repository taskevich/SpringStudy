package org.example.studyspring.app.Users;

import org.example.studyspring.app.models.Roles.Roles;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<Users, Integer> {
}
