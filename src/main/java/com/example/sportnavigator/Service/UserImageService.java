package com.example.sportnavigator.Service;

import com.example.sportnavigator.Models.User;
import com.example.sportnavigator.Models.UserImage;
import com.example.sportnavigator.repository.UserImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserImageService {
    private final UserImageRepository imageRepository;

    public UserImage findById(Long id){
        return imageRepository.findById(id).get();  //TODO Exception handler
    }

    public UserImage findByUser(User user){
        return imageRepository.findByUser(user);
    }

    public void save(UserImage image){
        imageRepository.save(image);
    }

}
