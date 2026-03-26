class Program
{
    static void Main(string[] args)
    {
        task1();
        task2();
    }

    public static void task1()
    {
        WeatherStation weatherStation = new();

        for (int i = 0; i < 10; i++)
        {
            Sensor sensor = new(i, weatherStation);
            weatherStation.Unsubscribe(sensor);
        }
        GC.Collect();
        GC.WaitForPendingFinalizers();
        weatherStation.RaiseEvent();
    }

    public static void task2()
    {
        Sensor[] arr = new Sensor[10];

        for (int i = 0; i < 10; i++)
        {
            arr[i] = new(i, i * 2);
        }

        string frgt_format = "{0:F1}°F  ";
        string klvn_format = "{0:F1}К   ";

        Func<Sensor, string> toFahrenheit = s => string.Format(frgt_format, s.CelsiusTemperatureValue * 9 / 5 + 32);
        Func<Sensor, string> toKelvin = s => string.Format(klvn_format, s.CelsiusTemperatureValue + 273.15);

        Console.WriteLine("Температура в Фаренгейтах");
        ConvertCollection(arr, toFahrenheit);

        Console.WriteLine("\n\nТемпература в Кельвинах");
        ConvertCollection(arr, toKelvin);
    }

    static void ConvertCollection<T, TResult>(T[] items, Func<T, TResult> func)
    {
        for (int i = 0; i < items.Length; i++)
            Console.Write(func(items[i]));
    }
}
