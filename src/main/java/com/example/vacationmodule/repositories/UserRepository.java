package com.example.vacationmodule.repositories;

import com.example.vacationmodule.domain.User;
import com.example.vacationmodule.domain.VacationRequest;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User,Long> {
    User findUserById(Long id);
    List<User> findAll();
    User findByUsername(String s);
}
