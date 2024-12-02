package org.example.springdatajpa.repositories;

import org.example.springdatajpa.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
