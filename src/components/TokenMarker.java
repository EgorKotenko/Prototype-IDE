
package components;

import ide245.MDocument;
import ide245.Token;
import ide245.TokenType;
import java.awt.Color;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;
import javax.swing.JEditorPane;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.Document;


/**
 * Подсветка токенов
 * @author КК
 */
public class TokenMarker implements Component, CaretListener {


    /**
     * Конструктор
     */
    public TokenMarker() {
        tokenTypes.add(TokenType.COMMENT); // Быдлокод менять ннннада
        tokenTypes.add(TokenType.COMMENT2);
        tokenTypes.add(TokenType.DEFAULT);
        tokenTypes.add(TokenType.DELIMITER);
        tokenTypes.add(TokenType.IDENTIFIER);
        tokenTypes.add(TokenType.KEYWORD);
        tokenTypes.add(TokenType.NUMBER);
        tokenTypes.add(TokenType.OPERATOR);
        tokenTypes.add(TokenType.REGEX);
        tokenTypes.add(TokenType.REGEX2);
        tokenTypes.add(TokenType.STRING);
        tokenTypes.add(TokenType.TYPE);
        
    }

    @Override
    public void caretUpdate(CaretEvent e) {
        markTokenAt(e.getDot());
    }

    /**
     * Подсветка токена на определенной позиции
     * @param pos 
     */
    public void markTokenAt(int pos) {
        Document d = pane.getDocument();
        MDocument doc;
        if (d instanceof MDocument) {
            doc = (MDocument) d;
        } else {
            doc = null;
        }
        if (doc != null) {
            Token token = doc.getTokenAt(pos);
            removeMarkers();
            if (token != null && tokenTypes.contains(token.type)) {
                addMarkers(token);
            }
        }
    }

    /**
     * Выпиливание всех маркеров
     */
    public void removeMarkers() {
        Markers.removeMarkers(pane, marker);
    }

    /**
     * Подсветка токенов по шаблону
     * @param tok
     */
    void addMarkers(Token tok) {
        MDocument sDoc = (MDocument) pane.getDocument();
        sDoc.readLock();
        String text = tok.getText().toString();
        Iterator<Token> it = sDoc.getTokens(0, sDoc.getLength());
        while (it.hasNext()) {
            Token nextToken = it.next();
            String nextText = nextToken.getText().toString();
            if (text.equals(nextText)) {
                Markers.markToken(pane, nextToken, marker);
            }

        }
        sDoc.readUnlock();
    }

    @Override
    public void install(JEditorPane editor) {
        this.pane = editor;
        pane.addCaretListener(this);
        markTokenAt(editor.getCaretPosition());
    }

    @Override
    public void deinstall(JEditorPane editor) {
        removeMarkers();
        pane.removeCaretListener(this);
    }
    
    private static final Logger LOG = Logger.getLogger(TokenMarker.class.getName());
    private static final Color DEFAULT_COLOR = new Color(0xFFEE66);
    private JEditorPane pane;
    private Set<TokenType> tokenTypes = new HashSet<TokenType>();
    private Markers.SimpleMarker marker = new Markers.SimpleMarker(DEFAULT_COLOR);
}
