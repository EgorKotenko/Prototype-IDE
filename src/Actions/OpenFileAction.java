
package Actions;

import ide245.FileMenu;
import ide245.MainWindow;
import javax.swing.JEditorPane;

/**
 * 
 * @author KK
 */
public class OpenFileAction extends MAction {
    
    FileMenu menu;
    MainWindow frame;
    
    public OpenFileAction(FileMenu fMenu, MainWindow window) {
        super("Open");
        menu = fMenu; 
        frame = window;
    }

    @Override
    public void mActionPerformed(JEditorPane eP) {
        //menu.openFile(frame);
    }
}
