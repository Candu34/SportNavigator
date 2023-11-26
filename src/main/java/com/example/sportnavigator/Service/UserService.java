package com.example.sportnavigator.Service;


import com.example.sportnavigator.Models.User;
import com.example.sportnavigator.repository.UserRepository;
import com.example.sportnavigator.util.exceptions.UserExistingEmailException;
import com.example.sportnavigator.util.exceptions.UserNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    @Transactional()
    public void saveUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null)
            throw new UserExistingEmailException("User with this message allready exist");
        else {
            userRepository.save(user);
        }
    }

    public User getUserById(long id){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new UserNotFoundException("User not found");
        }
        else {
            return user.get();
        }
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Transactional()
    public void deleteUser(long id){
        userRepository.deleteById(id);
    }




}
