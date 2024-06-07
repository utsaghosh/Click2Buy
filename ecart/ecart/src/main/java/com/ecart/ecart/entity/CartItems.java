package com.ecart.ecart.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartItemId;

    @Column(nullable = false)
    private LocalDateTime creationDate;

    @Column(nullable = false)
    private Integer itemCount;

    @Column(nullable = false)
    private Integer itemPrice;

    @ManyToOne
    @JoinColumn(name="item_id", nullable = false)
    private Items item;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private Users user;
}
