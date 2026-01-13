package lab5;

import java.util.*;
import java.util.logging.Logger;

import lab5.Train.Pair;

public class Experiment {

    private static final Logger LOGGER = LoggerUtil.getLogger();
    private Random random;

    private ArrayList<Train.Pair> arrayListTrain;
    private LinkedList<Train.Pair> linkedListTrain;

    public Map<Integer, Long> arrayAddAvg = new LinkedHashMap<>();
    public Map<Integer, Long> arrayRemoveAvg = new LinkedHashMap<>();

    public Map<Integer, Long> listAddAvg = new LinkedHashMap<>();
    public Map<Integer, Long> listRemoveAvg = new LinkedHashMap<>();

    public Experiment() {
        random = new Random();
        arrayListTrain = new ArrayList<Train.Pair>();
        linkedListTrain = new LinkedList<Train.Pair>();
    }


    public void add_and_remove_test(int size, Collection<Pair> datastructure, String datastructure_name) {
    	
        datastructure.clear();
        
        Train train = new Train();
        Train.Pair[] wagons = new Train.Pair[size];
        
        for (int i = 0; i < size; i++) {
        	wagons[i] =  train.new Pair(random.nextInt(5) + 1);
        }

        long addStartTime = System.nanoTime();
        for (int i = 0; i < size; i++) {
            datastructure.add(wagons[i]);
        }
        long addTotalTime = System.nanoTime() - addStartTime;
        
        
        int removeCount = size / 10;

        long removeStartTime = System.nanoTime();
        for (int i = 0; i < removeCount; i++) {
            int index = random.nextInt(size - i);
            datastructure.remove(index);
        }
        long removeTotalTime = System.nanoTime() - removeStartTime;

        LOGGER.info("Добавлено:                " + size);
        LOGGER.info("Общее время добавления:   " + addTotalTime + " нс");
        LOGGER.info("Среднее время добавления: " + (addTotalTime / size) + " нс");
        LOGGER.info("Удалено:                  " + removeCount);
        LOGGER.info("Общее время удаления:     " + removeTotalTime + " нс");
        LOGGER.info("Среднее время удаления:   " + (removeTotalTime / removeCount) + " нс");
        LOGGER.info("\n");
        
        if (datastructure_name.equals("ArrayList")) {
        	arrayAddAvg.put(size, addTotalTime / size);
            arrayRemoveAvg.put(size, removeTotalTime / removeCount);
        }
        else {
        	listAddAvg.put(size, addTotalTime / size);
	        listRemoveAvg.put(size, removeTotalTime / removeCount);
        }
    }

    
    public void runAllTests() {
        LOGGER.info("###############################");
        LOGGER.info("#   ЗАПУСК ВРЕМЕННЫХ ТЕСТОВ   #");
        LOGGER.info("###############################\n");

        int[] sizes = {10, 100, 1000, 10000, 100000};
        
        
        // прогрев
        LOGGER.setLevel(java.util.logging.Level.OFF);
        for (int size : sizes) {
        	add_and_remove_test(size, arrayListTrain,  "ArrayList");
        	add_and_remove_test(size, linkedListTrain, "LinkedList");
        }
        LOGGER.setLevel(java.util.logging.Level.ALL);
        
        
        
        
        LOGGER.info("========== ArrayList ==========");
        for (int size : sizes) {
        	add_and_remove_test(size, arrayListTrain,  "ArrayList");
        }
        
        LOGGER.info("========== LinkedList ==========");
        for (int size : sizes) {
        	add_and_remove_test(size, linkedListTrain, "LinkedList");
        }
        
        LOGGER.info("################################");
        LOGGER.info("#  ОКОНЧАНИЕ ВРЕМЕННЫХ ТЕСТОВ  #");
        LOGGER.info("################################");


        // Запускаем окно графиков Java2D
        new GraphWindow(arrayAddAvg, listAddAvg, arrayRemoveAvg, listRemoveAvg);
    }
	
	
}
