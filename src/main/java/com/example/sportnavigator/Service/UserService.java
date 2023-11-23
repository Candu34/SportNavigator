package com.example.sportnavigator.Service;


import com.example.sportnavigator.Models.User;
import com.example.sportnavigator.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public long saveUser(User user){
        return userRepository.save(user).getId();
    }

    public User getUserById(long id){
        return userRepository.findById(id).get();
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Transactional
    public void deleteUser(long id){
        userRepository.deleteById(id);
    }

    @Transactional
    public void updateUser(User updatedUser, long id){
        updatedUser.setId(userRepository.findById(id).get().getId());
    }

}
