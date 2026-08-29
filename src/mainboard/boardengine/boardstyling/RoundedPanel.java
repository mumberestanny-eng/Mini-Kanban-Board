package mainboard.boardengine.boardstyling;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class RoundedPanel extends JPanel {
    private int cornerRadius = 30;
    private int borderThickness = 2;

    public RoundedPanel(int radius, int thickness) {
        this.cornerRadius = radius;
        this.borderThickness = thickness;

        setOpaque(false);
        setBorder(null);
    }


    public RoundedPanel() {
        this(30, 2);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, width, height, cornerRadius, cornerRadius);

        g2.setColor(getForeground());
        g2.setStroke(new BasicStroke(borderThickness));
        g2.drawRoundRect(
                borderThickness / 2,
                borderThickness / 2,
                width - borderThickness,
                height - borderThickness,
                cornerRadius,
                cornerRadius
        );

        g2.dispose();
    }
}

