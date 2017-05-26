package ide245;


import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.event.DocumentEvent;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.*;
import javax.swing.undo.UndoManager;

/**
 * Представление нашего текста (отвечает за подсветку например)
 * @author КК
 */
public class MDocument extends PlainDocument {

    /**
     * Создание документа
     */
    public MDocument() {
        super();
        undo = new UndoManager();
        
        putProperty(MDocument.tabSizeAttribute, 1);
        
        addUndoableEditListener(new UndoableEditListener() {

            public void undoableEditHappened(UndoableEditEvent evt) {
                undo.addEdit(evt.getEdit());
            }
        });
    }

    /**
     * Парсинг (передается лексеру)
     */
    private void parse() {
        List<Token> toks = new ArrayList<Token>(getLength() / 10);
        try {
            Segment seg = new Segment();
            getText(0, getLength(), seg);
            lexer.parse(seg, 0, toks);
        } catch (BadLocationException ex) {
            log.log(Level.SEVERE, null, ex);
        } finally {
            tokens = toks;
        }
    }

    @Override
    protected void fireChangedUpdate(DocumentEvent e) {
        parse();
        super.fireChangedUpdate(e);
    }

    @Override
    protected void fireInsertUpdate(DocumentEvent e) {
        parse();
        super.fireInsertUpdate(e);
    }

    @Override
    protected void fireRemoveUpdate(DocumentEvent e) {
        parse();
        super.fireRemoveUpdate(e);
    }

    /**
     * Замена токена
     * @param token
     * @param replacement 
     */
    public void replaceToken(Token token, String replacement) {
        try {
            replace(token.start, token.length, replacement, null);
        } catch (BadLocationException ex) {
            log.log(Level.WARNING, "unable to replace token: " + token, ex);
        }
    }

    /**
     * Итератор для обхода наших токенов (снова кэп)
     */
    private class TokenIterator implements ListIterator<Token> {

        int start;
        int end;
        int ndx = 0;

        private TokenIterator(int start, int end) {
            this.start = start;
            this.end = end;
            if (tokens != null && !tokens.isEmpty()) {
                Token token = new Token(TokenType.COMMENT, start, end - start);
                ndx = Collections.binarySearch((List) tokens, token);
                if (ndx < 0) {
                    ndx = (-ndx - 1 - 1 < 0) ? 0 : (-ndx - 1 - 1);
                    Token t = tokens.get(ndx);
                    if (t.end() <= start) {
                        ndx++;
                    }

                }
            }
        }

        @Override
        public boolean hasNext() {
            if (tokens == null) {
                return false;
            }
            if (ndx >= tokens.size()) {
                return false;
            }
            Token t = tokens.get(ndx);
            if (t.start >= end) {
                return false;
            }
            return true;
        }

        @Override
        public Token next() {
            return tokens.get(ndx++);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasPrevious() {
            if (tokens == null) {
                return false;
            }
            if (ndx <= 0) {
                return false;
            }
            Token t = tokens.get(ndx);
            if (t.end() <= start) {
                return false;
            }
            return true;
        }

        @Override
        public Token previous() {
            return tokens.get(ndx--);
        }

        @Override
        public int nextIndex() {
            return ndx + 1;
        }

        @Override
        public int previousIndex() {
            return ndx - 1;
        }

        @Override
        public void set(Token e) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void add(Token e) {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Итератор токенов для куска текста
     * @param start
     * @param end
     * @return 
     */
    public Iterator<Token> getTokens(int start, int end) {
        return new TokenIterator(start, end);
    }
    
    public List<Token> getTokens() {
        return tokens;
    }

    public Token getTokenAt(int pos) {
        if (tokens == null || tokens.isEmpty() || pos > getLength()) {
            return null;
        }
        Token tok = null;
        Token tKey = new Token(TokenType.DEFAULT, pos, 1);
        @SuppressWarnings("unchecked")
        int ndx = Collections.binarySearch((List) tokens, tKey);
        if (ndx < 0) {
            ndx = (-ndx - 1 - 1 < 0) ? 0 : (-ndx - 1 - 1);
            Token t = tokens.get(ndx);
            if ((t.start <= pos) && (pos <= t.end())) {
                tok = t;
            }
        } else {
            tok = tokens.get(ndx);
        }
        return tok;
    }

    public Token getWordAt(int offs, Pattern p) {
        Token word = null;
        try {
            Element line = getParagraphElement(offs);
            if (line == null) {
                return word;
            }
            int lineStart = line.getStartOffset();
            int lineEnd = Math.min(line.getEndOffset(), getLength());
            Segment seg = new Segment();
            getText(lineStart, lineEnd - lineStart, seg);
            if (seg.count > 0) {
                Matcher m = p.matcher(seg);
                int o = offs - lineStart;
                while (m.find()) {
                    if (m.start() <= o && o <= m.end()) {
                        word = new Token(TokenType.DEFAULT, m.start() + lineStart, m.end() - m.start());
                        break;
                    }
                }
            }
        } catch (BadLocationException ex) {
            Logger.getLogger(MDocument.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return word;
        }
    }

    public Token getNextToken(Token tok) {
        int n = tokens.indexOf(tok);
        if ((n >= 0) && (n < (tokens.size() - 1))) {
            return tokens.get(n + 1);
        } else {
            return null;
        }
    }

    public Token getPrevToken(Token tok) {
        int n = tokens.indexOf(tok);
        if ((n > 0) && (!tokens.isEmpty())) {
            return tokens.get(n - 1);
        } else {
            return null;
        }
    }

    public Token getPairFor(Token t) {
        if (t == null || t.pairValue == 0) {
            return null;
        }
        Token p = null;
        int ndx = tokens.indexOf(t);
        int w = t.pairValue;
        int direction = (t.pairValue > 0) ? 1 : -1;
        boolean done = false;
        int v = Math.abs(t.pairValue);
        while (!done) {
            ndx += direction;
            if (ndx < 0 || ndx >= tokens.size()) {
                break;
            }
            Token current = tokens.get(ndx);
            if (Math.abs(current.pairValue) == v) {
                w += current.pairValue;
                if (w == 0) {
                    p = current;
                    done = true;
                }
            }
        }

        return p;
    }
    
    public void doUndo() {
        if (undo.canUndo()) {
            undo.undo();
            parse();
        }
    }

    public void doRedo() {
        if (undo.canRedo()) {
            undo.redo();
            parse();
        }
    }

    public Matcher getMatcher(Pattern pattern) {
        return getMatcher(pattern, 0, getLength());
    }

    public Matcher getMatcher(Pattern pattern, int start) {
        return getMatcher(pattern, start, getLength() - start);
    }

    public Matcher getMatcher(Pattern pattern, int start, int length) {
        Matcher matcher = null;
        if (getLength() == 0) {
            return null;
        }
        if (start >= getLength()) {
            return null;
        }
        try {
            if (start < 0) {
                start = 0;
            }
            if (start + length > getLength()) {
                length = getLength() - start;
            }
            Segment seg = new Segment();
            getText(start, length, seg);
            matcher = pattern.matcher(seg);
        } catch (BadLocationException ex) {
            log.log(Level.SEVERE, "Requested offset: " + ex.offsetRequested(), ex);
        }
        return matcher;
    }

    public String getLineAt(int pos) throws BadLocationException {
        Element e = getParagraphElement(pos);
        Segment seg = new Segment();
        getText(e.getStartOffset(), e.getEndOffset() - e.getStartOffset(), seg);
        char last = seg.last();
        if (last == '\n' || last == '\r') {
            seg.count--;
        }
        return seg.toString();
    }

    public void removeLineAt(int pos)
            throws BadLocationException {
        Element e = getParagraphElement(pos);
        remove(e.getStartOffset(), getElementLength(e));
    }

    public void replaceLineAt(int pos, String newLines)
            throws BadLocationException {
        Element e = getParagraphElement(pos);
        replace(e.getStartOffset(), getElementLength(e), newLines, null);
    }

    private int getElementLength(Element e) {
        int end = e.getEndOffset();
        if (end >= (getLength() - 1)) {
            end--;
        }
        return end - e.getStartOffset();
    }

    public synchronized String getUncommentedText(int aStart, int anEnd) {
        readLock();
        StringBuilder result = new StringBuilder();
        Iterator<Token> iter = getTokens(aStart, anEnd);
        while (iter.hasNext()) {
            Token t = iter.next();
            if (!TokenType.isComment(t)) {
                result.append(t.getText());
            }
        }
        readUnlock();
        return result.toString();
    }

    public int getLineStartOffset(int pos) {
        return getParagraphElement(pos).getStartOffset();
    }

    public int getLineEndOffset(int pos) {
        int end = 0;
        end = getParagraphElement(pos).getEndOffset();
        if (end >= getLength()) {
            end = getLength();
        }
        return end;
    }

    public int getLineCount() {
        Element e = getDefaultRootElement();
        int cnt = e.getElementCount();
        return cnt;
    }

    public int getLineNumberAt(int pos) {
        int lineNr = getDefaultRootElement().getElementIndex(pos);
        return lineNr;
    }

    @Override
    public String toString() {
        return "SyntaxDocument(" + lexer + ", " + ((tokens == null) ? 0 : tokens.size()) + " tokens)@"
                + hashCode();
    }

    @Override
    public void replace(int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        remove(offset, length);
        insertString(offset, text, attrs);
    }

    /**
     * Append the given string to the text of this document.
     *
     * @param str
     * @return this document
     */
    public MDocument append(String str) {
        try {
            insertString(getLength(), str, null);
        } catch (BadLocationException ex) {
            log.log(Level.WARNING, "Error appending str", ex);
        }
        return this;
    }

    private Lexer lexer = new Lexer();
    private UndoManager undo;
    private List<Token> tokens;
    private static final Logger log = Logger.getLogger(MDocument.class.getName());
}
