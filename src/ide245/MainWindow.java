package ide245;


import Compiler.Tree;
import compiler.Executer;
import java.awt.Image;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

/**
 * Собственно, графический редактор
 * @author КК
 */
public class MainWindow extends KKframe {
    private static final long serialVersionUID = 1L;
    
    /*public File currentFile = null;
    public boolean isCurrentFileEmpty = true;
    public FileMenu fileMenu = new FileMenu();
    public SaveOptionFrame saveFileFrame = new SaveOptionFrame();*/

    /**
     * Creates new form JFrame
     */
    public MainWindow() {
        
        initComponents();
        setLocationRelativeTo(null);
        mTextPane.setVisible(false);
        fileMenu.mTextPane = mTextPane;
        saveFileFrame.mainFrame = this;
        mTextPane.setEditorKit(new SyntaxKit(fileMenu, this));
        
        mPane = (MTabbedPane)jTabbedPane1;
        
        CaretViewer cv = new CaretViewer(mTextPane);

        
        //mTextPane.setContentType("text/2K");
        Image icon = new ImageIcon("icon.png").getImage();
        setIconImage(icon);
        
    }
    
    @Override
    public void processWindowEvent(WindowEvent ev) {
        if (ev.getID() == WindowEvent.WINDOW_CLOSING)
            mExitItemActionPerformed(null);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }
        
    public void closeTab() {
        fileMenu.closeFile(mPane.getSelectedIndex());
        mPane.close();
        fileMenu.setFile(mPane.getSelectedIndex());
    }
    
    public void close() {
        mExitItemActionPerformed(null);
    }
    
    public boolean isClosing() {
        return isClosing;
    }
    
    public void SetClosing(boolean b) {
        isClosing = b;
    }

    public void saveAs() {
        this.mSaveAsItemActionPerformed(null);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mScrollPane = new javax.swing.JScrollPane();
        mTextPane = new javax.swing.JEditorPane();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new MTabbedPane(mTextPane, null, fileMenu);
        mMenuBar = new javax.swing.JMenuBar();
        mFileMenu = new javax.swing.JMenu();
        mNewItem = new javax.swing.JMenuItem();
        mOpenItem = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        mSaveItem = new javax.swing.JMenuItem();
        mSaveAsItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mExitItem = new javax.swing.JMenuItem();
        mEditMenu = new javax.swing.JMenu();
        mUndoItem = new javax.swing.JMenuItem();
        mRedoItem = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        mCutItem = new javax.swing.JMenuItem();
        mCopyItem = new javax.swing.JMenuItem();
        mPasteItem = new javax.swing.JMenuItem();
        mDeleteItem = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        mSelectItem = new javax.swing.JMenuItem();
        mViewMenu = new javax.swing.JMenu();
        mFScreenItem = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        mCloseTabItem = new javax.swing.JMenuItem();
        mRunMenu = new javax.swing.JMenu();
        mRunItem = new javax.swing.JMenuItem();
        mHelpMenu = new javax.swing.JMenu();
        mHelpItem = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        mAboutItem = new javax.swing.JMenuItem();

        setTitle("Prototype");
        setFocusCycleRoot(false);
        setMinimumSize(new java.awt.Dimension(600, 400));

        mTextPane.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        mTextPane.setToolTipText("");
        mTextPane.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                mTextPaneKeyTyped(evt);
            }
        });
        mScrollPane.setViewportView(mTextPane);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("     ");

        jTabbedPane1.setToolTipText("");
        jTabbedPane1.setMaximumSize(new java.awt.Dimension(32767, 20));

        mMenuBar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        mMenuBar.setMaximumSize(new java.awt.Dimension(180, 32769));

        mFileMenu.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        mFileMenu.setText("File");
        mFileMenu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mFileMenu.setPreferredSize(new java.awt.Dimension(30, 19));

        mNewItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        mNewItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/page.png"))); // NOI18N
        mNewItem.setText("   New   ");
        mNewItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mNewItemActionPerformed(evt);
            }
        });
        mFileMenu.add(mNewItem);

        mOpenItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        mOpenItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/folder.png"))); // NOI18N
        mOpenItem.setText("   Open   ");
        mOpenItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mOpenItemActionPerformed(evt);
            }
        });
        mFileMenu.add(mOpenItem);
        mFileMenu.add(jSeparator2);

        mSaveItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        mSaveItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/disk.png"))); // NOI18N
        mSaveItem.setText("   Save   ");
        mSaveItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mSaveItemActionPerformed(evt);
            }
        });
        mFileMenu.add(mSaveItem);

        mSaveAsItem.setText("   Save as...   ");
        mSaveAsItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mSaveAsItemActionPerformed(evt);
            }
        });
        mFileMenu.add(mSaveAsItem);
        mFileMenu.add(jSeparator1);

        mExitItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        mExitItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/door.png"))); // NOI18N
        mExitItem.setText("   Exit   ");
        mExitItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mExitItemActionPerformed(evt);
            }
        });
        mFileMenu.add(mExitItem);

        mMenuBar.add(mFileMenu);

        mEditMenu.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        mEditMenu.setText("Edit");

        mUndoItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        mUndoItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/arrow_undo.png"))); // NOI18N
        mUndoItem.setText("   Undo   ");
        mUndoItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mUndoItemActionPerformed(evt);
            }
        });
        mEditMenu.add(mUndoItem);

        mRedoItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        mRedoItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/arrow_redo.png"))); // NOI18N
        mRedoItem.setText("   Redo   ");
        mRedoItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mRedoItemActionPerformed(evt);
            }
        });
        mEditMenu.add(mRedoItem);
        mEditMenu.add(jSeparator3);

        mCutItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        mCutItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/cut.png"))); // NOI18N
        mCutItem.setText("   Cut   ");
        mCutItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mCutItemActionPerformed(evt);
            }
        });
        mEditMenu.add(mCutItem);

        mCopyItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        mCopyItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/page_copy.png"))); // NOI18N
        mCopyItem.setText("   Copy   ");
        mCopyItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mCopyItemActionPerformed(evt);
            }
        });
        mEditMenu.add(mCopyItem);

        mPasteItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        mPasteItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/page_white_paste.png"))); // NOI18N
        mPasteItem.setText("   Paste   ");
        mPasteItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mPasteItemActionPerformed(evt);
            }
        });
        mEditMenu.add(mPasteItem);

        mDeleteItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, 0));
        mDeleteItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/cross.png"))); // NOI18N
        mDeleteItem.setText("   Delete  ");
        mDeleteItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mDeleteItemActionPerformed(evt);
            }
        });
        mEditMenu.add(mDeleteItem);
        mEditMenu.add(jSeparator4);

        mSelectItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        mSelectItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/accept.png"))); // NOI18N
        mSelectItem.setText("   Select All   ");
        mSelectItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mSelectItemActionPerformed(evt);
            }
        });
        mEditMenu.add(mSelectItem);

        mMenuBar.add(mEditMenu);

        mViewMenu.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        mViewMenu.setText("View");

        mFScreenItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ENTER, java.awt.event.InputEvent.ALT_MASK));
        mFScreenItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/arrow_out.png"))); // NOI18N
        mFScreenItem.setText("   Full Screen   ");
        mFScreenItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mFScreenItemActionPerformed(evt);
            }
        });
        mViewMenu.add(mFScreenItem);
        mViewMenu.add(jSeparator6);

        mCloseTabItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.ALT_MASK));
        mCloseTabItem.setText("   Close Tab");
        mCloseTabItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mCloseTabItemActionPerformed(evt);
            }
        });
        mViewMenu.add(mCloseTabItem);

        mMenuBar.add(mViewMenu);

        mRunMenu.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        mRunMenu.setText("Run");

        mRunItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F9, java.awt.event.InputEvent.CTRL_MASK));
        mRunItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/application_view_xp_terminal.png"))); // NOI18N
        mRunItem.setText("   Run   ");
        mRunItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mRunItemActionPerformed(evt);
            }
        });
        mRunMenu.add(mRunItem);

        mMenuBar.add(mRunMenu);

        mHelpMenu.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        mHelpMenu.setText("Help");

        mHelpItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, java.awt.event.InputEvent.CTRL_MASK));
        mHelpItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/help.png"))); // NOI18N
        mHelpItem.setText("   View Help   ");
        mHelpMenu.add(mHelpItem);
        mHelpMenu.add(jSeparator5);

        mAboutItem.setText("   About Prototype   ");
        mHelpMenu.add(mAboutItem);

        mMenuBar.add(mHelpMenu);

        setJMenuBar(mMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mScrollPane)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(mScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mSaveAsItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mSaveAsItemActionPerformed
        if (mTextPane.isVisible()) {
            fileMenu.saveFileAs(this, mPane.getSelectedIndex());
            String name = fileMenu.currentFile.getName();
            mPane.setName(name);
            mPane.setUnModified();
        }
    }//GEN-LAST:event_mSaveAsItemActionPerformed

    private void mNewItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mNewItemActionPerformed
        if (mPane.getTabCount() == 99)
            return;
        mTextPane.setVisible(true);
        textPaneIsVisible = true;
        MDocument doc = (MDocument) mTextPane.getDocument();
        Token.doc = doc;
        mPane.create("New", false);
        fileMenu.setFile(mPane.getSelectedIndex());
    }//GEN-LAST:event_mNewItemActionPerformed

    private void mOpenItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mOpenItemActionPerformed
        if (mPane.getTabCount() == 99)
            return;
        mPane.createDocument();
        fileMenu.openFile(this, mPane.getTabCount());
        if (!fileMenu.isEmpty()) {
            mTextPane.setVisible(true);
            textPaneIsVisible = true;
            String name = fileMenu.currentFile.getName();
            mPane.create(name, true);
        }
    }//GEN-LAST:event_mOpenItemActionPerformed
        
    private void mExitItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mExitItemActionPerformed
        isClosing = true;
        if (mTextPane.isVisible()) {
            mPane.setSelectedIndex(mPane.getTabCount() - 1);
            if (mPane.isModified()) {
                saveFileFrame.mainFrame = this;
                saveFileFrame.setVisible(true);
            } else {
                closeTab();
                close();
            }
        } else {
            dispose();
            System.exit(0);
        }            
    }//GEN-LAST:event_mExitItemActionPerformed

    private void mSaveItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mSaveItemActionPerformed
        if (mTextPane.isVisible()) {
            fileMenu.saveFile(this);
            mPane.setUnModified();
        }
    }//GEN-LAST:event_mSaveItemActionPerformed

    private void mFScreenItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mFScreenItemActionPerformed
        setExtendedState(MAXIMIZED_BOTH);
    }//GEN-LAST:event_mFScreenItemActionPerformed

    private void mCutItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mCutItemActionPerformed
        mTextPane.cut();
        mPane.setModified();
    }//GEN-LAST:event_mCutItemActionPerformed

    private void mCopyItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mCopyItemActionPerformed
        mTextPane.copy();
    }//GEN-LAST:event_mCopyItemActionPerformed

    private void mPasteItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mPasteItemActionPerformed
        mTextPane.paste();
        mPane.setModified();
    }//GEN-LAST:event_mPasteItemActionPerformed

    private void mDeleteItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mDeleteItemActionPerformed
        mTextPane.replaceSelection(null);
        mPane.setModified();
    }//GEN-LAST:event_mDeleteItemActionPerformed

    private void mRedoItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mRedoItemActionPerformed
        MDocument doc = (MDocument) mTextPane.getDocument();
        doc.doRedo();
        mPane.setModified();
    }//GEN-LAST:event_mRedoItemActionPerformed

    private void mUndoItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mUndoItemActionPerformed
        MDocument doc = (MDocument) mTextPane.getDocument();
        doc.doUndo();
        mPane.setModified();
    }//GEN-LAST:event_mUndoItemActionPerformed

    private void mSelectItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mSelectItemActionPerformed
        mTextPane.selectAll();
    }//GEN-LAST:event_mSelectItemActionPerformed

    private void mRunItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mRunItemActionPerformed
        File file = new File("temp.cpp");
        if (file.exists()) {
            file.delete();
        }
        MDocument doc = (MDocument) mTextPane.getDocument();
        tree = new Tree(doc.getTokens());
        tree.parse();
        //mTextPane.setText(doc.getTokenAt(0).getString());
        Executer.execute(file.getName());
    }//GEN-LAST:event_mRunItemActionPerformed

    private void mCloseTabItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mCloseTabItemActionPerformed
        if (mPane.isModified() && mTextPane.isVisible()) {
            saveFileFrame.mainFrame = this;
            saveFileFrame.setVisible(true);
        } else if (mTextPane.isVisible()) {
            closeTab();
        }
    }//GEN-LAST:event_mCloseTabItemActionPerformed

    private void mTextPaneKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mTextPaneKeyTyped
        if (!evt.isControlDown() || evt.isAltDown())
            mPane.setModified();
    }//GEN-LAST:event_mTextPaneKeyTyped

    
    
    private Tree tree;
    private MTabbedPane mPane;
    private boolean isClosing = false;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JMenuItem mAboutItem;
    private javax.swing.JMenuItem mCloseTabItem;
    private javax.swing.JMenuItem mCopyItem;
    private javax.swing.JMenuItem mCutItem;
    private javax.swing.JMenuItem mDeleteItem;
    private javax.swing.JMenu mEditMenu;
    private javax.swing.JMenuItem mExitItem;
    private javax.swing.JMenuItem mFScreenItem;
    private javax.swing.JMenu mFileMenu;
    private javax.swing.JMenuItem mHelpItem;
    private javax.swing.JMenu mHelpMenu;
    private javax.swing.JMenuBar mMenuBar;
    private javax.swing.JMenuItem mNewItem;
    private javax.swing.JMenuItem mOpenItem;
    private javax.swing.JMenuItem mPasteItem;
    private javax.swing.JMenuItem mRedoItem;
    private javax.swing.JMenuItem mRunItem;
    private javax.swing.JMenu mRunMenu;
    private javax.swing.JMenuItem mSaveAsItem;
    private javax.swing.JMenuItem mSaveItem;
    private javax.swing.JScrollPane mScrollPane;
    private javax.swing.JMenuItem mSelectItem;
    private javax.swing.JEditorPane mTextPane;
    private javax.swing.JMenuItem mUndoItem;
    private javax.swing.JMenu mViewMenu;
    // End of variables declaration//GEN-END:variables

    private class CaretViewer implements CaretListener {

        public CaretViewer(javax.swing.JEditorPane eP) {
            eP.addCaretListener(this);
            mPane = eP;
        }
        
        @Override
        public void caretUpdate(CaretEvent e) {
            MDocument doc = (MDocument) mPane.getDocument();
            int line = doc.getLineNumberAt(e.getDot()) + 1;
            int pos = e.getDot() - doc.getLineStartOffset(e.getDot());
            jLabel1.setText(String.valueOf(line) + " | "
                    + String.valueOf(pos) + "     ");
        }
        
        private javax.swing.JEditorPane mPane;
        
    }


}
