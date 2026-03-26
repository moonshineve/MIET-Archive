

namespace lab2{
    class Program
    {
        static void Main()
        {
            show_sorting();
            show_logging();
            show_energy_level();
        }

        private static void show_sorting()
        {
            Console.WriteLine("Демонстрация соритроки по возрасту");
            List<Organism> list = [new Animal("Крокодил", 20), new Plant("Борщевик", 5), new Plant("Крыжовник", 10)];
            
            Console.WriteLine("До Sort():");
            foreach (Organism organism in list)
            {
                Console.WriteLine(organism.GetStatus());
            }

            list.Sort();
            
            Console.WriteLine("\nПосле Sort():");
            foreach (Organism organism in list)
            {
                Console.WriteLine(organism.GetStatus());
            }
        }

        private static void show_logging()
        {
            Console.WriteLine("\nДемонстрация логгирования");
            List<Organism> list = [new Plant("Борщевик", 5), new Plant("Крыжовник", 10), new Animal("Крокодил", 20)];
            foreach (Organism organism in list)
            {
                if (organism is Animal) {Console.WriteLine( ((Animal)organism).LogInfo() );}
                else {{Console.WriteLine( ((Plant)organism).LogInfo() );}}
            }
        }

        private static void show_energy_level()
        {
            Console.WriteLine("\nДемонстрация рассчета уровня энергии\n");
            Animal animal = new("Мейн-кун", 3);
            Plant  plant = new("Мята", 1);

            Console.WriteLine($"Объект: {animal.GetStatus()}");
            animal.IsHungry = false;
            Console.WriteLine("Уровень энергии при отсутствии голода: " + animal.calculate_energy());
            animal.IsHungry = true;
            Console.WriteLine("Уровень энергии при наличии голода: " + animal.calculate_energy());


            
            Console.WriteLine($"\nОбъект: {plant.GetStatus()}");
            plant.LightLevel = 0.33;
            Console.WriteLine($"Уровень энергии при освещении в {plant.LightLevel*100}%: " + Math.Round(plant.calculate_energy(), 2));
            plant.LightLevel = 1.0;
            Console.WriteLine($"Уровень энергии при освещении в {plant.LightLevel*100}%: " + Math.Round(plant.calculate_energy(), 2));
        }
    }
}