package com.ecart.ecart.dto;

import com.ecart.ecart.entity.CartItems;
import com.ecart.ecart.entity.Ratings;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemModel {

    private Integer cartItemId;
    private LocalDateTime creationDate;

    private Integer itemCount;
    private Integer itemId;
    private String itemName;
    private String itemImage;
    private Integer itemPrice;
    private Float averageRating;
    private Integer userId;

    public CartItemModel (CartItems cartItem){

        this.setCartItemId(cartItem.getCartItemId());
        this.setItemId(cartItem.getItem().getItemId());
        this.setUserId(cartItem.getUser().getUserId());
        this.setItemName(cartItem.getItem().getItemName());
        this.setItemImage(cartItem.getItem().getItemImage());
        this.setItemPrice(cartItem.getItem().getItemPrice());
        this.setCreationDate(cartItem.getCreationDate());
        this.setItemCount(cartItem.getItemCount());
        Float averageRating = (float) cartItem.getItem().getRatingsList().stream().mapToDouble(Ratings::getScore).average().orElse(0.0);
        this.setAverageRating(averageRating);
    }

    public static List<CartItemModel> parseModelListFromEntityList(List<CartItems> cartItems){

        List<CartItemModel> cartItemModels = new ArrayList<CartItemModel>();

        for (CartItems cartEntity : cartItems){
            cartItemModels.add(new CartItemModel(cartEntity));
        }

        return  cartItemModels;
    }
}
