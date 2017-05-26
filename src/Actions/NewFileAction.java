
package Actions;

import ide245.FileMenu;
import javax.swing.JEditorPane;

/**
 * 
 * @author KK
 */
public class NewFileAction extends MAction {
    
    FileMenu menu;
    
    public NewFileAction(FileMenu fMenu) {
        super("NewFile");
        menu = fMenu;      
    }

    @Override
    public void mActionPerformed(JEditorPane eP) {
        //menu.newFile();
    }
}
