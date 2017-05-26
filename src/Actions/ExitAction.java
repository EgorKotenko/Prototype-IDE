
package Actions;

import ide245.FileMenu;
import ide245.MainWindow;
import javax.swing.JEditorPane;

/**
 * 
 * @author KK
 */
public class ExitAction extends MAction {
    
    FileMenu menu;
    MainWindow frame;
    
    public ExitAction(FileMenu fMenu, MainWindow window) {
        super("Exit");
        menu = fMenu; 
        frame = window;      
    }

    @Override
    public void mActionPerformed(JEditorPane eP) {
        if (frame.textPaneIsVisible) {
            frame.saveFileFrame.setVisible(true);
            frame.saveFileFrame.mainFrame = frame;
        } else {
            frame.dispose();
        }
    }
}
