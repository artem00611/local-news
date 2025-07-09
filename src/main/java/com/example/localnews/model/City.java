package com.example.localnews.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cities")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String stateName;

    private String stateCode;

    private Integer newsCount = 0;

    @OneToMany(mappedBy = "matchedCity", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<News> newsList = new ArrayList<>();
}
