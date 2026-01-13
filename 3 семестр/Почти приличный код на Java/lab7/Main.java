package lab7;

public class Main {
	
    public static void main(String[] args) {

        var model = new RaceModel();
        var view = new RaceView();
        var controller = new RaceController(model, view);
        
        model.setController(controller); 

    }
}
