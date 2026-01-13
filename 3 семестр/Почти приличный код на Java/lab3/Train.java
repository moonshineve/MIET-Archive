package lab2;

import java.util.*;

public class Train {
	
	private static int railcar_limit = 60;
	private Pair[] trainArray;
	private int last_wagon; // index
	
	private ArrayList<Pair> trainArrayList; // NEW
	private LinkedList<Pair> trainLinkedList; // NEW
	Storage storage;
	Railcar railcar;
	
	Train() { 
		trainArray = new Pair[railcar_limit];
		
		last_wagon = 0;
		storage = new Storage();
		railcar = new Railcar();
		
		trainArrayList = new ArrayList<Pair>(); // NEW
		trainLinkedList = new LinkedList<Pair>(); // NEW
	}
	
	public int getRailcarLimit() { return railcar_limit; }
	
	
	private Pair createPair(int railcar_type) {

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
			
		default: return null;
		
		}
		
		return new Pair(railcar_instance, goods_instance);
	}
	
	
	public void addRailcar() {
		int whereToAdd = View.whichDataStructure(); // 1 - array, 2 - ArrayList, 3 - LinkedList
		
		switch(whereToAdd) {
		
		case 1:
			
			int railcar_type = View.checkAddRailcar(last_wagon, railcar_limit);
			
			if (railcar_type != 0) {
				trainArray[last_wagon] = createPair(railcar_type);
				last_wagon += 1;
			}
			break;
		
		case 2:
			int wagon_number = View.scanningWagonNumToLoad();
			if (wagon_number != -1) { GenerateWagons(trainArrayList, wagon_number); }
			break;
			
		case 3:
			wagon_number = View.scanningWagonNumToLoad();
			if (wagon_number != -1) { GenerateWagons(trainLinkedList, wagon_number); }
			break;
		}
	}
	

	
	// NEW
	private void GenerateWagons(Collection<Pair> train, int wagons_number) {
		
		boolean if_possible_to_load = View.unableToLoadTrain(train, wagons_number, railcar_limit);
		
		if (if_possible_to_load) {
			
			Random generator = new Random();
			int random_wagon_type;
			
			for (int i=0; i<wagons_number; i++) {
				
				random_wagon_type = generator.nextInt(4)+1;
				train.add(createPair(random_wagon_type));
				
			}
		}
	}
	
	// NEW
	public void print_train() {
		int whichTrainToPrint = View.whichDataStructure();

		switch(whichTrainToPrint) {
		case 0:
			return;
		case 1:
			View.print_train(trainArray, last_wagon);
			break;
		case 2:
			View.print_train(trainArrayList);
			break;
		case 3:
			View.print_train(trainLinkedList);
			break;
		}
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
