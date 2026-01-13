package lab4;

import java.util.*;
import java.io.*;

import lab4.Train.Pair;


public class View {
	
	private static Scanner scanner;
	private static String pattern;
	
	static {
		scanner = new Scanner(System.in);
		pattern = "[12345]";
		
	}
	
	
	public static  void avaiable_vagons() {
		System.out.println("\nДоступные типы вагонов: ");
		for (int i=0; i<5; i++) {
			System.out.println("  "+(i+1) + ") "+ Railcar.vagon_types[i]);
		}
	}
	
	
	public static void avaiable_goods() {
		System.out.println("\nДоступные типы грузов: ");
		for (int i=0; i<5; i++) {
			System.out.println("  "+(i+1) + ") "+ Storage.goods_types[i]);
		}
	}

	
	public static int checkAddRailcar(int last_wagon, int railcar_limit) throws IndexOutOfBoundsException {
		
		if (last_wagon > railcar_limit) {
			throw new IndexOutOfBoundsException("Невозможно произвести загрузку: достигунт лимит количества вагонов в поезде типа Array");
		}

		avaiable_vagons();
		
		System.out.print("  Ввод: ");
		
		String railcar_buffer = scanner.nextLine(); 
		
		if (railcar_buffer.matches(pattern)) {		
			
			avaiable_goods();
			
			System.out.print("  Ввод: ");
			
			String goods_buffer = scanner.nextLine(); 
			
			if (goods_buffer.matches(pattern)) {
				
				
				if (Integer.parseInt(railcar_buffer) != Integer.parseInt(goods_buffer)) { 
					throw new InputMismatchException(
							"Невозможно загрузить " + Storage.goods_types[Integer.parseInt(goods_buffer)].toLowerCase() + 
							" в вагон " + Railcar.vagon_types[Integer.parseInt(railcar_buffer)].toLowerCase());
				}
				
				else { return Integer.parseInt(railcar_buffer); }
				
					
			}
			else { throw new InputMismatchException("Некорректный выбор типа груза для поезда (Ввод: "  + goods_buffer + ")" ); }
						
		}
		else { throw new InputMismatchException("Некорректный выбор типа вагона для поезда (Ввод: "  + railcar_buffer + ")" ); }
			
		}
	
	
	public static void print_train(Pair[] train, int last_wagon) {
		
		System.out.print("       ____\n");
		System.out.print(" _T____|OO|\n");
		System.out.print(" >|____| _|");
		
		String wheels = "\n /oo-@-@-@   ";
		
		for (int i=0; i<last_wagon; i++) {
			System.out.print("-[" + train[i] + "]");

			for (int j=0; j<train[i].toString().length(); j++) {
				if (j%2==0) { wheels += "@"; }
				
				else {
					if (j != train[i].toString().length()-1) {wheels += "-"; }
					else {wheels += " ";}
				}
			}
			wheels += "   ";
		}
		
		System.out.println(wheels);

	}
	
	
	
	public void hello() {
		System.out.println("===============================================================");
		System.out.println("С помощью этой программы можно загрузить железнодорожный состав");
		System.out.println("===============================================================");
	}
	
	
	
	public int user_menu() throws InputMismatchException {
		System.out.println();
		String[] commands = {"Завершить программу", "Добавить вагон", "Показать поезд", "Вывести типы вагонов", "Вывести типы грузов"};
		
		System.out.println("\nДоступные комманды: ");
		int formating = 25; // чтобы высчитать кол-во точек
		
		for (int i=0; i<commands.length; i++) {
			System.out.println(commands[i] + ".".repeat(formating-commands[i].length()) + (i) );
		}
		
		System.out.print("  Ввод: ");	
		
		String buffer;
		String buffer_pattern = "[01234]";
		
		buffer = scanner.nextLine();
		if (! buffer.matches(buffer_pattern)) {
			throw new InputMismatchException("Некорректный выбор действия пользователем user в главном меню (Ввод: "  + buffer + ")");
		}
		return Integer.parseInt(buffer);
	}
	
	
	
	public int root_menu() {
		String[] commands = {"Завершить программу", "Добавить вагон", "Показать поезд", "Вывести типы вагонов", "Вывести типы грузов", "Записать данные в ДБ",
							 "Получить данные из ДБ", "Вкл/выкл режим отладки", "Вкл/выкл автотесты"};
		
		System.out.println("\nДоступные комманды: ");
		int formating = 25; // чтобы высчитать кол-во точек
		
		for (int i=0; i<commands.length; i++) {
			System.out.println(commands[i] + ".".repeat(formating-commands[i].length()) + (i) );
		}
		
		System.out.print("Ввод: ");	
		
		String buffer = scanner.nextLine();
		String buffer_pattern = "[012345678]";
		
		if (! buffer.matches(buffer_pattern)) {
			throw new InputMismatchException("Некорректный выбор действия пользователем root в главном меню (Ввод: "  + buffer + ")");
		}
		return Integer.parseInt(buffer);
	}
	
	
	
	
	// true - root;		false - user;	 exit(0) - something wrong
	public boolean set_access(Properties prop) {
		if (prop.isEmpty()) {
			System.err.println("Не удалось загрузить данные из БД. Продолжение работы невозможно.");
			System.exit(0);
		}
		
		String right_user_password = prop.getProperty("user_password");
		String right_root_password = prop.getProperty("root_password");
		String user_name;
		String user_password;
		
		
		while (true) {
			
			final String ANSI_RESET = "\u001B[0m";
			final String ANSI_GREEN = "\u001B[32m";
			final String ANSI_RED = "\u001B[31m";
			
			System.out.print("Введите имя пользователя (user/root): ");
			user_name = scanner.nextLine();
			
			System.out.print("Введите пароль пользователя: ");
			user_password = scanner.nextLine();
			
			if (user_name.equals("root") && user_password.equals(right_root_password)) {
				System.out.println(ANSI_GREEN + "Вы получили root-доступ к программе\n" + ANSI_RESET);
				return true;
			}
			
			if (user_name.equals("user") && user_password.equals(right_user_password)) {
				System.out.println(ANSI_GREEN + "Вы получили доступ к программе\n" + ANSI_RESET);
				return false;
			}
			
			System.out.println(ANSI_RED + "Неправильный логин или пароль. Попробуйте заново\n"+ ANSI_RESET);
		}
		
	}
	
	public boolean set_debugging_mode(Properties props) throws InputMismatchException, IOException {
	    
	    System.out.println("1........вкл");
	    System.out.println("2.......выкл");
	    System.out.print("Ввод: ");
	    
	    String user_input = scanner.nextLine();
	    String user_input_pattern = "[12]";
	    
	    if (user_input.matches(user_input_pattern)) {
	        
	    	String debug_mode = user_input.equals("1") ? "true" : "false";
	    	
	        props.setProperty("if_to_log", debug_mode);
	        
	        final File f = new File("config.ini");
	        if (!f.exists()) {
	            f.createNewFile();
	        }
	        
	        FileOutputStream out = new FileOutputStream(f); // поток вывода для записи в файл
	        props.store(out, null); // запись в файл, null - без комментариев
	        
	        System.out.println("Режим отладки успешно установлен");
	        
	        return Boolean.parseBoolean(debug_mode);
	    }
	    
	    else {
	        throw new InputMismatchException("Некорректный выбор действия пользователем root при выборе режима отладки (Ввод: " + user_input + ")");
	    }
	}
		
	
	
	
	public boolean set_autotest_mode(Properties props) throws InputMismatchException, IOException {
	    
	    System.out.println("1........вкл");
	    System.out.println("2.......выкл");
	    System.out.print("Ввод: ");
	    
	    String user_input = scanner.nextLine();
	    String user_input_pattern = "[12]";
	    
	    if (user_input.matches(user_input_pattern)) {
	        
	    	String autotest_mode = user_input.equals("1") ? "true" : "false";
	    	
	        props.setProperty("autotest_mode", autotest_mode);
	        
	        final File f = new File("config.ini");
	        if (!f.exists()) {
	            f.createNewFile();
	        }
	        
	        FileOutputStream out = new FileOutputStream(f); // поток вывода для записи в файл
	        props.store(out, null); // запись в файл, null - без комментариев
	        
	        System.out.println("Режим для автотестов успешно установлен");
	        
	        return Boolean.parseBoolean(autotest_mode);
	    }
	    
	    else {
	        throw new InputMismatchException("Некорректный выбор действия пользователем root при выборе режима отладки (Ввод: " + user_input + ")");
	    }
	}
	
	
}

