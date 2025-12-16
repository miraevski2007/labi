package com.piza;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Представляет мясную пиццу с несколькими типами мяса.
 */
public class MeatPizza extends Pizza {
    private List<String> meatTypes;
    
    public MeatPizza() {
        super();
        this.meatTypes = new ArrayList<>();
    }
    
    public MeatPizza(double price, double weight, double diameter, 
                    int calories, List<String> meatTypes) {
        super(price, weight, diameter, calories);
        this.meatTypes = new ArrayList<>(meatTypes);
    }
    
    public List<String> getMeatTypes() { return new ArrayList<>(meatTypes); }
    public void setMeatTypes(List<String> meatTypes) { this.meatTypes = new ArrayList<>(meatTypes); }
    public void addMeatType(String meatType) { this.meatTypes.add(meatType); }
    
    @Override
    public String toString() {
        return String.format("MeatPizza{цена=%.2f, вес=%.1fг, диаметр=%.1fсм, " +
                           "калории=%d, видыМяса=%s}",
                getPrice(), getWeight(), getDiameter(), getCalories(), meatTypes);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!super.equals(obj)) return false;
        if (getClass() != obj.getClass()) return false;
        MeatPizza that = (MeatPizza) obj;
        return Objects.equals(meatTypes, that.meatTypes);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), meatTypes);
    }
}