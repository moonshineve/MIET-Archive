package lab7;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RaceController {

    private final RaceModel model;
    private final RaceView view;

    public RaceController(RaceModel m, RaceView v) {
        this.model = m;
        this.view = v;

        view.startBtn.addActionListener(e -> startRace());
        view.resetBtn.addActionListener(e -> resetRace());
    }
    

    private void startRace() { // создает задачи дл€ каждой кнопки-гонщика
        List<Runnable> tasks = new ArrayList<>(); // массив потоков
        Random rnd = new Random();

        for (JButton btn : view.racers) {
            tasks.add(() -> {
                while (!model.isRaceFinished() && !Thread.currentThread().isInterrupted()) {
                    try {
                        int speed = 1 + rnd.nextInt(10);

                        btn.setLocation(btn.getX() + speed, btn.getY());
                            if (btn.getX() >= view.finishX) {
                                model.declareWinner(btn.getText());
                            }
                            
                        Thread.sleep(20); // чтобы кнопки не перемещались слишком быстро
                    } catch (InterruptedException ex) {
                        return;
                    }
                }
            });
        }

        model.startRace(tasks);
    }

    
    private void resetRace() {
        model.stopRace();

        for (JButton btn : view.racers) {
            btn.setLocation(20, btn.getY());
            btn.setBackground(null); // чтоб победитель в следующей гонке стал нормальным
        }
    }

    public void finishRace(String winner) {
        SwingUtilities.invokeLater(() -> {
            for (JButton b : view.racers) {
                if (b.getText().equals(winner)) {
                    b.setBackground(java.awt.Color.MAGENTA);
                }
            }
            JOptionPane.showMessageDialog(view, "ѕобедитель: " + winner);
        });
    }
}