namespace lab2{
    class Plant : Organism, ILoggable
    {    
        public Plant(string name, int age): base(name, age) {LightLevel = 1.0;}
        public double LightLevel
        { 
            get;
            set
            {
                if (value >= 0.0 && value <= 1.0){ field = value; }
                else { throw new ArgumentException($"Значение освещения {value} ∉ [0, 1]"); }
            }
        }

        override public double calculate_energy() { return Age*LightLevel*10; }

        public string LogInfo()
        {
            return $"[{DateTime.Now}] Статус: {GetStatus()}";
        } 
    }
}