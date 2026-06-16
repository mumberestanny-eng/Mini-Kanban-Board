import javax.swing.*;
import java.awt.*;

public class RoundedButton extends JButton {
    private int radius;

    public RoundedButton(String text, int radius) {
        super(text);
        this.radius = radius;
        setContentAreaFilled(false); // Stops Swing from drawing a default rectangle background
        setBorderPainted(false);     // Stops Swing from drawing the default sharp border
        setFocusPainted(false);      // Stops the ugly inner text focus ring
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Change color dynamically when hovering or clicking!
        if (getModel().isPressed()) {
            g2d.setColor(getBackground().darker());
        } else if (getModel().isRollover()) {
            g2d.setColor(getBackground().brighter());
        } else {
            g2d.setColor(getBackground());
        }

        // Fill the smooth rounded background
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        g2d.dispose();

        // Let Swing print the text and icon over our new background safely
        super.paintComponent(g);
    }
}
