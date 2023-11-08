package com.example.sportnavigator.DTO;


import com.example.sportnavigator.Models.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SportCourtDTO {

    private String name;

    private String description;

    private List<Image> images;



}
