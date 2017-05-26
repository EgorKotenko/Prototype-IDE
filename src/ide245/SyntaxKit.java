package ide245;



import Actions.*;
import components.Component;
import components.LinesNumeror;
import components.TokenMarker;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.logging.Logger;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JEditorPane;
import javax.swing.KeyStroke;
import javax.swing.text.*;


/**
 * Кит который всем заправляет
 * @author КК
 */
public class SyntaxKit extends DefaultEditorKit implements ViewFactory {

    FileMenu menu;
    MainWindow frame;
    
    public SyntaxKit(FileMenu fMenu, MainWindow window) {
        super();
        menu = fMenu;
        frame = window;
    }

    public void addComponents(JEditorPane eP) {
        installComponent(eP, new TokenMarker());
        installComponent(eP, new LinesNumeror());
    }
    
    public void addActions(JEditorPane eP) {
        InputMap imap = new InputMap();
        imap.setParent(eP.getInputMap());
        ActionMap amap = new ActionMap();
        amap.setParent(eP.getActionMap());
        
        // пихаем действия аля быдлокод, надо автоматизировать 
        // и вынести действия в файл
        UndoAction undo = new UndoAction();
        amap.put("Undo", undo);
        KeyStroke KS = KeyStroke.getKeyStroke("control Z");
        imap.put(KS, "Undo");
        
        RedoAction redo = new RedoAction();
        amap.put("Redo", redo);
        KS = KeyStroke.getKeyStroke("control Y");
        imap.put(KS, "Redo");
        
        /*SaveFileAction save = new SaveFileAction(menu);
        amap.put("Save", save);
        KS = KeyStroke.getKeyStroke("control S");
        imap.put(KS, "Save");
        
        OpenFileAction open = new OpenFileAction(menu, frame);
        amap.put("Open", open);
        KS = KeyStroke.getKeyStroke("control O");
        imap.put(KS, "Open");
        
        NewFileAction newFile = new NewFileAction(menu);
        amap.put("NewFile", newFile);
        KS = KeyStroke.getKeyStroke("control N");
        imap.put(KS, "NewFile");
        
        ExitAction exit = new ExitAction(menu, frame);
        amap.put("Exit", exit);
        KS = KeyStroke.getKeyStroke("alt F4");
        imap.put(KS, "Exit");
        
        FullScreenAction fscreen = new FullScreenAction(frame);
        amap.put("FullScreen", fscreen);
        KS = KeyStroke.getKeyStroke("alt enter");
        imap.put(KS, "FullScreen");*/
        
        // ставим карты
        eP.setActionMap(amap);
        eP.setInputMap(JTextComponent.WHEN_FOCUSED, imap);
    }

    public void installComponent(JEditorPane pane, Component comp) {

        comp.install(pane);
        if (editorComponents.get(pane) == null) {
            editorComponents.put(pane, new ArrayList<Component>());
        }
        editorComponents.get(pane).add(comp);
        
    }

    @Override
    public ViewFactory getViewFactory() {
        return this;
    }

    @Override
    public View create(Element element) {
        return new MView(element);
    }

    @Override
    public void install(JEditorPane editorPane) {
        super.install(editorPane);
        Font font = DEFAULT_FONT;
        editorPane.setFont(font);
        editorPane.setCaretColor(Color.black);
        addComponents(editorPane);
        addActions(editorPane);
    }

    @Override
    public void deinstall(JEditorPane editorPane) {
        List<Component> l = editorComponents.get(editorPane);
        for (Component c : editorComponents.get(editorPane)) {
            c.deinstall(editorPane);
        }
        editorComponents.clear();
    }

    @Override
    public Document createDefaultDocument() {
        return new MDocument();
    }

    @Override
    public String getContentType() {
        return "text/2K";
    }
    
    private static Font DEFAULT_FONT;
    private static final Logger LOG = Logger.getLogger("SyntaxKit");
    private Map<JEditorPane, List<Component>> editorComponents =
            new WeakHashMap<JEditorPane, List<Component>>();
}
