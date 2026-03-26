package lab8;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    public Train train;
    
    private JList<String> trainList; 
    private JLabel wagonCountLabel;
    private JLabel totalTonnageLabel;
    
    public MyFrame() {
        train = new Train();
        initUI();
    }
    
    private void initUI() {
        setTitle("Управление составом");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setSize(900, 400);
        setLocationRelativeTo(null);
        
        // панель информации с кол-вом вагонов и тоннажем
        JPanel infoPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        infoPanel.setBorder(BorderFactory.createTitledBorder("Информация"));
        
        wagonCountLabel = new JLabel("Вагонов: 0/60", SwingConstants.CENTER);
        totalTonnageLabel = new JLabel("Грузоподъемность: 0 тонн", SwingConstants.CENTER);
        
        infoPanel.add(wagonCountLabel);
        infoPanel.add(totalTonnageLabel);
        
        // панель управления 
        JPanel controlPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        controlPanel.setBorder(BorderFactory.createTitledBorder("Управление"));
        
        JButton addButton = new JButton("Добавить вагон");
        addButton.addActionListener(e -> showAddDialog());
        
        JButton editButton = new JButton("Изменить вагон");
        editButton.addActionListener(e -> editSelectedWagon());
        
        JButton clearButton = new JButton("Очистить состав");
        clearButton.addActionListener(e -> clearTrain());
        
        // НОВАЯ КНОПКА для сетевого обмена
        JButton networkButton = new JButton("Сетевой обмен");
        networkButton.addActionListener(e -> openNetworkManager());
        
        // кнопка для тестовых данных
        JButton testButton = new JButton("Добавить тестовые данные");
        testButton.addActionListener(e -> addTestData());
        
        controlPanel.add(addButton);
        controlPanel.add(editButton);   
        controlPanel.add(clearButton);
        controlPanel.add(networkButton);
        controlPanel.add(testButton);
        
        // отображение вагонов поезда
        trainList = new JList<>(new String[60]);
        trainList.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(trainList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Состав поезда"));
        
        // помещение инфы и кнопок слева
        JPanel leftPanel = new JPanel(new BorderLayout(10, 10));
        leftPanel.add(infoPanel, BorderLayout.NORTH);
        leftPanel.add(controlPanel, BorderLayout.CENTER);
        
        add(leftPanel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);
        
        setVisible(true);
        updateTrainList();
    }
    
    private void openNetworkManager() {
        NetworkManager networkManager = new NetworkManager(this);
        networkManager.setVisible(true);
    }
    
    private void addTestData() {
        if (train.last_wagon < train.railcar_limit - 5) {
            train.addRailcar(1); // Контейнер
            train.addRailcar(2); // Жидкости
            train.addRailcar(3); // Сыпучие
            train.addRailcar(4); // Автомобили
            train.addRailcar(5); // Замороженные
            updateTrainList();
            JOptionPane.showMessageDialog(this, "Добавлено 5 тестовых вагонов");
        }
    }
    

    private int WagonDiolog(String title) {
        String railcarType = (String) JOptionPane.showInputDialog(
            this,
            "Выберите тип вагона:",
            title,
            JOptionPane.QUESTION_MESSAGE,
            null,
            Railcar.vagon_types,
            Railcar.vagon_types[0]
        );
        
        if (railcarType == null) return -1;
        
        String goodsType = (String) JOptionPane.showInputDialog(
            this,
            "Выберите тип груза:",
            title,
            JOptionPane.QUESTION_MESSAGE,
            null,
            Storage.goods_types,
            Storage.goods_types[0]
        );
        
        if (goodsType == null) return -1;
        
        int railcarIndex = -1;
        int goodsIndex = -1;
        
        for (int i = 0; i < Railcar.vagon_types.length; i++) {
            if (Railcar.vagon_types[i].equals(railcarType)) railcarIndex = i + 1;
            if (Storage.goods_types[i].equals(goodsType)) goodsIndex = i + 1;
        }
        
        if (railcarIndex != goodsIndex) {
            JOptionPane.showMessageDialog(this, 
                "Тип вагона не соответствует типу товара!\nВыберите соответствующие типы.",
                "Ошибка", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
        
        return railcarIndex;
    }
    
    private void showAddDialog() {
        addWagon(WagonDiolog("Добавление вагона"));
    }
    
    private void editSelectedWagon() {
        int selectedIndex = trainList.getSelectedIndex();
        
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, 
                "Выберите вагон для редактирования!", 
                "Ошибка", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        editWagon(selectedIndex, WagonDiolog("Изменение вагона"));
    }
    
    private void addWagon(int type) {
        if (train.last_wagon >= train.railcar_limit) {
            JOptionPane.showMessageDialog(this, 
                "Лимит: " + train.railcar_limit + " вагонов", 
                "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        train.addRailcar(type);
        updateTrainList();
    }
    
    private void editWagon(int index, int newType) {
        Railcar.RailcarParent railcar_instance;
        Storage.GoodsParent goods_instance;

        switch(newType) {
            case 1:
                railcar_instance = train.railcar.new Container();
                goods_instance = train.storage.new Container();
                break;
            case 2:
                railcar_instance = train.railcar.new Cistern();
                goods_instance = train.storage.new Fluid();
                break;
            case 3:
                railcar_instance = train.railcar.new Friable();
                goods_instance = train.storage.new Friable();
                break;
            case 4:
                railcar_instance = train.railcar.new Auto();
                goods_instance = train.storage.new Auto();
                break;
            case 5:
                railcar_instance = train.railcar.new Refrigerator();
                goods_instance = train.storage.new Frozen();
                break;
            default: 
                return;
        }
        
        train.train[index] = train.new Pair(railcar_instance, goods_instance, index+1);
        updateTrainList();
        
        JOptionPane.showMessageDialog(this, 
            "Вагон " + (index + 1) + " успешно изменен!", 
            "Успех", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void updateTrainList() {
        String[] wagons = new String[train.last_wagon];
        int totalWeight = 0;
        
        for (int i = 0; i < train.last_wagon; i++) {
            Train.Pair wagon = train.train[i];
            if (wagon != null) {
                String info = wagon.toString();
                wagons[i] = info;
                totalWeight += wagon.railcar.getTonnage();
            }
        }
        
        trainList.setListData(wagons);
        wagonCountLabel.setText("Вагонов: " + train.last_wagon + "/" + train.railcar_limit);
        totalTonnageLabel.setText("Грузоподъемность: " + totalWeight + " тонн");
    }
    
    private void clearTrain() {
        int choice = JOptionPane.showConfirmDialog(
            this, "Очистить весь состав?", 
            "Подтверждение", JOptionPane.YES_NO_OPTION
        );
        
        if (choice == JOptionPane.YES_OPTION) {
            for (int i = 0; i < train.last_wagon; i++) {
                train.train[i] = null;
            }
            
            train.last_wagon = 0;
            updateTrainList();
        }
    }
}