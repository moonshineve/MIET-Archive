package lab5;


public class Railcar {
	
	public static String[] vagon_types = {"Для контейнеров", "Для жидкостей", "Для сыпучих грузов", "Для автомобилей", "Для замороженных продуктов"};
	
	public class RailcarParent {
		
		public int tonnage;
		
		public RailcarParent(int tonnage) { this.tonnage = tonnage; }
		public void setTonnage(int t) { tonnage = t; }
		public int getTonnage() { return tonnage; }
		
	}
	
	public class Container extends RailcarParent {
		
		Container() { super(70); }
	}
	
	public class Cistern extends RailcarParent {
		
		Cistern() { super(60); }
	}
	
	public class Friable extends RailcarParent {
		
		Friable() { super(60); }
	}
	
	public class Auto extends RailcarParent {
		
		Auto() { super(10); }
	}
	
	public class Refrigerator extends RailcarParent {
		
		Refrigerator() { super(15); }
	}
	

}

