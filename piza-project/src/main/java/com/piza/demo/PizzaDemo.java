package com.piza.demo;

import com.piza.PepperoniPizza;
import com.piza.CheesePizza;
import com.piza.MeatPizza;
import com.piza.Pizza;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Демонстрационный класс для иерархии пицц.
 */
public final class PizzaDemo {
    
    private PizzaDemo() {}
    
    public static int countPizzasByWeight(List<Pizza> pizzas, double minWeight) {
        int count = 0;
        for (Pizza pizza : pizzas) {
            if (pizza.getWeight() > minWeight) {
                count++;
            }
        }
        return count;
    }
    
    public static void printPricesByDiameter(List<Pizza> pizzas, double minDiameter) {
        System.out.println("Пиццы с диаметром больше " + minDiameter + "см:");
        for (Pizza pizza : pizzas) {
            if (pizza.getDiameter() > minDiameter) {
                System.out.printf("- %s: %.2f денежных единиц%n", 
                    pizza.getClass().getSimpleName(), pizza.getPrice());
            }
        }
    }
    
    public static void main(String[] args) {
        List<Pizza> pizzas = createPizzaInstances();
        
        System.out.println("=== ВСЕ ПИЦЦЫ ===");
        for (Pizza pizza : pizzas) {
            System.out.println(pizza);
        }
        
        double weightThreshold = 500.0;
        int heavyPizzaCount = countPizzasByWeight(pizzas, weightThreshold);
        System.out.println("\n=== ПИЦЦЫ ТЯЖЕЛЕЕ " + weightThreshold + "г ===");
        System.out.println("Количество: " + heavyPizzaCount);
        
        double diameterThreshold = 30.0;
        System.out.println("\n==================================================");
        printPricesByDiameter(pizzas, diameterThreshold);
    }
    
    private static List<Pizza> createPizzaInstances() {
        List<Pizza> pizzas = new ArrayList<>();
        
        pizzas.add(new PepperoniPizza(12.50, 450.0, 28.0, 1200, false));
        pizzas.add(new PepperoniPizza(15.75, 520.0, 32.0, 1450, true));
        pizzas.add(new PepperoniPizza(18.90, 580.0, 35.0, 1680, false));
        
        pizzas.add(new CheesePizza(10.80, 420.0, 26.0, 980, "Моцарелла"));
        pizzas.add(new CheesePizza(13.40, 470.0, 29.0, 1150, "Чеддер"));
        pizzas.add(new CheesePizza(16.20, 510.0, 33.0, 1280, "Гауда"));
        
        pizzas.add(new MeatPizza(17.50, 560.0, 34.0, 1560, 
            Arrays.asList("Ветчина", "Бекон", "Колбаса")));
        pizzas.add(new MeatPizza(19.80, 620.0, 36.0, 1780, 
            Arrays.asList("Курица", "Говядина", "Пепперони")));
        pizzas.add(new MeatPizza(15.30, 530.0, 31.0, 1420, 
            Arrays.asList("Салями", "Прошутто")));
        
        return pizzas;
    }
}