package lab6;

public class Train {
    public int railcar_limit;
    public Train.Pair[] train; 
    public int last_wagon;
    
    Storage storage;
    Railcar railcar;
    
    public Train() { 
        railcar_limit = 60; 
        train = new Pair[railcar_limit];
        last_wagon = 0;
        storage = new Storage();
        railcar = new Railcar();
    }
    
    public void addRailcar(int railcar_type) {
		
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
		
		train[last_wagon] = new Pair(railcar_instance, goods_instance, last_wagon+1);
		last_wagon += 1;
	}
    
    
    
    public class Pair {
        public Railcar.RailcarParent railcar; 
        public Storage.GoodsParent goods;
        public int wagon_number;
        
        public Pair(Railcar.RailcarParent railcar, Storage.GoodsParent goods, int wagon_number) {
            this.railcar = railcar;
            this.goods = goods;
            this.wagon_number = wagon_number;
        }
        
        public String toString() {
            return "Вагон " + wagon_number + ": " + goods.getCargoType() + " " + railcar.tonnage + " тонн";
        }
    }
    
}