
class WeatherStation
{
    public delegate void Action();
    public event Action? OnAlert;

    public void RaiseEvent()
    {
        OnAlert?.Invoke();
    }

    public void Unsubscribe(Sensor sensor)
    {
        OnAlert -= sensor.Message;
    }
}

