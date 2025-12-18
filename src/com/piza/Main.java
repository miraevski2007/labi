package com.piza;

import java.util.Arrays;
import java.util.List;

/**
 * Демонстрационный класс для тестирования репозитория пицц.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Демонстрация работы PizzaRepository ===\n");
        
        // 1. Создаем репозиторий
        PizzaRepository repository = new PizzaRepository();
        System.out.println("1. Создан пустой репозиторий: " + repository);
        
        // 2. Создаем различные пиццы
        Pizza pepperoni = new PepperoniPizza(450.0, 800.0, 30.0, 1200, true);
        Pizza cheese = new CheesePizza(350.0, 700.0, 25.0, 900, "Пармезан");
        Pizza meat = new MeatPizza(550.0, 900.0, 35.0, 1500, 
                                 Arrays.asList("Ветчина", "Бекон", "Курица"));
        
        // 3. Добавляем пиццы в репозиторий
        System.out.println("\n2. Добавление пицц:");
        repository.add(pepperoni);
        repository.add(cheese);
        repository.add(meat);
        System.out.println("   Добавлено пицц: " + repository.size());
        
        // 4. Показываем все пиццы
        System.out.println("\n3. Все пиццы в репозитории:");
        List<Pizza> allPizzas = repository.getAll();
        allPizzas.forEach(pizza -> System.out.println("   - " + pizza));
        
        // 5. Поиск пицц по критериям
        System.out.println("\n4. Поиск пицц:");
        
        System.out.println("   а) Пиццы дороже 400 рублей:");
        repository.findAll(p -> p.getPrice() > 400)
                  .forEach(p -> System.out.println("      - " + p.getSpecificDescription() + 
                                                   ", цена: " + p.getPrice()));
        
        System.out.println("\n   б) Самые калорийные пиццы (>1000 калорий):");
        repository.findAll(p -> p.getCalories() > 1000)
                  .forEach(p -> System.out.println("      - " + p.getSpecificDescription() + 
                                                   ", калории: " + p.getCalories()));
        
        // 6. Обновление пиццы
        System.out.println("\n5. Обновление пиццы:");
        Pizza newCheese = new CheesePizza(380.0, 750.0, 25.0, 950, "Гауда");
        if (repository.update(cheese, newCheese)) {
            System.out.println("   Пицца успешно обновлена!");
        }
        
        // 7. Удаление пиццы
        System.out.println("\n6. Удаление пиццы:");
        if (repository.remove(meat)) {
            System.out.println("   Мясная пицца удалена!");
        }
        
        // 8. Финальное состояние репозитория
        System.out.println("\n7. Финальное состояние репозитория:");
        System.out.println("   " + repository);
        System.out.println("   Оставшиеся пиццы:");
        repository.getAll().forEach(p -> System.out.println("   - " + p.getSpecificDescription()));
        
        System.out.println("\n=== Демонстрация завершена ===");
    }
}