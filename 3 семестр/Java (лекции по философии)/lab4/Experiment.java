package lab4;

import java.util.*;
import java.util.logging.Logger;

public class Experiment {
    private static final Logger LOGGER = LoggerUtil.getLogger();
    private Random random;
    
    private ArrayList<String> arrayListTrain;
    private LinkedList<String> linkedListTrain;
    
    public Experiment() {
        random = new Random();
        arrayListTrain = new ArrayList<>();
        linkedListTrain = new LinkedList<>();
    }
    
    private String createRandomCarriage() {
        String[] carriageTypes = {"Контейнер", "Цистерна", "Для сыпучих", "Авто", "Рефрижератор"};
        return carriageTypes[random.nextInt(5)] + "_" + random.nextInt(1000);
    }
    
    public void runArrayListTest(int size) {
        LOGGER.info("=== Начало автотеста ArrayList с размером: " + size + " ===");
        arrayListTrain.clear();
        
        String[] carriages = new String[size];
        for (int i = 0; i < size; i++) {
            carriages[i] = createRandomCarriage();
        }
        
        long addStartTime = System.nanoTime();
        for (int i = 0; i < size; i++) {
            arrayListTrain.add(carriages[i]);
        }
        long addTotalTime = System.nanoTime() - addStartTime;
        
        int removeCount = size / 10;
        long removeStartTime = System.nanoTime();
        for (int i = 0; i < removeCount; i++) {
            int index = random.nextInt(arrayListTrain.size());
            arrayListTrain.remove(index);
        }
        long removeTotalTime = System.nanoTime() - removeStartTime;
        
        LOGGER.info("=== Результаты автотеста ArrayList ===");
        LOGGER.info("Размер: " + size + ", Добавлено: " + size + ", Удалено: " + removeCount);
        LOGGER.info("Общее время добавления: " + addTotalTime/1000000 + " мс");
        LOGGER.info("Общее время удаления: " + removeTotalTime/1000000 + " мс");
        LOGGER.info("Среднее время добавления: " + (addTotalTime/size) + " нс");
        LOGGER.info("Среднее время удаления: " + (removeTotalTime/removeCount) + " нс");
        LOGGER.info("Финальный размер ArrayList: " + arrayListTrain.size());
    }
    
    public void runLinkedListTest(int size) {
        LOGGER.info("=== Начало автотеста LinkedList с размером: " + size + " ===");
        linkedListTrain.clear();
        
        String[] carriages = new String[size];
        for (int i = 0; i < size; i++) {
            carriages[i] = createRandomCarriage();
        }
        
        long addStartTime = System.nanoTime();
        for (int i = 0; i < size; i++) {
            linkedListTrain.add(carriages[i]);
        }
        long addTotalTime = System.nanoTime() - addStartTime;
        
        int removeCount = size / 10;
        long removeStartTime = System.nanoTime();
        for (int i = 0; i < removeCount; i++) {
            int index = random.nextInt(linkedListTrain.size());
            linkedListTrain.remove(index);
        }
        long removeTotalTime = System.nanoTime() - removeStartTime;
        
        LOGGER.info("=== Результаты автотеста LinkedList ===");
        LOGGER.info("Размер: " + size + ", Добавлено: " + size + ", Удалено: " + removeCount);
        LOGGER.info("Общее время добавления: " + addTotalTime/1000000 + " мс");
        LOGGER.info("Общее время удаления: " + removeTotalTime/1000000 + " мс");
        LOGGER.info("Среднее время добавления: " + (addTotalTime/size) + " нс");
        LOGGER.info("Среднее время удаления: " + (removeTotalTime/removeCount) + " нс");
        LOGGER.info("Финальный размер LinkedList: " + linkedListTrain.size());
    }
    
    public void runAccessTest(int size) {
        LOGGER.info("=== Начало теста доступа с размером: " + size + " ===");
        
        arrayListTrain.clear();
        linkedListTrain.clear();
        for (int i = 0; i < size; i++) {
            String carriage = createRandomCarriage();
            arrayListTrain.add(carriage);
            linkedListTrain.add(carriage);
        }
        
        long arrayListAccessTime = 0;
        for (int i = 0; i < 1000; i++) {
            int index = random.nextInt(size);
            long startOp = System.nanoTime();
            String element = arrayListTrain.get(index);
            arrayListAccessTime += System.nanoTime() - startOp;
        }
        
        long linkedListAccessTime = 0;
        for (int i = 0; i < 1000; i++) {
            int index = random.nextInt(size);
            long startOp = System.nanoTime();
            String element = linkedListTrain.get(index);
            linkedListAccessTime += System.nanoTime() - startOp;
        }
        
        LOGGER.info("=== Результаты теста доступа ===");
        LOGGER.info("ArrayList среднее время доступа: " + (arrayListAccessTime/1000) + " нс");
        LOGGER.info("LinkedList среднее время доступа: " + (linkedListAccessTime/1000) + " нс");
        LOGGER.info("Разница: " + ((linkedListAccessTime - arrayListAccessTime)/1000) + " нс");
    }
    
    public void runAllTests() {
        LOGGER.info("########################################");
        LOGGER.info("# ЗАПУСК АВТОТЕСТОВ ПРОИЗВОДИТЕЛЬНОСТИ #");
        LOGGER.info("########################################");
        
        int[] testSizes = {100, 1000, 10000};
        
        for (int size : testSizes) {
            runArrayListTest(size);
            runLinkedListTest(size);
            runAccessTest(size);
            LOGGER.info("---");
        }
        
        LOGGER.info("########################################");
        LOGGER.info("# АВТОТЕСТЫ ЗАВЕРШЕНЫ #");
        LOGGER.info("########################################");
    }
}