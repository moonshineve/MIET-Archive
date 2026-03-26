package lab4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;



public class Configuration {
    
    public Configuration(String FileName, Properties props) throws IOException, Exception {
        getConfigFile(FileName, props);
    }
    
    private void getConfigFile(String FileName, Properties props) throws FileNotFoundException, IOException, Exception {        
        InputStream fs;
        
        try {
            File f = new File(FileName);             
            fs = new FileInputStream(f); 
            final String ANSI_RESET = "\u001B[0m";
            final String ANSI_GREEN = "\u001B[32m";
            
            props.load(fs);
            System.out.println(ANSI_GREEN + "Конфигурация загружена\n" + ANSI_RESET);
            fs.close();
            
        } 
        
        catch (FileNotFoundException e) {
            System.err.println("Ошибка: конфигурационный файл не найден.");
        } 
        
        catch (IOException e) {
            System.err.println("Ошибка при чтении конфигурационного файла.");
        } 
        
        catch (Exception e) {
            throw new Exception("Ошибка при загрузке конфигурационного файла.");
        }
        
    }
    
}
