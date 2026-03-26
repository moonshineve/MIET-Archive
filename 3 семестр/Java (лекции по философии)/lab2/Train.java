package lab2;


public class Train {
	
	private int railcar_limit;
	private Pair[] train;
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
	
	
	public void addRailcar() {
		
		int railcar_type = View.checkAddRailcar(last_wagon, railcar_limit);
		
		if (railcar_type != 0) {
			
		}
			Railcar.RailcarParent railcar_instance;
			Storage.GoodsParent goods_instance;

			switch(railcar_type) {
			
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
				
			default: return;
			
			}
			
			train[last_wagon] = new Pair(railcar_instance, goods_instance);
			last_wagon += 1;
		}
	
	
	public void print_train() {
		View.print_train(train, last_wagon);
	}
	
	class Pair {
		Railcar.RailcarParent railcar;
		Storage.GoodsParent goods;
		
		Pair(Railcar.RailcarParent  railcar, Storage.GoodsParent goods) {
			this.railcar = railcar;
			this.goods   = goods;
		}
		
		public String toString() {
			return goods.getCargoType() + " " + railcar.getTonnage() + " тонн";
		}
	}
}
