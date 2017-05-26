
package components;


import javax.swing.JEditorPane;

/**
 * Интерфейс для компонент JEditorPane (подсветка токенов, например)
 * @author КК
 */
public interface Component {

    /**
     * 
     * @param editor поле, в которое вкручиваем компоненту
     */
    public void install(JEditorPane editor);

    /**
     * 
     * @param editor угадайте сами
     */
    public void deinstall(JEditorPane editor);

}
