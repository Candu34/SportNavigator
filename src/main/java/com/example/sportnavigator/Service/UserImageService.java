package com.example.sportnavigator.Service;

import com.example.sportnavigator.Models.User;
import com.example.sportnavigator.Models.UserImage;
import com.example.sportnavigator.repository.UserImageRepository;
import com.example.sportnavigator.util.exceptions.ImageNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserImageService {
    private final UserImageRepository imageRepository;

    public UserImage findById(Long id) {
        Optional<UserImage> userImage = imageRepository.findById(id);
        if (userImage.isEmpty()) {
            throw new ImageNotFoundException("Image was not found");
        }

        return userImage.get();
    }

    public UserImage findByUser(User user) {
        return imageRepository.findByUser(user);
    }

    @Transactional()
    public void save(UserImage image) {
        imageRepository.save(image);
    }

    @Transactional()
    public void deleteImage(Long id){
        imageRepository.deleteById(id);
    }

}
