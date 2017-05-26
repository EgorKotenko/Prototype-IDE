package components;

import ide245.MDocument;
import ide245.MView;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;

public class LinesNumeror extends JPanel
        implements CaretListener, DocumentListener, PropertyChangeListener, Component {

    private final static int HEIGHT = Integer.MAX_VALUE - 1000000;
    private JEditorPane editor;
    private int minimumDisplayDigits = 2;
    private int lastDigits;
    private int lastHeight;
    private int lastLine;
    private String numbersFormat = "%3d";
    private Color currentLineColor;

    public JScrollPane getScrollPane(JTextComponent editorPane) {
        Container p = editorPane.getParent();
        while (p != null) {
            if (p instanceof JScrollPane) {
                return (JScrollPane) p;
            }
            p = p.getParent();
        }
        return null;
    }

    @Override
    public void install(final JEditorPane editor) {
        this.editor = editor;

        setForeground(Color.BLACK);
        setBackground(Color.getColor("light_blue"));
        setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 2));
        currentLineColor = Color.LIGHT_GRAY;

        setFont(editor.getFont());

        editor.getDocument().addDocumentListener(this);
        editor.addCaretListener(this);
        editor.addPropertyChangeListener(this);
        JScrollPane sp = getScrollPane(editor);
        sp.setRowHeaderView(this);
        setVisible(false);
    }

    @Override
    public void deinstall(JEditorPane editor) {
        this.editor.getDocument().removeDocumentListener(this);
        editor.removeCaretListener(this);
        editor.removePropertyChangeListener(this);
        JScrollPane sp = getScrollPane(editor);
        if (sp != null) {
            editor.getDocument().removeDocumentListener(this);
            sp.setRowHeaderView(null);
        }
    }

    public int getMinimumDisplayDigits() {
        return minimumDisplayDigits;
    }

    public void setMinimumDisplayDigits(int minimumDisplayDigits) {
        this.minimumDisplayDigits = minimumDisplayDigits;
        setPreferredWidth();
    }

    private void setPreferredWidth() {
        MDocument doc = (MDocument) editor.getDocument();
        int lines = doc.getLineCount();
        int digits = Math.max(String.valueOf(lines).length(), minimumDisplayDigits);

        if (lastDigits != digits) {
            lastDigits = digits;
            numbersFormat = "%" + digits + "d";
            FontMetrics fontMetrics = getFontMetrics(getFont());
            int width = fontMetrics.charWidth('0') * digits;
            Insets insets = getInsets();
            int preferredWidth = insets.left + insets.right + width;

            Dimension d = getPreferredSize();
            d.setSize(preferredWidth, HEIGHT);
            setPreferredSize(d);
            setSize(d);

        }
    }

    @Override
    public void paintComponent(Graphics g) {
            
        super.paintComponent(g);

        MDocument doc = (MDocument) editor.getDocument();

        FontMetrics fontMetrics = editor.getFontMetrics(editor.getFont());
        Insets insets = getInsets();
        int currentLine = -1;
        currentLine = doc.getLineNumberAt(editor.getCaretPosition()) + 1;
        int lh = fontMetrics.getHeight();
        int maxLines = doc.getLineCount();
        if (!editor.isVisible()) {
            maxLines = 0;
            currentLine = 0;
        }
        MView.setRenderingHits((Graphics2D) g);

        for (int line = 1; line <= maxLines; line++) {
            String lineNumber = String.format(numbersFormat, line);
            int y = line * lh;
            if (line == currentLine) {
                g.setColor(currentLineColor);
                g.fillRect(0, y - lh + fontMetrics.getDescent() - 1, getWidth(), lh);
                g.setColor(getForeground());
                g.drawString(lineNumber, insets.left, y);
            } else {
                g.drawString(lineNumber, insets.left, y);
            }
        }
    }

    @Override
    public void caretUpdate(CaretEvent e) {
        if (editor.isVisible())
            setVisible(true);
        else
            setVisible(false);
        int caretPosition = editor.getCaretPosition();
        Element root = editor.getDocument().getDefaultRootElement();
        int currentLine = root.getElementIndex(caretPosition);

        if (lastLine != currentLine) {
            repaint();
            lastLine = currentLine;
        }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        documentChanged();
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        documentChanged();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        documentChanged();
    }

    private void documentChanged() {

        SwingUtilities.invokeLater(new Runnable() {

            
            @Override
            public void run() {
                int preferredHeight = editor.getPreferredSize().height;

                if (lastHeight != preferredHeight) {
                    setPreferredWidth();
                    repaint();
                    lastHeight = preferredHeight;
                }
            }
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("document")) {
            if (evt.getOldValue() instanceof MDocument) {
                MDocument MDocument = (MDocument) evt.getOldValue();
                MDocument.removeDocumentListener(this);
            }
            if (evt.getNewValue() instanceof MDocument) {
                MDocument MDocument = (MDocument) evt.getNewValue();
                MDocument.addDocumentListener(this);
                setPreferredWidth();
                repaint();
            }
        } else if (evt.getNewValue() instanceof Font) {
            setPreferredWidth();
            repaint();
        }
    }
}
