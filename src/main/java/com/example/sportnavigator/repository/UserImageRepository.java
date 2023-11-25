package com.example.sportnavigator.repository;

import com.example.sportnavigator.Models.User;
import com.example.sportnavigator.Models.UserImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserImageRepository extends JpaRepository<UserImage, Long> {

    public UserImage findByUser(User user);
}
