
package components;


import ide245.MDocument;
import ide245.Token;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;

/**
 * Класс, предоставляющий кучу статических методов для маркировки текста
 * @author КК
 */
public class Markers {

    
    /**
     * Стандартный маркировщик
     */
    public static class SimpleMarker extends DefaultHighlighter.DefaultHighlightPainter {

        public SimpleMarker(Color color) {
            super(color);
        }
    }

    /**
     * Удаление только наших маркировщиков
     *
     * @param component компонента, с которой удаляем
     * @param marker выкручиваемый маркер
     */
    public static void removeMarkers(JTextComponent component, SimpleMarker marker) {
        Highlighter hilite = component.getHighlighter();
        Highlighter.Highlight[] hilites = hilite.getHighlights();

        for (int i = 0; i < hilites.length; i++) {
            if (hilites[i].getPainter() instanceof SimpleMarker) {
                SimpleMarker hMarker = (SimpleMarker) hilites[i].getPainter();
                if (marker == null || hMarker.equals(marker)) {
                    hilite.removeHighlight(hilites[i]);
                }
            }
        }
    }

    /**
     * Удаление уащпе всех маркеров из EditorPane
     *
     * @param editorPane
     */
    public static void removeMarkers(JTextComponent editorPane) {
        removeMarkers(editorPane, null);
    }

    /**
     * Подсвечивание данного токена на данной панели данным маркером
     *
     * @param pane
     * @param token
     * @param marker
     */
    public static void markToken(JTextComponent pane, Token token, SimpleMarker marker) {
        markText(pane, token.start, token.end(), marker);
    }

    /**
     * Подсветка куска текста
     *
     * @param pane
     * @param start
     * @param end
     * @param marker
     */
    public static void markText(JTextComponent pane, int start, int end, SimpleMarker marker) {
        try {
            Highlighter hiliter = pane.getHighlighter();
            int selStart = pane.getSelectionStart();
            int selEnd = pane.getSelectionEnd();
            
            if (selStart == selEnd || end < selStart || start > selStart) {
                hiliter.addHighlight(start, end, marker);
                return;
            }
            if (selStart > start && selStart < end) {
                hiliter.addHighlight(start, selStart, marker);
            }
            
            if (selEnd > start && selEnd < end) {
                hiliter.addHighlight(selEnd, end, marker);
            }

        } catch (BadLocationException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Подсветка всего текста, подходящего под шаблон
     *
     * @param pane 
     * @param pattern 
     * @param marker 
     */
    public static void markAll(JTextComponent pane, Pattern pattern, SimpleMarker marker) {
        MDocument sDoc = (MDocument) pane.getDocument();
        if (sDoc == null || pattern == null) {
            return;
        }
        Matcher matcher = sDoc.getMatcher(pattern);
        if (matcher == null) {
            return;
        }
        while (matcher.find()) {
            markText(pane, matcher.start(), matcher.end(), marker);
        }
    }
    
    private static final Logger LOG = Logger.getLogger(Markers.class.getName());
}
