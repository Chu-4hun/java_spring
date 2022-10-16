package com.hludencov.java_spring.repo;

import com.hludencov.java_spring.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsersRepository extends CrudRepository<User, Long> {
    List<User> findByNicknameContains(String title);
}
