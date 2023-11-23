package com.example.sportnavigator.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "users_profile_pictures")
public class EncodedUserImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mime")
    private String mime;

    @Column(name = "data", nullable = false)
    private String data;

    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


}
