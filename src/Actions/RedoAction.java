
package Actions;

import ide245.MDocument;
import javax.swing.JEditorPane;

/**
 *
 * @author KK
 */
public class RedoAction extends MAction {
    
    public RedoAction() {
        super("Redo");
    }

    @Override
    public void mActionPerformed(JEditorPane eP) {
        MDocument doc = (MDocument) eP.getDocument();
        doc.doRedo();
    }
}
