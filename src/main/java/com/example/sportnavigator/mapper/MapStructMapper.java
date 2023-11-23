package com.example.sportnavigator.mapper;


import com.example.sportnavigator.DTO.UserDTO;
import com.example.sportnavigator.Models.User;
import com.example.sportnavigator.util.exceptions.UserNotCreatedException;
import org.springframework.stereotype.Component;

@Component
public class MapStructMapper {


    public UserDTO userToUserDTO(User user) {
        if (user == null)
            return null;

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getName());
        userDTO.setPassword(user.getPassword());
        userDTO.setUserImage(user.getUserImage());

        return userDTO;
    }

    public User UserDTOToUser(UserDTO userDTO) {
        if(userDTO == null)
            throw new UserNotCreatedException("Error to create user");

        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());

        return user;
    }
}
