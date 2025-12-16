package com.piza;

import java.util.Objects;

/**
 * Представляет сырную пиццу с определенным типом сыра.
 */
public class CheesePizza extends Pizza {
    private String cheeseType;
    
    public CheesePizza() {
        super();
        this.cheeseType = "Mozzarella";
    }
    
    public CheesePizza(double price, double weight, double diameter, 
                      int calories, String cheeseType) {
        super(price, weight, diameter, calories);
        this.cheeseType = cheeseType;
    }
    
    public String getCheeseType() { return cheeseType; }
    public void setCheeseType(String cheeseType) { this.cheeseType = cheeseType; }
    
    @Override
    public String toString() {
        return String.format("CheesePizza{цена=%.2f, вес=%.1fг, диаметр=%.1fсм, " +
                           "калории=%d, типСыра='%s'}",
                getPrice(), getWeight(), getDiameter(), getCalories(), cheeseType);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!super.equals(obj)) return false;
        if (getClass() != obj.getClass()) return false;
        CheesePizza that = (CheesePizza) obj;
        return Objects.equals(cheeseType, that.cheeseType);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cheeseType);
    }
}