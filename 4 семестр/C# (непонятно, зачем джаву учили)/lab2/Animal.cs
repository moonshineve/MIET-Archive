namespace lab2{
    class Animal : Organism, ILoggable
    {
        public Animal(string name, int age): base(name, age) {}

        public bool IsHungry {get; set;} = true;

        override public double calculate_energy()
        {
            return Age*(1 - (IsHungry ? 1 : 0)/2.0)*10;
        }

        public string LogInfo()
        {
            return $"[{DateTime.Now}] Статус: {GetStatus()}";
        } 
    }
}