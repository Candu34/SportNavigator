package com.example.sportnavigator.mapper;


import com.example.sportnavigator.DTO.EncodedImage;
import com.example.sportnavigator.DTO.UserDTO;
import com.example.sportnavigator.Models.CourtImage;
import com.example.sportnavigator.Models.User;
import com.example.sportnavigator.Models.UserImage;
import com.example.sportnavigator.util.exceptions.UserNotCreatedException;
import org.apache.tomcat.util.codec.binary.Base64;
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
        EncodedImage encodedImage = ImageToEncodedImage(user.getImage());
        userDTO.setImage(encodedImage);

        return userDTO;
    }

    public User UserDTOToUser(UserDTO userDTO) {
        if(userDTO == null)
            throw new UserNotCreatedException("Error to create user");

        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        UserImage image = EncodedImageToImage(userDTO.getImage(), user);
        user.setImage(image);
        return user;
    }

    public UserImage EncodedImageToImage(EncodedImage encodedImage, User user){
        UserImage image = new UserImage();

        byte[] data = Base64.decodeBase64(encodedImage.getData());
        image.setBytes(data);
        image.setMime(encodedImage.getMime());
        image.setUser(user);

        return image;
    }

    public <T> EncodedImage ImageToEncodedImage(T image){
        EncodedImage encodedImage = new EncodedImage();
        if(image instanceof UserImage) {
            encodedImage.setMime(((UserImage) image).getMime());
            encodedImage.setData(Base64.encodeBase64String(((UserImage) image).getBytes()));
        }else if (image instanceof CourtImage){
            encodedImage.setMime(((CourtImage) image).getMime());
            encodedImage.setData(Base64.encodeBase64String(((CourtImage) image).getBytes()));
        }
        return encodedImage;
    }
}
