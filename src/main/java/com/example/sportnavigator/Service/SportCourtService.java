package com.example.sportnavigator.Service;


import com.example.sportnavigator.Models.SportCourt;
import com.example.sportnavigator.Models.User;
import com.example.sportnavigator.repository.SportCourtRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SportCourtService {
    private final SportCourtRepository sportCourtRepository;


    public List<SportCourt> findAll() {
        return sportCourtRepository.findAll();
    }

    public void save(SportCourt sportCourt) {
        sportCourtRepository.save(sportCourt);
    }

    public SportCourt fidById(Long id) {
        return sportCourtRepository.findById(id).orElse(null); //TODO Exception handler
    }
    public List<SportCourt> findByUser(User user){
        return sportCourtRepository.findByUser(user);
    }

}
