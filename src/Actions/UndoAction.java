
package Actions;

import ide245.MDocument;
import javax.swing.JEditorPane;

/**
 *
 * @author Артем
 */
public class UndoAction extends MAction {
    
    public UndoAction() {
        super("Undo");
    }
    
    @Override
    public void mActionPerformed(JEditorPane eP) {
        MDocument doc = (MDocument) eP.getDocument();
        doc.doUndo();
    }
    
}
