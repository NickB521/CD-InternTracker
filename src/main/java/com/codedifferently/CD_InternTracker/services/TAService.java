package com.codedifferently.CD_InternTracker.services;

import com.codedifferently.CD_InternTracker.exceptions.ResourceCreationException;
import com.codedifferently.CD_InternTracker.exceptions.ResourceNotFoundException;
import com.codedifferently.CD_InternTracker.models.TA;

import java.util.List;

public interface TAService {



   User create(User user) throws ResourceCreationException;
    User getById(Long id) throws ResourceNotFoundException;

  User getByEmail(String email) throws ResourceNotFoundException;

  List<User> getAll();
  User update(Long id, User userDetail);

   void delete(Long id);

    User create(User user) throws ResourceCreationException;

    TA create(TA TA) throws ResourceCreationException;


    TA getById(Long id) throws ResourceNotFoundException;

    TA getByEmail(String email) throws ResourceNotFoundException;

    List<TA> getAll();

    TA update(Long id, TA TADetail);

    void delete(Long id);

}
