package com.piza;

import java.util.Objects;

/**
 * Представляет пиццу Пепперони с опцией остроты.
 */
public class PepperoniPizza extends Pizza {
    private boolean isSpicy;
    
    public PepperoniPizza() {
        super();
        this.isSpicy = false;
    }
    
    public PepperoniPizza(double price, double weight, double diameter, 
                         int calories, boolean isSpicy) {
        super(price, weight, diameter, calories);
        this.isSpicy = isSpicy;
    }
    
    public boolean isSpicy() { return isSpicy; }
    public void setSpicy(boolean isSpicy) { this.isSpicy = isSpicy; }
    
    @Override
    public String toString() {
        return String.format("PepperoniPizza{цена=%.2f, вес=%.1fг, диаметр=%.1fсм, " +
                           "калории=%d, острая=%b}",
                getPrice(), getWeight(), getDiameter(), getCalories(), isSpicy);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!super.equals(obj)) return false;
        if (getClass() != obj.getClass()) return false;
        PepperoniPizza that = (PepperoniPizza) obj;
        return isSpicy == that.isSpicy;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isSpicy);
    }
}