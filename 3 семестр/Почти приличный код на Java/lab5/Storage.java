package lab5;


public class Storage {
	
	public static String[] goods_types = {"Контейнеры", "Жидкости", "Сыпучие грузы", "Автомобили", "Замороженные продукты"};
	
	
	public class GoodsParent {
		private String name;
		
		GoodsParent(String name) {
			this.name = name;
		}
		
		public String getCargoType() { return name; }

	}
	
	// рузы 5-ти типов: контейнеры, жидкости, сыпучие грузы, автомобили, замороженные продукты;
	
	public class Container extends GoodsParent{
		
		Container() { super("Контейнер"); }
	}
	
	public class Fluid extends GoodsParent{
		
		Fluid() { super("Жидкости"); }
	}
	
	public class Friable extends GoodsParent{
		
		Friable() { super("Сыпучие грузы"); }
	}
	
	public class Auto extends GoodsParent{
		
		Auto() { super("Автомобили"); }
	}
	
	public class Frozen extends GoodsParent{
		
		Frozen() { super("Заомроженные продукты"); }
	}
	
}
