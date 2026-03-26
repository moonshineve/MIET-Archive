
class Program
{
    static void Main(string[] args)
    {
        task2();
    }

    public static void task1()
    {
        int N = 10;
        List<Sensor> sensor_list = new List<Sensor>(N);
        Console.WriteLine("Коллекция: ");
        for (int i = 0; i < N; i++)
        {
            Sensor sensor = new Sensor(i + 1);
            sensor_list.Add(sensor);
            Console.WriteLine(sensor + " ");
        }

        var linq_list = sensor_list
            .Where(s => s.Temperature < 15)
            .OrderBy(s => s.Temperature)
            .Select(s => s.Temperature);

        Console.WriteLine("\nТемпературы меньше 15°C: ");
        foreach (float temperature in linq_list)
        {
            Console.Write($"{temperature:F1}°C  ");
        }
    }

    public static void task2()
    {
        var sensors = new List<Sensor>();
        for (int i = 0; i < 10; i++) sensors.Add(new Sensor(i+1));


        var groups = sensors.GroupBy(s => s.SensorType);
        foreach (var g in groups)
        {
            Console.WriteLine(g.ToString());
            Console.WriteLine($"{g.Key}: {g.Count()} шт, средняя темп: {g.Average(s => s.Temperature):F1}°C\n");
        }


        Console.WriteLine($"\nЕсть темп > 25°C? {sensors.Any(s => s.Temperature > 25)}");
        Console.WriteLine($"Все Indoor? {sensors.All(s => s.SensorType == SensorTypes.IndorSensor)}");

        // 3. ToDictionary - словарь по ID
        var dict = sensors.ToDictionary(s => s.SensorId, s => s);
        Console.WriteLine($"\nДатчик №5: {dict[5]}");

        // 4. Join - соединение с комнатами
        var rooms = new[] { (1, "Зал"), (2, "Офис"), (3, "Склад") };
        var joined = sensors.Join(rooms, s => s.SensorId, r => r.Item1, (s, r) => $"{r.Item2}: {s.Temperature:F1}°C");
        foreach (var j in joined) Console.WriteLine(j);
    }
}

