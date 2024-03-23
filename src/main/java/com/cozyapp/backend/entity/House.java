package com.cozyapp.backend.entity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "houses")

public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String description;
    private String coverImageUrl;
    private String price;
    private String lat;
    private String longi;
    private String streetNumber;
    private Long bedRooms;
    private Long bathRooms;
    private String liveLocation;
    private String availableStatus;
    private String typeOfHouse;
    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("house")
    private List<Picture> pictures;

    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("house")
    private List<Video> videos;
    @ElementCollection
    @CollectionTable(name = "house_features", joinColumns = @JoinColumn(name = "house_id"))
    @MapKeyColumn(name = "feature_name")
    @Column(name = "is_available")
    private Map<String, Boolean> features = new HashMap<>();
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private OurUsers user;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<WishlistItem> wishlistItems = new HashSet<>();

}
