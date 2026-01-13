package lab2;

import java.util.Collection;
import java.util.*;

import lab2.Train.Pair;


public class View {
	
	private static Scanner scanner;
	private static String pattern;
	
	static {
		scanner = new Scanner(System.in);
		pattern = "[12345]";
	}
	
	
	
	public static  void avaiable_vagons() {
		System.out.println("\n  Доступные типы вагонов ↓↓↓");
		for (int i=0; i<5; i++) {
			System.out.println("    "+(i+1) + ") "+ Railcar.vagon_types[i]);
		}
	}
	
	
	public static void avaiable_goods() {
		System.out.println("\n  Доступные типы грузов ↓↓↓");
		for (int i=0; i<5; i++) {
			System.out.println("    "+(i+1) + ") "+ Storage.goods_types[i]);
		}
	}
	
	// returns 0 if smthing is wrong
	public static int checkAddRailcar(int last_wagon, int railcar_limit) {
	
	if (last_wagon >= railcar_limit) {
		System.out.println("Достигунт лимит количества вагонов. Невозможно произвести загрузку.");
		return 0;
	}

	avaiable_vagons();
	
	System.out.print("Введите число от 1 до 5: ");
	
	String railcar_buffer = scanner.nextLine(); 
	
	if (railcar_buffer.matches(pattern)) {		
		
		avaiable_goods();
		
		System.out.print("Введите число от 1 до 5: ");
		
		String goods_buffer = scanner.nextLine(); 
		
		if (goods_buffer.matches(pattern)) {
			
			if (Integer.parseInt(railcar_buffer) != Integer.parseInt(goods_buffer)) { System.out.println("Тип вагона несоответствует типу товара. Невозможно произвести загрузку."); return 0; }
			
			else { return Integer.parseInt(railcar_buffer); }
				
		}
		else { System.out.println("Вы ввели несуществующий тип груза. Невозможно произвести загрузку."); return 0; }
					
	}
	else { System.out.println("Вы ввели несуществующий тип вагона. Невозможно произвести загрузку."); return 0; }
		
	}
	
	
	public static void print_train(Pair[] train, int last_wagon) {
		
		System.out.println();
		System.out.println("┏━━━━━━━━━  • ✤ • ━━━━━━━━━━┓");
		System.out.println("       Поезд типа Array      ");
		System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		
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
	
	
	// NEW
	public static void print_train(Collection<Pair> train) {
		
		String[] trainClass = train.getClass().toString().split("\\.");
		
		System.out.println();
		System.out.println("┏━━━━━━━━━  • ✤ • ━━━━━━━━━━┓");
		System.out.println("    Поезд типа " + trainClass[trainClass.length-1]);
		System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");

		
		System.out.print("       ____\n");
		System.out.print(" _T____|OO|\n");
		System.out.print(" >|____| _|");
		
		String wheels = "\n /oo-@-@-@   ";
		
		for (Pair pair : train) {
			System.out.print("-[" + pair + "]");
			
			for (int j=0; j<pair.toString().length(); j++) {
				
				if (j%2==0) { wheels += "@"; }
				
				else {
					if (j != pair.toString().length()-1) {wheels += "-"; }
					else {wheels += " ";}
				}
			}
			
			wheels += "   ";
			
		}
		
		System.out.println(wheels + "\n");

	}
	
	
	
	public void hello() {
		System.out.println("===============================================================");
		System.out.println("С помощью этой программы можно загрузить железнодорожный состав");
		System.out.println("===============================================================");
	}
	
	public int menu() {
		System.out.println();
		String[] commands = {"Добавить вагон", "Показать поезд", "Вывести типы вагонов", "Вывести типы грузов"};
		System.out.println("\n┏━━━━ Доступные комманды ━━━━┓");
		int formating = 25; // чтобы высчитать кол-во точек
		
		for (int i=0; i<commands.length; i++) {
			System.out.println("┃ " + commands[i] + ".".repeat(formating-commands[i].length()) + (i+1) + " ┃");
		}
		System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.print("  Ввод: ");	
		
		String buffer = scanner.nextLine();
		String buffer_pattern = "[1234]";
		
		if (buffer.matches(buffer_pattern)) { return Integer.parseInt(buffer); }
		else {return 0;}
	}
	
	
	// NEW
	public static int whichDataStructure() {
		
		String userChoice;
		String buffer_pattern = "[123]";
		
		System.out.println();
		System.out.println("┏━━ Какая структура данных ━━┓");
		System.out.println("┃ Array....................1 ┃");
		System.out.println("┃ ArrayList................2 ┃");
		System.out.println("┃ LinkedList...............3 ┃");
		System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.print("  Ввод: ");
		
		try {
			userChoice = scanner.nextLine();
			if (!userChoice.matches(buffer_pattern)) {
				throw new IllegalArgumentException("Допустимые типы поездов: 1, 2, 3");
			}
			return Integer.parseInt(userChoice);
		}
		catch (IllegalArgumentException ex) {
			System.err.println("  Возникла ошибка: " + ex.getMessage());
			return 0;
		}
	}
	
	
	// NEW
	public static <T> boolean unableToLoadTrain(Collection<T> train, int wagons_number, int railcar_limit) {
		
		try {
			
			if (train.size() + wagons_number > 	railcar_limit) {
				throw new IndexOutOfBoundsException("Невозможно загрузить " + wagons_number + " вагонов: всего " + (railcar_limit-train.size()) + " свободных мест");
			}
			return true;
		}
		catch (IndexOutOfBoundsException ex) {
			System.err.println("Возникла ошибка: " + ex.getMessage() + " (╥﹏╥)");
			return false;
		}
		
	}
	
	
	// NEW
	public static int scanningWagonNumToLoad() {
		int user_input = -1;
		System.out.print("  Введите количество вагонов для загрузки: ");
		
		try {
			user_input = scanner.nextInt();
			if (user_input < 0) { throw new InputMismatchException(); }
		}
		
		catch(InputMismatchException ex) {
			System.err.println("Ошибка: Ввод не соответствует неотрицательному целому числу ( •`ᴖ´• )");
		}
		
		// чтоб считать \n
		finally { scanner.nextLine(); }
		
		return user_input;
	}
	
}
