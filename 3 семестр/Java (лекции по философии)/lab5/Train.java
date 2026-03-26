package lab5;


public class Train {
	
	private int railcar_limit;
	public Pair[] train;
	private int last_wagon; // index
	
	Storage storage;
	Railcar railcar;
	
	Train() { 
		railcar_limit = 60; 
		train = new Pair[railcar_limit];
		
		last_wagon = 0;
		storage = new Storage();
		railcar = new Railcar();
		
	}
	
	public int getRailcarLimit() { return railcar_limit; }

	
	
	public void addRailcar(int railcar_type) {
		train[last_wagon] = new Pair(railcar_type);
		last_wagon += 1;
	}
	
	public void addRailcar() {
		int railcar_type = View.checkAddRailcar(last_wagon, railcar_limit);
		addRailcar(railcar_type);
	}
	
	public void print_train() {
		View.print_train(train, last_wagon);
	}
	
	
	class Pair {
		
		Railcar.RailcarParent railcar_instance;
		Storage.GoodsParent goods_instance;
		
		int wagon_type;
		
		Pair(int wagon_type) {
			
			switch(wagon_type) {
			case 1:
				railcar_instance = railcar.new Container();
				goods_instance   = storage.new Container();
				break;
				
			case 2:
				railcar_instance = railcar.new Cistern();
				goods_instance   = storage.new Fluid();
				break;
				
			case 3:
				railcar_instance = railcar.new Friable();
				goods_instance   = storage.new Friable();
				break;
				
			case 4:
				railcar_instance = railcar.new Auto();
				goods_instance   = storage.new Auto();
				break;
				
			case 5:
				railcar_instance = railcar.new Refrigerator();
				goods_instance   = storage.new Frozen();
				break;
			}
			
			this.wagon_type = wagon_type;
		}
		
		public String toString() {
			return goods_instance.getCargoType() + " " + railcar_instance.getTonnage() + " тонн";
		}
	}
}

