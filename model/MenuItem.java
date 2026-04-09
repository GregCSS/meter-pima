// MenuItem is abstract because there won't ever be a plain MenuItem object,
// only Food and Drink objects
package model;

import java.util.List;

public abstract class MenuItem {
    private int itemId;
    private String name;
    private float price;

    private List<Ingredient> ingredients;

    public abstract String getDetails();

    public MenuItem(int itemId, String name, float price, List<Ingredient> ingredients) {
        this.itemId = itemId;
        this.name   = name;
        this.price  = price;
    }

    // Getters
    public int getItemId()  { return this.itemId; }
    public String getName() { return this.name; }
    public float getPrice() { return this.price; }

    public String getType() {
        return this.getClass().getName();
    }

    // Setters
    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name; 
        }
    }

    public void setPrice(float price) { 
        if (price > 0) {
            this.price = price; 
        }
    }

    public Boolean isMakable() {
        // Iterates over each ingredients and see if there is enough of it in stock
        return false;
    }
    
}