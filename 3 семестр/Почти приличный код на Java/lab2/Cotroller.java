package lab2;

public class Cotroller {
	View view;
	Train train;
	
	public void run() {
		view = new View();
		train = new Train();
		
		view.hello();
		int user_action;
		
		do {
		user_action = view.menu();
		
		switch (user_action) {
			case (1):
				train.addRailcar();
				break;
				
			case (2):
				train.print_train();
				break;
				
			case (3):
				View.avaiable_vagons();
				break;
				
			case (4):
				View.avaiable_goods();
				break;
			}
		} while (user_action != 0);
	}
}
