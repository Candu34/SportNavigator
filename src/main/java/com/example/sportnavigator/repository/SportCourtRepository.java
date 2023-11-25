package com.example.sportnavigator.repository;

import com.example.sportnavigator.Models.SportCourt;
import com.example.sportnavigator.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SportCourtRepository extends JpaRepository<SportCourt, Long> {

    public List<SportCourt> findByUser(User user);
}
