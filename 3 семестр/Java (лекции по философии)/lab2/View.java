package lab2;
import java.util.Scanner;

import lab2.Train.Pair;


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
	
	public int menu() {
		String[] commands = {"Добавить вагон", "Показать поезд", "Вывести типы вагонов", "Вывести типы грузов"};
		System.out.println("\nДоступные комманды: ");
		int formating = 25; // чтобы высчитать кол-во точек
		
		for (int i=0; i<commands.length; i++) {
			System.out.println(commands[i] + ".".repeat(formating-commands[i].length()) + (i+1) );
		}
		
		System.out.print("Ввод: ");	
		
		String buffer = scanner.nextLine();
		String buffer_pattern = "[1234]";
		
		if (buffer.matches(buffer_pattern)) { return Integer.parseInt(buffer); }
		else {return 0;}
	}
	
	
}
