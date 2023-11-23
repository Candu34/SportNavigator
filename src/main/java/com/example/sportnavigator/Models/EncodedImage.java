package com.example.sportnavigator.Models;


import jakarta.persistence.*;

@Entity
@Table(name = "court_images")
public class EncodedImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mime")
    private String mime;
    @Column(name = "data", nullable = false)
    private String data;

    @ManyToOne
    private SportCourt sportCourt;


}
