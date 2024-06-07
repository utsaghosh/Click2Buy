package com.ecart.ecart.dto;

import com.ecart.ecart.entity.Items;
import com.ecart.ecart.entity.Ratings;
import com.ecart.ecart.exception.InvalidInputException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemModel {
    private Integer itemId;
    private Integer itemPrice;
    private String itemName;
    private String itemDescription;
    private String itemImage;
    private Float averageRating;

    public ItemModel (Items item){

        this.setItemId(item.getItemId());
        this.setItemName(item.getItemName());
        this.setItemDescription(item.getItemDescription());
        this.setItemPrice(item.getItemPrice());
        this.setItemImage((item.getItemImage()));
        Float averageRating = (float) item.getRatingsList().stream().mapToDouble(Ratings::getScore).average().orElse(0.0);
        this.setAverageRating(averageRating);
    }

    public static List<ItemModel> parseModelListFromEntityList(List<Items> items){

        List<ItemModel> itemModelList = new ArrayList<ItemModel>();

        for (Items itemEntity : items){
            itemModelList.add(new ItemModel(itemEntity));
        }

        return  itemModelList;
    }

    public Items parseEntityFromModel() throws InvalidInputException {
        if(! this.isModelValid()){
            return null;
        }
        Items item = new Items();
        item.setItemName(this.getItemName());
        item.setItemDescription(this.getItemDescription());
        item.setItemPrice(this.getItemPrice());
        item.setItemImage((this.getItemImage()));

        return item;
    }

    public Boolean isModelValid() throws InvalidInputException {

        if(!this.validateItemPrice(this.getItemPrice())){
            throw new InvalidInputException();
        }
        if(!this.validateItemName(this.getItemName())){
            throw new InvalidInputException();
        }
        if(!this.validateItemDescription(this.getItemDescription())){
            throw new InvalidInputException();
        }
        if(!this.validateItemImage(this.getItemImage())){
            throw new InvalidInputException();
        }
        return true;
    }

    public Boolean validateItemPrice(int itemPrice){
        return true;
    }
    public Boolean validateItemName(String itemName){
        return true;
    }
    public Boolean validateItemDescription(String itemDescription){
        return true;
    }
    public Boolean validateItemImage(String itemImage){
        return true;
    }
}
