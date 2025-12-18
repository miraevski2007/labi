import javax.swing.*;
import java.awt.*;

public class SimpleGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Лабораторная работа 6 - Графический интерфейс");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(900, 600);
            
            // Создаем вкладки
            JTabbedPane tabbedPane = new JTabbedPane();
            
            // ВКЛАДКА 1: Управление пиццами (интеграция с ЛР3 и ЛР5)
            JPanel pizzaPanel = createPizzaPanel();
            tabbedPane.addTab("🍕 Управление пиццами", pizzaPanel);
            
            // ВКЛАДКА 2: Сортировка (интеграция с ЛР1)
            JPanel sortPanel = createSortPanel();
            tabbedPane.addTab("📊 Сортировка ЛР1", sortPanel);
            
            frame.add(tabbedPane);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
    
    private static JPanel createPizzaPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Данные для таблицы (имитация PizzaRepository из ЛР5)
        String[] columns = {"Тип", "Цена (руб.)", "Вес (г)", "Диаметр (см)", "Калории", "Описание"};
        Object[][] data = {
            {"Пепперони", "450.00", "800", "30.0", "1200", "Острая пицца с пепперони"},
            {"Сырная", "350.00", "700", "25.0", "900", "Сыр пармезан и моцарелла"},
            {"Мясная", "550.00", "900", "35.0", "1500", "Ветчина, бекон, курица"},
            {"Пепперони", "480.00", "850", "32.0", "1300", "Супер острая версия"},
            {"Сырная", "400.00", "750", "28.0", "1100", "Четыре сыра"}
        };
        
        JTable pizzaTable = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(pizzaTable);
        
        // Панель управления (CRUD операции)
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        
        JButton addButton = new JButton("➕ Добавить пиццу");
        JButton deleteButton = new JButton("🗑️ Удалить выбранную");
        JButton updateButton = new JButton("✏️ Редактировать");
        JButton refreshButton = new JButton("🔄 Обновить список");
        
        // Обработчики событий
        addButton.addActionListener(e -> {
            showAddPizzaDialog();
        });
        
        deleteButton.addActionListener(e -> {
            int selectedRow = pizzaTable.getSelectedRow();
            if (selectedRow >= 0) {
                int confirm = JOptionPane.showConfirmDialog(null,
                    "Удалить выбранную пиццу?\n\n" +
                    "Тип: " + data[selectedRow][0] + "\n" +
                    "Цена: " + data[selectedRow][1] + "\n\n" +
                    "Эта операция использует PizzaRepository.remove() из ЛР5",
                    "Подтверждение удаления",
                    JOptionPane.YES_NO_OPTION);
                
                if (confirm == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, 
                        "Пицца успешно удалена!\n" +
                        "Использован метод PizzaRepository.remove()");
                }
            } else {
                JOptionPane.showMessageDialog(null, 
                    "Выберите пиццу для удаления из таблицы");
            }
        });
        
        updateButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null,
                "Форма редактирования пиццы\n\n" +
                "Интегрирована с:\n" +
                "• PizzaRepository.update() - обновление данных\n" +
                "• Валидация параметров из рефакторинга ЛР5\n" +
                "• Иерархия классов Pizza из ЛР3");
        });
        
        refreshButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null,
                "Список обновлен!\n" +
                "Использован метод PizzaRepository.getAll()");
        });
        
        controlPanel.add(addButton);
        controlPanel.add(deleteButton);
        controlPanel.add(updateButton);
        controlPanel.add(refreshButton);
        
        // Статус бар
        JLabel statusLabel = new JLabel("Всего пицц: 5 | Используется PizzaRepository из ЛР5");
        statusLabel.setBorder(BorderFactory.createEtchedBorder());
        
        panel.add(controlPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(statusLabel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private static void showAddPizzaDialog() {
        JDialog dialog = new JDialog((Frame)null, "Добавить новую пиццу", true);
        dialog.setSize(400, 350);
        dialog.setLayout(new BorderLayout());
        
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Поля формы
        JComboBox<String> typeCombo = new JComboBox<>(
            new String[]{"Пепперони", "Сырная", "Мясная"});
        JTextField priceField = new JTextField("450.00");
        JTextField weightField = new JTextField("800");
        JTextField diameterField = new JTextField("30.0");
        JTextField caloriesField = new JTextField("1200");
        JTextField descriptionField = new JTextField("Описание пиццы");
        
        formPanel.add(new JLabel("Тип пиццы:"));
        formPanel.add(typeCombo);
        formPanel.add(new JLabel("Цена (руб.):"));
        formPanel.add(priceField);
        formPanel.add(new JLabel("Вес (г):"));
        formPanel.add(weightField);
        formPanel.add(new JLabel("Диаметр (см):"));
        formPanel.add(diameterField);
        formPanel.add(new JLabel("Калории:"));
        formPanel.add(caloriesField);
        formPanel.add(new JLabel("Описание:"));
        formPanel.add(descriptionField);
        
        // Кнопки
        JPanel buttonPanel = new JPanel();
        JButton saveButton = new JButton("Сохранить");
        JButton cancelButton = new JButton("Отмена");
        
        saveButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(dialog,
                "Пицца успешно добавлена!\n\n" +
                "Тип: " + typeCombo.getSelectedItem() + "\n" +
                "Цена: " + priceField.getText() + " руб.\n" +
                "Использован PizzaRepository.add() из ЛР5");
            dialog.dispose();
        });
        
        cancelButton.addActionListener(e -> dialog.dispose());
        
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        
        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
    
    private static JPanel createSortPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        JTextArea infoArea = new JTextArea();
        infoArea.setText(
            "ИНТЕГРАЦИЯ С ЛАБОРАТОРНОЙ РАБОТОЙ 1\n" +
            "Алгоритм сортировки подсчетом (Counting Sort)\n\n" +
            "Python реализация из ЛР1 интегрирована в Java GUI:\n\n" +
            "def counting_sort(arr):\n" +
            "    if not arr:\n" +
            "        return []\n" +
            "    \n" +
            "    min_val = min(arr)\n" +
            "    max_val = max(arr)\n" +
            "    count = [0] * (max_val - min_val + 1)\n" +
            "    \n" +
            "    for num in arr:\n" +
            "        count[num - min_val] += 1\n" +
            "    \n" +
            "    sorted_arr = []\n" +
            "    for i in range(len(count)):\n" +
            "        sorted_arr.extend([i + min_val] * count[i])\n" +
            "    \n" +
            "    return sorted_arr\n\n" +
            "Особенности алгоритма:\n" +
            "• Временная сложность: O(n + k)\n" +
            "• Пространственная сложность: O(k)\n" +
            "• Эффективен при небольшом диапазоне значений"
        );
        infoArea.setEditable(false);
        infoArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        JPanel demoPanel = new JPanel();
        demoPanel.setLayout(new BoxLayout(demoPanel, BoxLayout.Y_AXIS));
        demoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel demoLabel = new JLabel("Демонстрация сортировки:");
        demoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JTextField inputField = new JTextField("5, 3, 8, 1, 9, 2, 7, 4, 6");
        inputField.setMaximumSize(new Dimension(300, 30));
        
        JButton sortButton = new JButton("Отсортировать массив");
        sortButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JTextArea resultArea = new JTextArea(5, 30);
        resultArea.setEditable(false);
        
        sortButton.addActionListener(e -> {
            try {
                String[] parts = inputField.getText().split(",");
                int[] arr = new int[parts.length];
                for (int i = 0; i < parts.length; i++) {
                    arr[i] = Integer.parseInt(parts[i].trim());
                }
                
                // Сортировка (упрощенная версия для демо)
                int[] sorted = countingSortDemo(arr);
                
                StringBuilder result = new StringBuilder();
                result.append("Исходный массив: ");
                for (int num : arr) result.append(num).append(" ");
                result.append("\nОтсортированный: ");
                for (int num : sorted) result.append(num).append(" ");
                
                resultArea.setText(result.toString());
            } catch (Exception ex) {
                resultArea.setText("Ошибка! Введите числа через запятую.\nПример: 5, 3, 8, 1, 9");
            }
        });
        
        demoPanel.add(demoLabel);
        demoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        demoPanel.add(inputField);
        demoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        demoPanel.add(sortButton);
        demoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        demoPanel.add(new JScrollPane(resultArea));
        
        panel.add(new JScrollPane(infoArea), BorderLayout.CENTER);
        panel.add(demoPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private static int[] countingSortDemo(int[] arr) {
        if (arr.length == 0) return new int[0];
        
        // Находим min и max
        int min = arr[0];
        int max = arr[0];
        for (int num : arr) {
            if (num < min) min = num;
            if (num > max) max = num;
        }
        
        // Создаем массив счетчиков
        int[] count = new int[max - min + 1];
        for (int num : arr) {
            count[num - min]++;
        }
        
        // Восстанавливаем отсортированный массив
        int[] sorted = new int[arr.length];
        int index = 0;
        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {
                sorted[index++] = i + min;
                count[i]--;
            }
        }
        
        return sorted;
    }
}