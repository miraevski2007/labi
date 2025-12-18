def input_massiv(letter):
    n = int(input("Введите кол-во элементов в массиве: "))
    s = []
    for i in range(n):
        element = int(input(f"Введите {letter}[{i}] = "))
        s.append(element)
    return s

def counting_sort(s):
    if not s:
        return []
    
    min_val = min(s)
    max_val = max(s)
    count_range = max_val - min_val + 1
    count = [0] * count_range
    
    for num in s:
        count[num - min_val] += 1
    
    sorted_arr = []
    for i in range(count_range):
        sorted_arr.extend([i + min_val] * count[i])
    
    return sorted_arr

if __name__ == "__main__":
    arr = input_massiv('A')
    print(f"\nИсходный массив: {arr}")
    sorted_arr = counting_sort(arr)
    print(f"После сортировки подсчетом: {sorted_arr}")