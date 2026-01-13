package lab5;

import javax.swing.*;
import java.util.Map;

public class GraphWindow extends JFrame {

    public GraphWindow(
            Map<Integer, Long> arrayAddAvg,
            Map<Integer, Long> listAddAvg,
            Map<Integer, Long> arrayRemoveAvg,
            Map<Integer, Long> listRemoveAvg
    ) {
        setTitle("Графики времени для Java-коллекций");
        setSize(900, 700);
        setLocationRelativeTo(null); // окно в центре экрана
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTabbedPane tabs = new JTabbedPane(); // панель вкладок

        tabs.add("Среднее добавление",
                new GraphPanel("Среднее время добавления", "нс", arrayAddAvg, listAddAvg));

        tabs.add("Среднее удаление",
                new GraphPanel("Среднее время удаления", "нс", arrayRemoveAvg, listRemoveAvg));

        add(tabs);
        setVisible(true);
    }
}
