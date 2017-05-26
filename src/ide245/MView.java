package ide245;


import java.awt.*;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.*;


/**
 * Вид на документ
 * @author КК
 */ // Здесь можно будет впиливать модный столбец с нумерацией строк
public class MView extends PlainView {

    
    public MView(Element element) {
        super(element);
    }

    @Override
    protected int drawUnselectedText(Graphics graphics, int x, int y, int p0,
            int p1) {
        setRenderingHits((Graphics2D) graphics);
        Font saveFont = graphics.getFont();
        Color saveColor = graphics.getColor();
        MDocument doc = (MDocument) getDocument();
        Segment segment = getLineBuffer();
        
        try {
            Iterator<Token> i = doc.getTokens(p0, p1);
            int start = p0;
            while (i.hasNext()) {
                Token t = i.next();
                if (start < t.start) {
                    doc.getText(start, t.start - start, segment);
                    x = DEFAULT_STYLE.drawText(segment, x, y, graphics, this, start);
                }
                int l = t.length;
                int s = t.start;
                if (s < p0) {
                    l -= (p0 - s);
                    s = p0;
                }
                if (s + l > p1) {
                    l = p1 - s;
                }
                doc.getText(s, l, segment);
                Style style = Style.getStyle(t.type);
                x = style.drawText(segment, x, y, graphics, this, t.start);
                start = t.end();
            }
            if (start < p1) {
                doc.getText(start, p1 - start, segment);
                x = DEFAULT_STYLE.drawText(segment, x, y, graphics, this, start);
            }
        } catch (BadLocationException ex) {
            log.log(Level.SEVERE, "Requested: " + ex.offsetRequested(), ex);
        } finally {
            graphics.setFont(saveFont);
            graphics.setColor(saveColor);
        }
        return x;
    }

    @Override
    protected int drawSelectedText(Graphics graphics, int x, int y, int p0, int p1)
            throws BadLocationException {
        return super.drawSelectedText(graphics, x, y, p0, p1);
    }

    public static void setRenderingHits(Graphics2D g2d) {
        g2d.addRenderingHints(sysHints);
    }

    @Override
    protected void updateDamage(javax.swing.event.DocumentEvent changes,
            Shape a,
            ViewFactory f) {
        super.updateDamage(changes, a, f);
        java.awt.Component host = getContainer();
        host.repaint();
    }

    static {
        sysHints = null;
        try {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            @SuppressWarnings("unchecked")
            Map<RenderingHints.Key, ?> map = (Map<RenderingHints.Key, ?>) toolkit.getDesktopProperty("awt.font.desktophints");
            sysHints = new RenderingHints(map);
        } catch (Throwable t) {
        }
    }
    
    private static final Logger log = Logger.getLogger(MView.class.getName());
    private Style DEFAULT_STYLE = new Style(Color.BLACK, Font.PLAIN);
    private static RenderingHints sysHints;
}
