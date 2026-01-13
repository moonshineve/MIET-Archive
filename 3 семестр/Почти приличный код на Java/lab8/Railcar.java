package lab8;

import java.io.Serializable;

public class Railcar implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public static String[] vagon_types = {"Для контейнеров", "Для жидкостей", "Для сыпучих грузов", "Для автомобилей", "Для замороженных продуктов"};
    
    public Railcar() {
        // Конструктор по умолчанию для сериализации
    }
    
    public class RailcarParent implements Serializable {
        private static final long serialVersionUID = 2L;
        
        public int tonnage;
        
        public RailcarParent(int tonnage) { 
            this.tonnage = tonnage; 
        }
        
        public void setTonnage(int t) { 
            tonnage = t; 
        }
        
        public int getTonnage() { 
            return tonnage; 
        }
    }
    
    public class Container extends RailcarParent {
        public Container() { 
            super(70); 
        }
    }
    
    public class Cistern extends RailcarParent {
        public Cistern() { 
            super(60); 
        }
    }
    
    public class Friable extends RailcarParent {
        public Friable() { 
            super(60); 
        }
    }
    
    public class Auto extends RailcarParent {
        public Auto() { 
            super(10); 
        }
    }
    
    public class Refrigerator extends RailcarParent {
        public Refrigerator() { 
            super(15); 
        }
    }
}