package com.example.vacationmodule.services;


import com.example.vacationmodule.domain.User;

import java.util.List;

public interface UserService {
  void save(User user);
  List<User> findAll();
  User findById(Long id);

}
