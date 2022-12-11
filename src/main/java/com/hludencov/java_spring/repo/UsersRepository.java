package com.hludencov.java_spring.repo;

import com.hludencov.java_spring.models.Group;
import com.hludencov.java_spring.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsersRepository extends CrudRepository<User, Long> {
    List<User> findByLoginContains(String title);
    List<User> findByLogin(String title);
    default List<User> findActive() {
        return this.findByActive(true);
    }
    List<User> findByActive(boolean active);
    List<User> findByGroupsContaining(Group group);


}
