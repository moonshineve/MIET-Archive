package lab7;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class RaceView extends JFrame {

    public JButton startBtn = new JButton("Start");
    public JButton resetBtn = new JButton("Reset");

    public List<JButton> racers = new ArrayList<>();
    public int finishX; 

    public RaceView() {
        setTitle("Button Race");
        setSize(600, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        startBtn.setBounds(20, 10, 80, 30);
        resetBtn.setBounds(110, 10, 80, 30);

        add(startBtn); // ôþñðò ýð ª¨õùü
        add(resetBtn);

        for (int i = 0; i < 3; i++) {
            JButton b = new JButton("Runner " + (i + 1));
            b.setBounds(20, 60 + i * 50, 100, 30);
            racers.add(b);
            add(b);
        }

        finishX = 500;

        setVisible(true);
    }
}