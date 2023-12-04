package com.example.sportnavigator.Controllers;


import com.example.sportnavigator.DTO.SportCourtDTO;
import com.example.sportnavigator.DTO.UserDTO;
import com.example.sportnavigator.Models.SportCourt;
import com.example.sportnavigator.Service.SportCourtService;
import com.example.sportnavigator.mapper.MapStructMapper;
import com.example.sportnavigator.util.exceptions.CourtNotCreatedException;
import com.example.sportnavigator.util.exceptions.UserNotCreatedException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/courts")
public class SportCourtController {
    private final SportCourtService sportCourtService;
    private final MapStructMapper mapStructMapper;


    @GetMapping()
    public List<SportCourtDTO> findAll() {
        List<SportCourt> courts = sportCourtService.findAll();
        List<SportCourtDTO> courtsDTO = new ArrayList<>();
        for (SportCourt court : courts) {
            courtsDTO.add(mapStructMapper.SportCourtToSportCourtDTO(court));
        }
        return courtsDTO;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<HttpStatus> save(@RequestBody @Valid SportCourtDTO sportCourtDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();

            for (FieldError error : errors) {
                errorMsg.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append(";");
            }

            throw new CourtNotCreatedException(errorMsg.toString());
        }
        SportCourt sportCourt = mapStructMapper.SportCourtDTOToSportCourt(sportCourtDTO);
        sportCourtService.save(sportCourt);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public SportCourtDTO findOne(@PathVariable long id) {
        return mapStructMapper.SportCourtToSportCourtDTO(sportCourtService.fidById(id));
    }


}
