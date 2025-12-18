def input_array(array_name: str) -> list[int]:
    """
    Запрашивает у пользователя массив целых чисел.
    Рефакторинг: добавлена валидация и обработка исключений.
    """
    while True:
        try:
            n = int(input(f"Введите количество элементов в массиве '{array_name}': "))
            if n <= 0:
                print("Количество элементов должно быть положительным числом.")
                continue
                
            array = []
            for i in range(n):
                while True:
                    try:
                        element = int(input(f"Введите {array_name}[{i}] = "))
                        array.append(element)
                        break
                    except ValueError:
                        print("Ошибка! Введите целое число.")
            return array
            
        except ValueError:
            print("Ошибка! Введите целое число для количества элементов.")

def counting_sort(arr: list[int]) -> list[int]:
    """
    Сортирует массив целых чисел методом подсчета.
    Рефакторинг: добавлены проверки и комментарии.
    """
    if not arr:
        return []
    
    if len(arr) == 1:
        return arr.copy()
    
    try:
        min_val = min(arr)
        max_val = max(arr)
        
        # Проверка на потенциально большой диапазон
        range_size = max_val - min_val + 1
        if range_size > 1000000:
            print("Предупреждение: Диапазон значений очень большой.")
            print("Метод подсчета может быть неэффективным.")
        
        # Создаем массив для подсчета частот
        count = [0] * range_size
        
        # Подсчет частот каждого элемента
        for num in arr:
            count[num - min_val] += 1
        
        # Восстановление отсортированного массива
        sorted_arr = []
        for i in range(range_size):
            # Добавляем элемент i+min_val count[i] раз
            sorted_arr.extend([i + min_val] * count[i])
            
        return sorted_arr
        
    except (ValueError, TypeError) as e:
        print(f"Ошибка при сортировке: {e}")
        return arr.copy()

def main():
    """Основная функция программы."""
    print("=== Программа сортировки массива методом подсчета ===")
    
    # Ввод массива с улучшенной функцией
    array = input_array('A')
    print(f"\nИсходный массив: {array}")
    
    # Сортировка
    sorted_array = counting_sort(array)
    print(f"Отсортированный массив: {sorted_array}")
    
    # Демонстрация обработки крайних случаев
    print("\n=== Тест с пустым массивом ===")
    empty_array = []
    sorted_empty = counting_sort(empty_array)
    print(f"Пустой массив: {empty_array}")
    print(f"После сортировки: {sorted_empty}")

if __name__ == "__main__":
    try:
        main()
    except KeyboardInterrupt:
        print("\nПрограмма прервана пользователем.")
    except Exception as e:
        print(f"Неожиданная ошибка: {e}")