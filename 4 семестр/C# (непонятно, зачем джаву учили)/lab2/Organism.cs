namespace lab2{
    abstract class Organism : IComparable
    {
        private string? species { get; }
        private int age;

        public Organism(string species, int age)
        {
            this.species = species;
            this.age = age;
        }

        public int Age
        {
            get {return age;}
            set
            {
                if (value >=0){ age = value; }
            }
        }

        public abstract double calculate_energy();
        public virtual string GetStatus()
        { 
            string age_ru = "";
            
            if (age % 10 == 0 || age % 10 is >= 5 and <= 9 || age is >= 11 and <= 14) 
                { age_ru = "лет"; }

            else if (age % 10 == 1) 
                { age_ru = "год"; }

            else 
                { age_ru = "года";}

            return $"{species}, {age} {age_ru}"; 
        }
        public int CompareTo(object? o)
        {
            if (o is Organism organism) 
            { 
                return age.CompareTo(organism.Age); 
            }
            else 
            { 
                string type = o is null ? "null" : o.GetType().ToString();
                throw new ArgumentException($"Сравнение разных типов (Organism и {type})"); 
            }
        }
    }
}