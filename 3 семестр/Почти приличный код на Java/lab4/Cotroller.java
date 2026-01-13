package lab4;

import java.util.Properties;
import java.util.logging.Logger;



public class Cotroller {
	View view;
	Train train;
	
	public void run() {
		
		view = new View();
		train = new Train();
		final Logger LOGGER = LoggerUtil.getLogger();
		LOGGER.setLevel(java.util.logging.Level.ALL);
		
		Properties prop = new Properties();

		try {Configuration config = new Configuration("config.ini", prop); }
		catch(Exception ex) {
			System.err.println(ex.getMessage() + " Невозможно продолджить выполнение программы.");
			System.exit(0);
		}
		
		boolean access_level = view.set_access(prop); // true - root;		false - user;
		LOGGER.info("Программа запущена. Пользователь: " + (access_level? "root" : "user"));
		
		boolean if_to_log =  Boolean.parseBoolean(prop.getProperty("if_to_log"));
		boolean autotest_mode =  Boolean.parseBoolean(prop.getProperty("autotest_mode"));
		
		view.hello();
		
		int user_action;
		
		
		if (autotest_mode) {
			System.out.println("Запуск временных тестов...");
            Experiment experiment = new Experiment();
            experiment.runAllTests();
            System.out.println("Тесты завершены. Результаты записаны в лог-файл.");
		}
		
			while (true) {
				
				try {
				
				user_action = access_level ? view.root_menu() : view.user_menu();
				
					switch (user_action) {
						
						case (0):
							System.out.println("\nВыполнение программы завершено");
							LOGGER.info("Выполнение программы завершено");
							System.exit(1);
						
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
							
						case (5):
							Database.updateDatabase(train);
							LOGGER.info("Данные занесены в БД");
							break;
						
						case (6):
							Database.getFromDatabase(train);
							LOGGER.info("Данные прочитаны из БД");
							break;
						
						case (7):
							if (access_level == true) {
								if_to_log = view.set_debugging_mode(prop);
							}
							break;
						
						case (8):
							if (access_level == true) {
								autotest_mode = view.set_autotest_mode(prop);
							}
							break;
					}
				}
				catch (Exception ex) { if (access_level || if_to_log) { LOGGER.warning(ex.getMessage()); } }
			} 
		
	}
}

