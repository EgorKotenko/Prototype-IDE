package ide245;


import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import javax.swing.text.Segment;
import javax.swing.text.TabExpander;
import javax.swing.text.Utilities;


/**
 * Стиль =/
 * @author КК
 */
public final class Style {


    public Style() {
        super();
    }

    public Style(Color color, boolean bold, boolean italic) {
        super();
        this.color = color;
        setBold(bold);
        setItalic(italic);
    }

    public Style(Color color, int fontStyle) {
        super();
        this.color = color;
        this.fontStyle = fontStyle;
    }

    public Style(String str) {
        String[] parts = str.split("\\s*,\\s*");
        if (parts.length != 2) {
            throw new IllegalArgumentException("style not correct format: " + str);
        }
        this.color = new Color(Integer.decode(parts[0]));
        this.fontStyle = Integer.decode(parts[1]);
    }

    public static Style getStyle(TokenType t) {
        switch (t) {
            case OPERATOR:         // Этот быдлокод надо менять, вынести
                return new Style(Color.black, 0); // стили в отдельный файл
            case DELIMITER:      // здесь цифры - стиль текста(см. стиль)
                return new Style(Color.black, 0);
            case KEYWORD:
                return new Style(Color.BLUE, 0);
            case TYPE:
                return new Style(Color.BLUE, 0);
            case STRING:
                return new Style(Color.RED, 0);
            case NUMBER:
                return new Style(Color.GRAY, 1);
            case REGEX:
                return new Style(Color.RED, 0);
            case IDENTIFIER:
                return new Style(Color.black, 0);
            case COMMENT:
                return new Style(Color.GREEN, 2);
            case DEFAULT:
                return new Style(Color.black, 0);
            default:
                return new Style(Color.black, 0);
        }
    }

    public boolean isBold() {
        return (fontStyle & Font.BOLD) != 0;
    }

    public void setBold(Boolean bold) {
        if (bold) {
            fontStyle |= Font.BOLD;
        } else {
            int mask = -1 ^ Font.BOLD;
            fontStyle = (fontStyle & (mask));
        }
    }

    public String getColorString() {
        return String.format("0x%06x", color.getRGB() & 0x00ffffff);
    }

    public void setColorString(String color) {
        this.color = Color.decode(color);
    }

    public Boolean isItalic() {
        return (fontStyle & Font.ITALIC) != 0;
    }

    public void setItalic(Boolean italic) {
        if (italic) {
            fontStyle |= Font.ITALIC;
        } else {
            fontStyle = (fontStyle & (-1 ^ Font.ITALIC));
        }
    }

    public int getFontStyle() {
        return fontStyle;
    }

    public Color getColor() {
        return color;
    }

    public int drawText(Segment segment, int x, int y,
            Graphics graphics, TabExpander e, int startOffset) {
        graphics.setFont(graphics.getFont().deriveFont(getFontStyle()));
        FontMetrics fontMetrics = graphics.getFontMetrics();
        int a = fontMetrics.getAscent();
        int h = a + fontMetrics.getDescent();
        int w = Utilities.getTabbedTextWidth(segment, fontMetrics, 0, e, startOffset);
        int rX = x - 1;
        int rY = y - a;
        int rW = w + 2;
        int rH = h;
        if ((getFontStyle() & 0x10) != 0) {
            graphics.setColor(Color.decode("#EEEEEE"));
            graphics.fillRect(rX, rY, rW, rH);
        }
        graphics.setColor(getColor());
        x = Utilities.drawTabbedText(segment, x, y, graphics, e, startOffset);
        if ((getFontStyle() & 0x8) != 0) {
            graphics.setColor(Color.RED);
            graphics.drawRect(rX, rY, rW, rH);
        }
        return x;
    }
    
    
    private Color color;
    private int fontStyle;
}
