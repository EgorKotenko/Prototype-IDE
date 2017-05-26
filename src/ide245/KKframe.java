/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ide245;

/**
 * Шаблон для всех окон
 * @author KK
 */
public class KKframe extends javax.swing.JFrame{
    private static final long serialVersionUID = 1L;    
    public FileMenu fileMenu = new FileMenu();
    public boolean textPaneIsVisible = false;
    public SaveOptionFrame saveFileFrame = new SaveOptionFrame();
 

}
