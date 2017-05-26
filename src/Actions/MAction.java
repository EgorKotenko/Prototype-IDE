
package Actions;

import java.awt.event.ActionEvent;
import javax.swing.JEditorPane;
import javax.swing.text.TextAction;

/**
 *
 * @author KK
 */
public class MAction extends TextAction {

    public MAction(String string) {
        super(string);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JEditorPane eP = (JEditorPane) e.getSource();
        mActionPerformed(eP);
    }
    
    public void mActionPerformed(JEditorPane eP) {
        
    }    
    
}
