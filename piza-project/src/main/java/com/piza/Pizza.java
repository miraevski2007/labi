package com.piza;

import java.util.Objects;

/**
 *  базовый класс, представляющий общую пиццу.
 */
public abstract class Pizza {
    private double price;
    private double weight;
    private double diameter;
    private int calories;
    
    public Pizza() {
        this.price = 0.0;
        this.weight = 0.0;
        this.diameter = 0.0;
        this.calories = 0;
    }
    
    public Pizza(double price, double weight, double diameter, int calories) {
        this.price = price;
        this.weight = weight;
        this.diameter = diameter;
        this.calories = calories;
    }
    
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    
    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }
    
    public double getDiameter() { return diameter; }
    public void setDiameter(double diameter) { this.diameter = diameter; }
    
    public int getCalories() { return calories; }
    public void setCalories(int calories) { this.calories = calories; }
    
    @Override
    public String toString() {
        return String.format("Pizza{цена=%.2f, вес=%.1fг, диаметр=%.1fсм, калории=%d}",
                price, weight, diameter, calories);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Pizza pizza = (Pizza) obj;
        return Double.compare(pizza.price, price) == 0 &&
               Double.compare(pizza.weight, weight) == 0 &&
               Double.compare(pizza.diameter, diameter) == 0 &&
               calories == pizza.calories;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(price, weight, diameter, calories);
    }
}