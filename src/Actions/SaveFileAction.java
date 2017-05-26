
package Actions;

import ide245.FileMenu;
import javax.swing.JEditorPane;

/**
 * 
 * @author KK
 */
public class SaveFileAction extends MAction {
    
    FileMenu menu;
    
    public SaveFileAction(FileMenu fMenu) {
        super("Save");
        menu = fMenu;      
    }

    @Override
    public void mActionPerformed(JEditorPane eP) {
        //menu.saveFile();
    }
}
