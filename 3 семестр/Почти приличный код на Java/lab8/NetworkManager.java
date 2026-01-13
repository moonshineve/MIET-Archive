package lab8;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;

public class NetworkManager extends JFrame {
    private MyFrame parentFrame;
    private JTextArea logArea;
    private JTextField hostField;
    private JTextField portField;
    
    public NetworkManager(MyFrame parentFrame) {
        this.parentFrame = parentFrame;
        initUI();
    }
    
    private void initUI() {
        setTitle("Сетевой обмен данными");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setSize(500, 400);
        setLocationRelativeTo(null);
        
        // панель параметров
        JPanel paramPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        paramPanel.setBorder(BorderFactory.createTitledBorder("Параметры подключения"));
        
        
        paramPanel.add(new JLabel("Порт:"));
        portField = new JTextField("12345");
        paramPanel.add(portField);
        
        // Панель кнопок
        JPanel buttonPanel = new JPanel(new GridLayout(6, 1, 5, 5));
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Операции"));
        
        JButton testConnectionBtn = new JButton("Проверить подключение");
        testConnectionBtn.addActionListener(e -> testConnection());
        
        JButton sendBtn = new JButton("Отправить состав");
        sendBtn.addActionListener(e -> sendTrain());
        
        JButton receiveBtn = new JButton("Получить состав");
        receiveBtn.addActionListener(e -> receiveTrain());
        
        JButton sendFileBtn = new JButton("Сохранить в файл");
        sendFileBtn.addActionListener(e -> saveToFile());
        
        JButton loadFileBtn = new JButton("Загрузить из файла");
        loadFileBtn.addActionListener(e -> loadFromFile());
        
        JButton startServerBtn = new JButton("Запустить простой сервер");
        startServerBtn.addActionListener(e -> startSimpleServer());
        
        buttonPanel.add(testConnectionBtn);
        buttonPanel.add(sendBtn);
        buttonPanel.add(receiveBtn);
        buttonPanel.add(sendFileBtn);
        buttonPanel.add(loadFileBtn);
        buttonPanel.add(startServerBtn);
        
        // логи от сервера
        logArea = new JTextArea(10, 40); // многострочная область для отображения текста
        logArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logArea);
        
        JPanel leftPanel = new JPanel(new BorderLayout(10, 10));
        leftPanel.add(paramPanel, BorderLayout.NORTH);
        leftPanel.add(buttonPanel, BorderLayout.CENTER);
        
        add(leftPanel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);
        
        setVisible(true);
    }
    
    
    private void testConnection() {
        new Thread(() -> {
            try {
                String host = "localhost";
                int port = Integer.parseInt(portField.getText());
                
                log("Проверка подключения к " + host + ":" + port + "...");
                Socket socket = new Socket(host, port);
                
                // поток для отправки объектов на сервер
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                // поток для получения объектов от сервера
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                
                oos.writeObject("PING"); // отправляет строку "PING" как объект
                oos.flush(); 			 // гарантирует, что данные сразу отправятся, а не будут ждать в буфере
                

                String response = (String) ois.readObject(); // сервер должен ответить "PONG"
                
                if ("PONG".equals(response)) {
                    log("✓ Сервер доступен");
                } else {
                    log("✗ Неизвестный ответ сервера: " + response);
                }
                
                socket.close();
                
            } catch (ConnectException e) {
                log("✗ Сервер недоступен");
            } catch (Exception e) {
                log("Ошибка: " + e.getMessage());
            }
        }).start();
    }
    
    private void sendTrain() {
        new Thread(() -> {
            try {
                String host = "localhost";
                int port = Integer.parseInt(portField.getText());
                
                log("Отправка состава на " + host + ":" + port + "...");
                Socket socket = new Socket(host, port);
                
                // поток для отправки объектов на сервер
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                
                // поток для получения объектов от сервера
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                
                oos.writeObject("SEND_TRAIN");
                oos.writeObject(parentFrame.train);
                oos.flush();
                
                String response = (String) ois.readObject();
                log("Ответ сервера: " + response);
                
                socket.close();
                
            } catch (ConnectException e) {
                log("✗ Сервер недоступен");
            } catch (Exception e) {
                log("Ошибка отправки: " + e.getMessage());
            }
        }).start();
    }
    
    private void receiveTrain() {
        new Thread(() -> {
            try {
                String host = "localhost";
                int port = Integer.parseInt(portField.getText());
                
                log("Получение состава с " + host + ":" + port + "...");
                Socket socket = new Socket(host, port);
                
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                
                oos.writeObject("GET_TRAIN");
                oos.flush();
                
                Train receivedTrain = (Train) ois.readObject();
                updateParentTrain(receivedTrain);
                
                log("✓ Состав получен: " + receivedTrain.last_wagon + " вагонов");
                
                socket.close();
                
            } catch (ConnectException e) {
                log("✗ Сервер недоступен");
            } catch (Exception e) {
                log("Ошибка получения: " + e.getMessage());
            }
        }).start();
    }
    
    private void updateParentTrain(Train newTrain) {
        parentFrame.train.last_wagon = newTrain.last_wagon;
        
        for (int i = 0; i < newTrain.last_wagon; i++) {
            if (newTrain.train[i] != null) {
                parentFrame.train.train[i] = newTrain.train[i];
            }
        }

        
        parentFrame.updateTrainList();
        log("Локальный состав обновлен (" + parentFrame.train.last_wagon + " вагонов)");
    }
    
    private void saveToFile() {
        JFileChooser fileChooser = new JFileChooser(); // диалоговое окно для выбора файлов в системе
        
        // JFileChooser.APPROVE_OPTION - константа, означающая, что пользователь нажал "Сохранить"
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            // потока для записи сер объекта
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                oos.writeObject(parentFrame.train);
                log("Состав сохранен в файл: " + file.getName());
            } catch (IOException e) {
                log("Ошибка сохранения: " + e.getMessage());
            }
        }
    }
    
    private void loadFromFile() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                Train loadedTrain = (Train) ois.readObject();
                updateParentTrain(loadedTrain);
                log("Состав загружен из файла: " + file.getName());
            } catch (Exception e) {
                log("Ошибка загрузки: " + e.getMessage());
            }
        }
    }
    
    private void startSimpleServer() {
        new Thread(() -> {
            try {
                int port = Integer.parseInt(portField.getText());
                
                // запускаем сервер в отдельном процессе т.к.
                // если запустить сервер в том же процессе то
                // нельзя остановить сервер не убивая клиент
                ProcessBuilder pb = new ProcessBuilder(
                    "java", "lab8.SimpleServer"
                ); // "java" - путь к исполняемому файлу
                
                Process process = pb.start();
                log("Сервер запущен в отдельном процессе (порт " + port + ")");
                log("Для управления сервером используйте консоль");
                
                BufferedReader reader = new BufferedReader(
                	// содержит все что сервер пишет в свой System.out
                    new InputStreamReader(process.getInputStream())
                );
                
                // бесконечный цикл выводит информацию с сервера пока он работает
                while (true) {
                    String line = reader.readLine();
                    if (line == null) {
                        break; // Выход из цикла
                    }
                    log("[Сервер] " + line);
                }
                
            } catch (Exception e) {
                log("Ошибка запуска сервера: " + e.getMessage());
            }
        }).start();
    }
    
    private void log(String message) {
        
        String time = String.format("%tT", new java.util.Date());
        logArea.append("[" + time + "] " + message + "\n");

    }
}