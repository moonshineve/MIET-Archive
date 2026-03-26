
class Sensor
{
    private int _sensor_number;
    public double CelsiusTemperatureValue { get; set; }
    public Sensor(int sensor_number, double celsiusTemperatureValue)
    {
        _sensor_number = sensor_number;
        CelsiusTemperatureValue = celsiusTemperatureValue;
    }
    public Sensor(int sensor_number, WeatherStation weather_station)
    {
        _sensor_number = sensor_number;
        weather_station.OnAlert += this.Message;
    }

    public void Message()
    {
        Console.WriteLine($"<Сенсор {_sensor_number} жив>");
    }
}

