package lab7;

import java.util.ArrayList;
import java.util.List;

public class RaceModel {

    private final List<Thread> runners = new ArrayList<>();
    volatile private boolean raceFinished = false;

    private RaceController controller;   

    public void setController(RaceController c) {
        this.controller = c;
    }

    public void startRace(List<Runnable> tasks) {

        raceFinished = false;
        runners.clear();

        for (Runnable r : tasks) {
            Thread t = new Thread(r);
            runners.add(t);
            t.start();
        }
    }

    public synchronized boolean isRaceFinished() {
        return raceFinished;
    }

    public synchronized void declareWinner(String name) {
        if (!raceFinished) {
            raceFinished = true;

            if (controller != null)
                controller.finishRace(name);   // <-- прямой вызов контроллера
        }
    }

    public void stopRace() {
        for (Thread t : runners) {
            if (t != null && t.isAlive())
                t.interrupt();
        }
        runners.clear();
    }
}