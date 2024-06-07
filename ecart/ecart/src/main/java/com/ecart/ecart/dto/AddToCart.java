package com.ecart.ecart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddToCart {
    private Integer itemId;
    private Integer userId;
    private Integer count;
}
