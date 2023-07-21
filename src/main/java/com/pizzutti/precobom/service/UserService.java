package com.pizzutti.precobom.service;

import com.pizzutti.precobom.model.User;
import com.pizzutti.precobom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

}
