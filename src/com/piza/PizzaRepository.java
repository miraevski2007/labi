package com.piza;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Репозиторий для управления коллекцией пицц.
 * Реализует паттерн Repository для отделения логики доступа к данным
 * от бизнес-логики.
 */
public class PizzaRepository {
    private List<Pizza> pizzas;
    
    /**
     * Конструктор по умолчанию.
     * Создает пустой репозиторий.
     */
    public PizzaRepository() {
        this.pizzas = new ArrayList<>();
    }
    
    /**
     * Конструктор с инициализацией.
     * @param pizzas начальная коллекция пицц
     */
    public PizzaRepository(List<Pizza> pizzas) {
        this.pizzas = new ArrayList<>(pizzas); // Защитное копирование
    }
    
    /**
     * Добавляет пиццу в коллекцию.
     * @param pizza пицца для добавления
     * @return true если добавление успешно
     * @throws IllegalArgumentException если пицца null
     */
    public boolean add(Pizza pizza) {
        if (pizza == null) {
            throw new IllegalArgumentException("Пицца не может быть null");
        }
        return pizzas.add(pizza);
    }
    
    /**
     * Удаляет пиццу из коллекции.
     * @param pizza пицца для удаления
     * @return true если удаление успешно
     */
    public boolean remove(Pizza pizza) {
        return pizzas.remove(pizza);
    }
    
    /**
     * Обновляет существующую пиццу.
     * @param oldPizza старая пицца (должна существовать в коллекции)
     * @param newPizza новая пицца
     * @return true если обновление успешно
     */
    public boolean update(Pizza oldPizza, Pizza newPizza) {
        int index = pizzas.indexOf(oldPizza);
        if (index != -1) {
            pizzas.set(index, newPizza);
            return true;
        }
        return false;
    }
    
    /**
     * Возвращает все пиццы в репозитории.
     * @return защищенная копия списка пицц
     */
    public List<Pizza> getAll() {
        return new ArrayList<>(pizzas); // Возвращаем копию для защиты от изменений
    }
    
    /**
     * Находит пиццу по предикату.
     * @param predicate условие поиска
     * @return Optional с найденной пиццей или empty
     */
    public Optional<Pizza> find(Predicate<Pizza> predicate) {
        return pizzas.stream()
                     .filter(predicate)
                     .findFirst();
    }
    
    /**
     * Находит все пиццы, удовлетворяющие предикату.
     * @param predicate условие поиска
     * @return список найденных пицц
     */
    public List<Pizza> findAll(Predicate<Pizza> predicate) {
        return pizzas.stream()
                     .filter(predicate)
                     .toList();
    }
    
    /**
     * Возвращает количество пицц в репозитории.
     * @return размер коллекции
     */
    public int size() {
        return pizzas.size();
    }
    
    /**
     * Проверяет, пуст ли репозиторий.
     * @return true если коллекция пуста
     */
    public boolean isEmpty() {
        return pizzas.isEmpty();
    }
    
    /**
     * Очищает репозиторий.
     */
    public void clear() {
        pizzas.clear();
    }
    
    @Override
    public String toString() {
        return String.format("PizzaRepository{количество=%d}", pizzas.size());
    }
}