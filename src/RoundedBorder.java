import javax.swing.border.AbstractBorder;
import java.awt.*;

public class RoundedBorder extends AbstractBorder {
    private Color color;
    private int thickness;
    private int radii;

    public RoundedBorder(Color color, int thickness, int radii) {
        this.color = color;
        this.thickness = thickness;
        this.radii = radii;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(thickness));
        // Draw the border slightly inside the boundaries to prevent clipping
        g2d.drawRoundRect(x + thickness/2, y + thickness/2, width - thickness, height - thickness, radii, radii);
        g2d.dispose();
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(this.radii/2, this.radii/2, this.radii/2, this.radii/2);
    }
}