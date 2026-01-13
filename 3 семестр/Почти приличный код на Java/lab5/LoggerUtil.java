package lab5;

import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;
import java.io.IOException;

public class LoggerUtil {
    private static Logger logger;
    
    static {
        logger = Logger.getLogger(Cotroller.class.getName());
        try {
            FileHandler fileHandler = new FileHandler("lab4.log", true);
            fileHandler.setFormatter(new SimpleFormatter() {
                @Override
                public String format(java.util.logging.LogRecord record) {
                    return record.getLevel() + ": " + record.getMessage() + "\n";
                }
            });
            fileHandler.setLevel(java.util.logging.Level.ALL);
            logger.addHandler(fileHandler);
            logger.setLevel(java.util.logging.Level.ALL);
            logger.setUseParentHandlers(false);
            
        } catch (IOException e) {
            System.err.println("Не удалось настроить файл логов: " + e.getMessage());
        }
    }
    
    public static Logger getLogger() {
        return logger;
    }
}