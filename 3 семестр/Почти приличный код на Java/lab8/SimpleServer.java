package lab8;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class SimpleServer {
    private static final int DEFAULT_PORT = 12345;
    private ServerSocket serverSocket;
    private ExecutorService threadPool;
    private List<ClientHandler> clients;
    private Train serverTrain;
    private boolean isRunning;
    
    public static void main(String[] args) {
        SimpleServer server = new SimpleServer();
        server.start();
        
        // Console control
        server.consoleControl(); // «апускает консольное управление
    }
    
    public SimpleServer() {
        serverTrain = new Train();
        clients = Collections.synchronizedList(new ArrayList<>()); // ѕќ“ќ ќЅ≈«ќѕј—Ќќ—“№
        threadPool = Executors.newCachedThreadPool(); // пул потоков дл€ клиентов
        // абор предварительно созданных и готовых к работе потоков, которые используютс€ 
        // дл€ эффективного выполнени€ задач, эконом€ ресурсы, так как не нужно каждый раз 
        // создавать новый поток дл€ каждой задачи
    }
    
    public void start() {
        start(DEFAULT_PORT);
    }
    
    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            isRunning = true;
            System.out.println("Server started on port " + port);
            System.out.println("IP address: " + InetAddress.getLocalHost().getHostAddress());
            
            // прием подключений в отдельном потоке
            new Thread(() -> this.acceptConnections()).start();
            
        } catch (IOException e) {
            System.out.println("Server start error: " + e.getMessage());
        }
    }
    
    private void acceptConnections() {
        while (isRunning && serverSocket != null && !serverSocket.isClosed()) {
            try {
                Socket clientSocket = serverSocket.accept();
                ClientHandler client = new ClientHandler(clientSocket, this);
                clients.add(client);
                threadPool.execute(client);
                
                System.out.println("New connection: " + 
                    clientSocket.getInetAddress().getHostAddress() + 
                    " (Total: " + clients.size() + ")");
                
            } catch (IOException e) {
                if (isRunning) {
                    System.out.println("Connection accept error: " + e.getMessage());
                }
            }
        }
    }
    
    public void stop() {
        isRunning = false;
        
        synchronized(clients) {
            for (ClientHandler client : clients) {
                client.disconnect();
            }
            clients.clear();
        }
        
        // закрываю серверный сокет
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (IOException e) {
            System.out.println("Server close error: " + e.getMessage());
        }
        
        threadPool.shutdown(); // останавливаю пул потоков
        System.out.println("Server stopped");
    }
    
    public void disconnectClient(String clientId) {
        synchronized(clients) {
            for (ClientHandler client : clients) {
                if (client.getId().equals(clientId)) {
                    client.disconnect();
                    clients.remove(client);
                    System.out.println("Client disconnected: " + clientId);
                    return;
                }
            }
        }
        System.out.println("Client not found: " + clientId);
    }
    
    public void listClients() {
        synchronized(clients) {
            if (clients.isEmpty()) {
                System.out.println("No connected clients");
            } else {
                System.out.println("Connected clients (" + clients.size() + "):");
                for (ClientHandler client : clients) {
                    System.out.println("  " + client.getId() + " - " + 
                        client.getAddress() + " (active: " + client.isActive() + ")");
                }
            }
        }
    }
    
    public void showTrain() {
        System.out.println("Current train on server:");
        System.out.println("Railcars: " + serverTrain.last_wagon + "/" + serverTrain.railcar_limit);
        
        int totalWeight = 0;
        for (int i = 0; i < serverTrain.last_wagon; i++) {
            Train.Pair wagon = serverTrain.train[i];
            if (wagon != null) {
                System.out.println("  " + wagon.toString());
                totalWeight += wagon.railcar.getTonnage();
            }
        }
        System.out.println("Total capacity: " + totalWeight + " tons");
    }
    
    public void saveTrain(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(serverTrain);
            System.out.println("Train saved to file: " + filename);
        } catch (IOException e) {
            System.out.println("Save error: " + e.getMessage());
        }
    }
    
    public void loadTrain(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            serverTrain = (Train) ois.readObject();
            System.out.println("Train loaded from file: " + filename);
        } catch (Exception e) {
            System.out.println("Load error: " + e.getMessage());
        }
    }
    
    public Train getTrain() {
        return serverTrain;
    }
    
    public void setTrain(Train train) {
        this.serverTrain = train;
    }
    
    public void removeClient(ClientHandler client) {
        clients.remove(client);
        System.out.println("Client disconnected: " + client.getId() + 
            " (Remaining: " + clients.size() + ")");
    }
    
    private void consoleControl() {
        Scanner scanner = new Scanner(System.in);
        
        while (isRunning) {
            System.out.print("\nCommand (help - show help): ");
            String command = scanner.nextLine().trim().toLowerCase();
            
            switch (command) {
                case "help":
                    printHelp();
                    break;
                    
                case "list":
                    listClients();
                    break;
                    
                case "show":
                    showTrain();
                    break;
                    
                case "save":
                    System.out.print("Filename: ");
                    String saveFile = scanner.nextLine();
                    saveTrain(saveFile);
                    break;
                    
                case "load":
                    System.out.print("Filename: ");
                    String loadFile = scanner.nextLine();
                    loadTrain(loadFile);
                    break;
                    
                case "disconnect":
                    System.out.print("Client ID: ");
                    String clientId = scanner.nextLine();
                    disconnectClient(clientId);
                    break;
                    
                case "stop":
                    System.out.println("Stopping server...");
                    stop();
                    return;
                    
                case "add":
                    System.out.print("Railcar type (1-5): ");
                    try {
                        int type = Integer.parseInt(scanner.nextLine());
                        if (type >= 1 && type <= 5) {
                            serverTrain.addRailcar(type);
                            System.out.println("Added railcar type " + type);
                            showTrain();
                        } else {
                            System.out.println("Invalid railcar type");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number format");
                    }
                    break;
                    
                case "clear":
                    for (int i = 0; i < serverTrain.last_wagon; i++) {
                        serverTrain.train[i] = null;
                    }
                    serverTrain.last_wagon = 0;
                    System.out.println("Train cleared");
                    break;
                    
                case "":
                    // Empty command - do nothing
                    break;
                    
                default:
                    System.out.println("Unknown command. Type 'help' for help.");
            }
        }
        
        scanner.close();
    }
    
    private void printHelp() {
        System.out.println("\nAvailable commands:");
        System.out.println("  help        - show this help");
        System.out.println("  list        - list connected clients");
        System.out.println("  show        - show current train");
        System.out.println("  save <file> - save train to file");
        System.out.println("  load <file> - load train from file");
        System.out.println("  disconnect <id> - disconnect client");
        System.out.println("  add         - add railcar to train");
        System.out.println("  clear       - clear train");
        System.out.println("  stop        - stop server");
    }
    
    class ClientHandler implements Runnable {
        private Socket socket;
        private SimpleServer server;
        private ObjectOutputStream oos;
        private ObjectInputStream ois;
        private String id;
        private boolean active;
        
        public ClientHandler(Socket socket, SimpleServer server) {
            this.socket = socket;
            this.server = server;
            // например, "192.168.1.10:54321"
            this.id = socket.getInetAddress().getHostAddress() + ":" + socket.getPort();
            this.active = true;
        }
        
        public String getId() {
            return id;
        }
        
        public String getAddress() {
            return socket.getInetAddress().getHostAddress();
        }
        
        public boolean isActive() {
            return active && socket != null && !socket.isClosed();
        }
        
        @Override
        public void run() {
            try {
                oos = new ObjectOutputStream(socket.getOutputStream());
                ois = new ObjectInputStream(socket.getInputStream());
                
                while (active && !socket.isClosed()) {
                    String command = (String) ois.readObject();
                    
                    if ("GET_TRAIN".equals(command)) {
                        oos.writeObject(server.getTrain());
                        oos.flush();
                        System.out.println("Train sent to client " + id);
                    } 
                    else if ("SEND_TRAIN".equals(command)) {
                        Train receivedTrain = (Train) ois.readObject();
                        server.setTrain(receivedTrain);
                        oos.writeObject("OK: Received " + receivedTrain.last_wagon + " railcars");
                        oos.flush();
                        System.out.println("Train received from client " + id);
                    }
                    else if ("PING".equals(command)) {
                        oos.writeObject("PONG");
                        oos.flush();
                    }
                }
                
            } catch (EOFException e) {
            	// клиент корректно отключилс€
            } catch (Exception e) {
                if (active) {
                    System.out.println("Error with client " + id + ": " + e.getMessage());
                }
            } finally {
                disconnect();
            }
        }
        
        public void disconnect() {
            active = false;
            try {
                if (socket != null && !socket.isClosed()) {
                    socket.close();
                }
            } catch (Exception e) {
                
            }
            server.removeClient(this);
        }
    }
}