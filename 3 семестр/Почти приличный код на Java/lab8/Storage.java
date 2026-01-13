package lab8;

import java.io.Serializable;

public class Storage implements Serializable {
    private static final long serialVersionUID = 3L;
    
    public static String[] goods_types = {"Контейнеры", "Жидкости", "Сыпучие грузы", "Автомобили", "Замороженные продукты"};
    
    public Storage() {
        // Конструктор по умолчанию для сериализации
    }
    
    public class GoodsParent implements Serializable {
        private static final long serialVersionUID = 4L;
        private String name;
        
        GoodsParent(String name) {
            this.name = name;
        }
        
        public String getCargoType() { 
            return name; 
        }
    }
    
    public class Container extends GoodsParent {
        Container() { 
            super("Контейнер"); 
        }
    }
    
    public class Fluid extends GoodsParent {
        Fluid() { 
            super("Жидкости"); 
        }
    }
    
    public class Friable extends GoodsParent {
        Friable() { 
            super("Сыпучие грузы"); 
        }
    }
    
    public class Auto extends GoodsParent {
        Auto() { 
            super("Автомобили"); 
        }
    }
    
    public class Frozen extends GoodsParent {
        Frozen() { 
            super("Заомроженные продукты"); 
        }
    }
}