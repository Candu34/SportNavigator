package com.example.sportnavigator.Service;


import com.example.sportnavigator.Models.SportCourt;
import com.example.sportnavigator.Models.User;
import com.example.sportnavigator.repository.SportCourtRepository;
import com.example.sportnavigator.util.exceptions.SportCourtNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<SportCourt> sportCourt = sportCourtRepository.findById(id);
        if (sportCourt.isEmpty()){
            throw new SportCourtNotFoundException("Court was not found");
        }

        return sportCourt.get();
    }

    public List<SportCourt> findByUser(User user){
        return sportCourtRepository.findByUser(user);
    }

}
