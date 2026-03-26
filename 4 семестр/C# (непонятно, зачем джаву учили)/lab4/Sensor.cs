
class Sensor
{
    public int SensorId { get; }
    public SensorTypes SensorType;
    public float Temperature { get; set; }
    public byte Humidity { get; set; } 

    public Sensor(int sensor_number)
    {
        var rand = new Random();
        SensorId = sensor_number;
        SensorType = rand.Next() % 2 == 1 ? SensorTypes.IndorSensor : SensorTypes.OutdoorSensor;

        Temperature = (float)rand.NextDouble() * 30;
        Humidity = (byte)rand.Next(101);

    }

    override public string ToString()
    {
        return $"Сенсор {SensorId,2} " +
               $"Тип: {SensorType,-13} " +
               $"Температура: {Temperature,4:F1}°C " +
               $"Влажность: {Humidity,2}%";
    }
}

