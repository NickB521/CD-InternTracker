package com.codedifferently.CD_InternTracker.services;

import com.codedifferently.CD_InternTracker.exceptions.ResourceCreationException;
import com.codedifferently.CD_InternTracker.exceptions.ResourceNotFoundException;
import com.codedifferently.CD_InternTracker.models.User;
import com.codedifferently.CD_InternTracker.repos.UserRepo;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService{
private UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User create(User user) throws ResourceCreationException {
        Optional<User> optional = userRepo.findByEmail(user.getEmail());
        if(optional.isPresent()){
            throw new ResourceCreationException("User with email exists: " + user.getEmail());
        }
       user = userRepo.save(user);
        return user;
    }

    @Override
    public User getById(Long id) throws ResourceNotFoundException {
        User user = userRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("No user with id: " + id));
        return user;
    }

    @Override
    public User getByEmail(String email) throws ResourceNotFoundException {
       User user = userRepo.findByEmail(email)
                .orElseThrow(()->new ResourceNotFoundException("No user with email: " + email));
        return user;
    }

    @Override
    public List<User> getAll() {
        return userRepo.findAll();
    }

    @Override
    public User update(Long id, User userDetail) {
        User user = getById(id);
        user.setPassword(userDetail.getPassword());
        user.setEmail(userDetail.getEmail());
        user.setPhoneNumber(userDetail.getPhoneNumber());
        user.setName(userDetail.getName());
        user.setAdmin(userDetail.isAdmin());
        user.setTA(userDetail.isTA());
        user = userRepo.save(user);
        return user;


    }

    @Override
    public void delete(Long id) {
        User user = getById(id);
        userRepo.delete(user);
    }
}
