package com.ecart.ecart.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Items {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itemId;

    @Column (nullable = false)
    private Integer itemPrice;

    @Column (nullable = false)
    private String itemName;

    @Lob
    @Column (length = 16777215)
    private String itemDescription;

    @Lob
    @Column (length = 16777215)
    private String itemImage;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
    private List<Ratings> ratingsList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,  mappedBy = "item")
    private List<Reviews> reviewsList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,  mappedBy = "item")
    private List<CartItems> cartEntriesList = new ArrayList<>();
}
