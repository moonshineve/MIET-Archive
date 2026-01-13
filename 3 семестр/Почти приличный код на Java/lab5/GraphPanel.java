package lab5;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class GraphPanel extends JPanel {

    private final Map<Integer, Long> arrayData;
    private final Map<Integer, Long> listData;
    private final String title;
    private final String xLabel;
    private final String yLabel;

    public GraphPanel(String title, String yLabel,
                      Map<Integer, Long> arrayData,
                      Map<Integer, Long> listData) 
    {
        this.title = title;
        this.xLabel = "кол-во элементов";
        this.yLabel = yLabel;
        this.arrayData = arrayData;
        this.listData = listData;

        setSize(new Dimension(800, 600));
    }

    @Override
    protected void paintComponent(Graphics g) {

    	// делает объект g2 для двумерного рисования из объекта g
        Graphics2D g2 = (Graphics2D) g;

        int w = getWidth();
        int h = getHeight();
        int margin = 60;
        
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, w, h);
        
        // оси
        g.setColor(Color.BLACK);
        g2.drawLine(margin, h - margin, w - margin, h - margin); // X начало: (margin, h-margin) конец: (w-margin, h-margin)
        g2.drawLine(margin, margin, margin, h - margin);         // Y

        // заголовок
        g2.setFont(new Font("Arial", Font.BOLD, 18));
        g2.drawString(title, margin, margin - 20);

        // Находим максимум по Y
        long maxY = 0;
        for (long v : arrayData.values()) maxY = Math.max(maxY, v);
        for (long v : listData.values()) maxY = Math.max(maxY, v);

        if (maxY == 0) maxY = 1;

        // Подпись оси Y
        g2.setFont(new Font("Arial", Font.PLAIN, 14));
        g2.drawString(yLabel, margin - 40, margin - 10);

        // Размеры данных
        int n = arrayData.size();
        int stepX = (w - 2 * margin) / (n - 1); // чтобы 10, 100, 1000... были на одном расстоянии

        // Рисуем линии
        drawLine(g2, arrayData, margin, h, stepX, maxY, Color.GREEN, "ArrayList");
        drawLine(g2, listData, margin, h, stepX, maxY, Color.MAGENTA, "LinkedList");

        // Легенда
        g2.setColor(Color.GREEN);
        g2.drawString("ArrayList", w - 160, margin + 10);
        g2.setColor(Color.MAGENTA);
        g2.drawString("LinkedList", w - 160, margin + 30);
    }


    private void drawLine(Graphics2D g2,
                          Map<Integer, Long> data,
                          int margin,
                          int h,
                          int stepX,
                          long maxY,
                          Color color,
                          String name) {

        g2.setColor(color);

        int i = 0;
        int prevX = 0, prevY = 0;

        for (Map.Entry<Integer, Long> entry : data.entrySet()) {
            int x = margin + i * stepX;
            int y = (int) (h - margin - (entry.getValue() * 1.0 / maxY) * (h - 2 * margin));

            // точка
            g2.fillOval(x - 5, y - 5, 10, 10);

            // линия с предыдущей точкой
            if (i > 0) {
                g2.drawLine(prevX, prevY, x, y);
            }

            // подпись X (размер списка)

            g2.drawString(Integer.toString(entry.getKey()), x - 10, h - margin + 20);

            
            prevX = x;
            prevY = y;
            i++;
        }
    }
}
