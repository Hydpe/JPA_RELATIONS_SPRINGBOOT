package com.thinkrestdemo.learning.models;

import jakarta.persistence.*;

@Entity
@Table(name = "uni_users")
public class UniUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id", unique = true)
    private UniUserProfile profile;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UniUserProfile getProfile() {
        return profile;
    }

    public void setProfile(UniUserProfile profile) {
        this.profile = profile;
    }

    // constructors, getters, setters
}
