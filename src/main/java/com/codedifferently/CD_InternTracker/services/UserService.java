package com.codedifferently.CD_InternTracker.services;

import com.codedifferently.CD_InternTracker.exceptions.ResourceCreationException;
import com.codedifferently.CD_InternTracker.exceptions.ResourceNotFoundException;
import com.codedifferently.CD_InternTracker.models.User;

import java.util.List;

public interface UserService {

    User create(User user) throws ResourceCreationException;

    User getById(Long id) throws ResourceNotFoundException;

    User getByEmail(String email) throws ResourceNotFoundException;

    List<User> getAll();

    User update(Long id, User userDetail);

    void delete(Long id);
}
