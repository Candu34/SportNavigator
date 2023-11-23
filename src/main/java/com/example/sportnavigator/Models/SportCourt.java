package com.example.sportnavigator.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "court")
@Entity
public class SportCourt {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "sportCourt")
    private List<EncodedImage> images = new ArrayList<>();

    @Column(name = "preview_image_id")
    private Long previewImageId;

    @Column(name = "date_of_creating")
    private LocalDateTime dateOfCreated;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    private User user;

    @Column(name = "court_type")
    private String courtType;

    @PrePersist
    private void init(){
        this.dateOfCreated = LocalDateTime.now();
    }

}
