package com.piza.demo;

import com.piza.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Демонстрационный класс для иерархии пицц.
 * Рефакторинг: добавлена работа с PizzaRepository.
 */
public final class PizzaDemo {
    
    private PizzaDemo() {} // Утилитный класс
    
    /**
     * Подсчитывает количество пицц тяжелее заданного веса.
     * Рефакторинг: теперь использует репозиторий.
     */
    public static int countPizzasByWeight(PizzaRepository repository, double minWeight) {
        return (int) repository.getAll().stream()
                .filter(pizza -> pizza.getWeight() > minWeight)
                .count();
    }
    
    /**
     * Выводит цены пицц с диаметром больше заданного.
     * Рефакторинг: использует репозиторий и форматированный вывод.
     */
    public static void printPricesByDiameter(PizzaRepository repository, double minDiameter) {
        System.out.println("Пиццы с диаметром больше " + minDiameter + " см:");
        System.out.println("----------------------------------------");
        
        repository.findAll(pizza -> pizza.getDiameter() > minDiameter)
                  .forEach(pizza -> System.out.printf(
                      "• %-20s | Цена: %6.2f | Диаметр: %4.1f см%n",
                      pizza.getSpecificDescription(),
                      pizza.getPrice(),
                      pizza.getDiameter()
                  ));
        
        if (repository.findAll(pizza -> pizza.getDiameter() > minDiameter).isEmpty()) {
            System.out.println("(нет пицц, удовлетворяющих условию)");
        }
    }
    
    /**
     * Находит пиццу с лучшим соотношением цена/калории.
     * Рефакторинг: новый метод, демонстрирующий бизнес-логику.
     */
    public static void findBestValuePizza(PizzaRepository repository) {
        repository.getAll().stream()
                  .min((p1, p2) -> Double.compare(
                      p1.getPrice() / p1.getCalories(),
                      p2.getPrice() / p2.getCalories()
                  ))
                  .ifPresentOrElse(
                      pizza -> System.out.printf(
                          "Лучшее соотношение цена/калории: %s (%.4f за калорию)%n",
                          pizza.getSpecificDescription(),
                          pizza.getPrice() / pizza.getCalories()
                      ),
                      () -> System.out.println("Нет пицц для сравнения")
                  );
    }
    
    /**
     * Группирует пиццы по типам.
     * Рефакторинг: демонстрация работы с разными типами пицц.
     */
    public static void groupPizzasByType(PizzaRepository repository) {
        System.out.println("\n=== Группировка пицц по типам ===");
        
        long pepperoniCount = repository.getAll().stream()
                .filter(p -> p instanceof PepperoniPizza)
                .count();
        long cheeseCount = repository.getAll().stream()
                .filter(p -> p instanceof CheesePizza)
                .count();
        long meatCount = repository.getAll().stream()
                .filter(p -> p instanceof MeatPizza)
                .count();
        
        System.out.printf("Пепперони: %d шт.%n", pepperoniCount);
        System.out.printf("Сырные: %d шт.%n", cheeseCount);
        System.out.printf("Мясные: %d шт.%n", meatCount);
    }
    
    public static void main(String[] args) {
        System.out.println("=== ДЕМОНСТРАЦИЯ РАБОТЫ С ПИЦЦАМИ ===\n");
        
        // Создаем репозиторий и заполняем его
        PizzaRepository repository = createAndPopulateRepository();
        
        System.out.println("1. ИНИЦИАЛИЗАЦИЯ РЕПОЗИТОРИЯ");
        System.out.println("   Создано пицц: " + repository.size());
        
        System.out.println("\n2. ВСЕ ПИЦЦЫ В РЕПОЗИТОРИИ");
        System.out.println("----------------------------------------");
        repository.getAll().forEach(pizza -> {
            System.out.printf("• %s%n", pizza);
            System.out.printf("  Цена за 100г: %.2f | Калории на 100г: %.1f%n",
                pizza.getPricePer100g(), pizza.getCaloriesPer100g());
        });
        
        System.out.println("\n3. СТАТИСТИКА");
        double weightThreshold = 500.0;
        int heavyPizzaCount = countPizzasByWeight(repository, weightThreshold);
        System.out.printf("   Пицц тяжелее %.1fг: %d шт.%n", weightThreshold, heavyPizzaCount);
        
        double diameterThreshold = 30.0;
        System.out.println("\n4. ПИЦЦЫ ПО ДИАМЕТРУ");
        printPricesByDiameter(repository, diameterThreshold);
        
        System.out.println("\n5. АНАЛИЗ ЦЕННОСТИ");
        findBestValuePizza(repository);
        
        System.out.println("\n6. ГРУППИРОВКА");
        groupPizzasByType(repository);
        
        // Демонстрация CRUD операций
        System.out.println("\n7. ДЕМОНСТРАЦИЯ CRUD ОПЕРАЦИЙ");
        demonstrateCrudOperations(repository);
        
        System.out.println("\n=== ДЕМОНСТРАЦИЯ ЗАВЕРШЕНА ===");
    }
    
    /**
     * Создает и заполняет репозиторий тестовыми данными.
     */
    private static PizzaRepository createAndPopulateRepository() {
        PizzaRepository repository = new PizzaRepository();
        
        // Добавляем пиццы с улучшенными параметрами
        repository.add(new PepperoniPizza(12.50, 450.0, 28.0, 1200, false));
        repository.add(new PepperoniPizza(15.75, 520.0, 32.0, 1450, true));
        repository.add(new PepperoniPizza(18.90, 580.0, 35.0, 1680, false));
        
        repository.add(new CheesePizza(10.80, 420.0, 26.0, 980, "Моцарелла"));
        repository.add(new CheesePizza(13.40, 470.0, 29.0, 1150, "Чеддер"));
        repository.add(new CheesePizza(16.20, 510.0, 33.0, 1280, "Гауда"));
        
        repository.add(new MeatPizza(17.50, 560.0, 34.0, 1560, 
            Arrays.asList("Ветчина", "Бекон", "Колбаса")));
        repository.add(new MeatPizza(19.80, 620.0, 36.0, 1780, 
            Arrays.asList("Курица", "Говядина", "Пепперони")));
        repository.add(new MeatPizza(15.30, 530.0, 31.0, 1420, 
            Arrays.asList("Салями", "Прошутто")));
        
        return repository;
    }
    
    /**
     * Демонстрирует CRUD операции с репозиторием.
     */
    private static void demonstrateCrudOperations(PizzaRepository repository) {
        System.out.println("Исходное количество: " + repository.size());
        
        // Добавление новой пиццы
        Pizza newPizza = new PepperoniPizza(14.50, 490.0, 30.0, 1350, true);
        if (repository.add(newPizza)) {
            System.out.println("✓ Добавлена новая пицца");
        }
        
        // Поиск и обновление
        repository.find(p -> p.getPrice() < 12.0)
                  .ifPresent(cheapPizza -> {
                      Pizza updatedPizza = new CheesePizza(
                          12.50, cheapPizza.getWeight(), 
                          cheapPizza.getDiameter(), 1100, "Дорогой сыр");
                      if (repository.update(cheapPizza, updatedPizza)) {
                          System.out.println("✓ Обновлена дешевая пицца");
                      }
                  });
        
        // Удаление
        repository.find(p -> p.getCalories() > 1700)
                  .ifPresent(highCaloriePizza -> {
                      if (repository.remove(highCaloriePizza)) {
                          System.out.println("✓ Удалена высококалорийная пицца");
                      }
                  });
        
        System.out.println("Финальное количество: " + repository.size());
    }
}