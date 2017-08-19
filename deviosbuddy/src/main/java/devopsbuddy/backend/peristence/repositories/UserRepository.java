package devopsbuddy.backend.peristence.repositories;

import org.springframework.data.repository.CrudRepository;

import devopsbuddy.backend.peristence.domain.backend.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
