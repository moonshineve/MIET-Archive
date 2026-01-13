package lab4;

import java.io.*;
import lab4.Train.Pair;

public class Database {
	
	static int last_id;
	
	public static void updateDatabase(Train train) {
		
		File database = new File("database.txt");
		
        try(FileWriter writer = new FileWriter(database, true))
        {
        	if (database.length() == 0) {
	            last_id=0;
        	}
        	
        	else {

                BufferedReader reader = new  BufferedReader(new FileReader("database.txt"));
                String line = reader.readLine();
                String last_id_record = line;
                
                while (line != null) {

                    if (line.length()>1 && line.substring(0, 2).equals("id")) {
                        last_id_record = line;
                    }

                    line = reader.readLine();
                }
                last_id = Integer.parseInt(last_id_record.substring(3));
                reader.close();
        	}
        	
        	System.out.println("In database");
        	
        	for (Pair pair: train.train) {
        		if (pair != null) {
	        		writer.write("id=" + (++last_id) + "\n");
	        		writer.write("type=" + pair.wagon_type + "\n\n");
        		}
        		else { break; }
        	}
        }
        
        catch(IOException e){ System.err.println(e.getMessage()); } 
		
	}
	
	
	public static void getFromDatabase(Train train) {
		try (BufferedReader reader = new  BufferedReader(new FileReader("database.txt"))) {
			
	        String line = reader.readLine();
	        int wagon_type_buffer;
	        
	        while (line != null) {
	
	            if (line.length()>3 && line.substring(0, 4).equals("type")) {
	            	wagon_type_buffer = Integer.parseInt(line.substring(5));
	            	train.addRailcarBypassConsole(wagon_type_buffer);
	            }

	            line = reader.readLine();
	        }
		}
		catch (Exception e) { System.err.println(e.getMessage()); }
	}
	
	
}