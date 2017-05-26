/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ide245;

import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

/**
 *
 * @author Артем
 */
public class MTabbedPane extends JTabbedPane implements CaretListener {
    
    public MTabbedPane(JEditorPane eP, ImageIcon i, FileMenu m) {
        super();
        mMenu = m;
        mPane = eP;
        icon = i;
        for (int k = 0; k < 100; k++)
            isModified[k] = false;
        this.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }
    
    public void create(String name, boolean isOpened) {
        if (getTabCount() == 0)
            newCreated = 1;
        index++;
        isModified[index] = false;
        addTab(name, icon, new JPanel(), name);
        this.setToolTipTextAt(index, null);
        if (name.equals("New")) {
            name += String.valueOf(newCreated) + ".kk";
            newCreated++;
        }
        if (!isOpened)
            tabs[index] = new MDocument();
        assignTabs(index);
        setSelectedIndex(index);
        setName(name);
    }
    
    public void createDocument() {
        int count = getTabCount();
        tabs[count] = new MDocument();
        mPane.setDocument(tabs[count]);
        Token.doc = tabs[count];
    }
    
    @Override
    public void setSelectedIndex(int _index) {
        //if (index > 0)
          //  tabs[getSelectedIndex()] = (MDocument) mPane.getDocument();
        super.setSelectedIndex(_index);
        if (tabs[_index] == null)
            tabs[_index] = new MDocument();
        Token.doc = tabs[_index];
        mPane.setDocument(tabs[_index]);
        currentlySaved = false;
        mMenu.setFile(_index);
    }
    
    @Override
    public void setName(String name) {
        setTitleAt(getSelectedIndex(), name);
    }
    
    public void setModified() {
        int index = getSelectedIndex();
        String name = getTitleAt(index);
        if (!isModified[index]) {
            setName(name + "*");
            isModified[index] = true;
        }
    }
    
    public void setUnModified() {
        int index = getSelectedIndex();
        String name = getTitleAt(index);
        if (isModified[index] && name.charAt(name.length() - 1) == '*') {
            setName(name.substring(0, name.length() - 1));
            isModified[index] = false;
        }
    }
    
    public boolean isModified() {
        return isModified[getSelectedIndex()];
    }
    
    public void close() {
        int i = getSelectedIndex();
        mPane.setText(null);
        tabs[i] = null;
        if (getTabCount() == 1)
            mPane.setVisible(false);
        else if (i == getTabCount() - 1)
            setSelectedIndex(i - 1);
        else
            setSelectedIndex(i + 1);
        remove(i);
        index--;
        assignTabs(index);
    }
    
    @Override
    public void remove(int i) {
        super.remove(i);
        for (int k = i; k < index; k++) {
            tabs[k] = tabs[k + 1];
            isModified[k] = isModified[k + 1];
        }
    }
    
    private void assignTabs(int value) {
        if (value == 0)
            setMnemonicAt(0, KeyEvent.VK_1);
        else if (value == 1) {
            assignTabs(0);
            setMnemonicAt(1, KeyEvent.VK_2);
        } else if (value == 2) {
            assignTabs(1);
            setMnemonicAt(2, KeyEvent.VK_3);
        } else if (value == 3) {
            assignTabs(2);
            setMnemonicAt(3, KeyEvent.VK_4);
        } else if (value == 4) {
            assignTabs(3);
            setMnemonicAt(4, KeyEvent.VK_5);
        } else if (value == 5) {
            assignTabs(4);
            setMnemonicAt(5, KeyEvent.VK_6);
        } else if (value == 6) {
            assignTabs(5);
            setMnemonicAt(6, KeyEvent.VK_7);
        } else if (value == 7) {
            assignTabs(6);
            setMnemonicAt(7, KeyEvent.VK_8);
        } else if (value == 8) {
            assignTabs(7);
            setMnemonicAt(8, KeyEvent.VK_9);
        }
    }
    
    private FileMenu mMenu;
    private JEditorPane mPane;
    private ImageIcon icon;
    private MDocument[] tabs = new MDocument[100];
    private boolean[] isModified = new boolean[100];
    private int index = -1;
    private boolean currentlySaved = false;
    private int newCreated = 1;

    @Override
    public void caretUpdate(CaretEvent e) {
        setModified();
    }
}
