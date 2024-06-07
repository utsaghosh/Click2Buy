package com.ecart.ecart.entity;

import com.ecart.ecart.dto.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column (length = 50, nullable = false)
    private String userName;

    @Column (length = 255, unique = true, nullable = false)
    private String email;

    @Column (length = 255, nullable = false)
    private String password;

    @Column (length = 255, nullable = false)
    private String address;

    private LocalDate dateOfBirth;

    @Transient
    private Long age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Ratings> ratingsList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Reviews> reviewsList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<CartItems> cartEntriesList = new ArrayList<>();


    public Long getAge() {

        return ChronoUnit.YEARS.between(dateOfBirth, LocalDate.now());
    }
}
