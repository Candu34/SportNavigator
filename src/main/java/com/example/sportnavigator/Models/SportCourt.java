package com.example.sportnavigator.Models;


import com.example.sportnavigator.DTO.EncodedImage;
import com.example.sportnavigator.Models.Enums.CourtType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "courts")
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
    private List<CourtImage> images = new ArrayList<>();

    @Column(name = "date_of_creating")
    private LocalDateTime dateOfCreated;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    private User user;

    @ElementCollection(targetClass = CourtType.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "court_type",
            joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<CourtType> courtTypes = new HashSet<>();


    @PrePersist
    private void init() {
        this.dateOfCreated = LocalDateTime.now();
    }

}
