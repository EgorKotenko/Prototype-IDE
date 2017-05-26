
package Actions;

import ide245.MainWindow;
import javax.swing.JEditorPane;
        
/**
 * 
 * @author KK
 */
public class FullScreenAction extends MAction {
    
    MainWindow frame;
    
    public FullScreenAction(MainWindow window) {
        super("FullScreen");
        frame = window;      
    }

    @Override
    public void mActionPerformed(JEditorPane eP) {
        frame.setExtendedState(2);
    }
}
