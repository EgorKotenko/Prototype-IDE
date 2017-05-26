/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ide245;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 * Работа с файлами
 * @author КК
 */
public class FileMenu {

    public class myFileFilter extends javax.swing.filechooser.FileFilter {
      
        public String ext, description;

        @Override
        public String getDescription() {
            return description;
        }

        myFileFilter(String ext, String description) {
            this.ext = ext;
        }
        
        //В этом методе может быть любая проверка файла
        @Override
        public boolean accept(File f) {
            if(f != null) {
                if(f.isDirectory()) {
                    return true;
                }
                return f.toString().endsWith(ext);
            }
            return false;
        }
    }
    
    public File currentFile = null;
    public javax.swing.JEditorPane mTextPane;
    
    public boolean isEmpty() {
        return currentFile == null;
    }
    
    
    /**
    * Сохранить файл как..
    */
    public void saveFileAs(KKframe frame, int index) {
        JFileChooser filesave = new JFileChooser(); 
        filesave.setFileFilter(new myFileFilter(".kk", "2K Language project"));
        filesave.showSaveDialog(frame);
        filesave.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (filesave.getSelectedFile() == null)
            return;
        files[index] = filesave.getSelectedFile();
        currentFile = files[index];
        try {
            saveCurrentFile();
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }  
    
    
    /**
    * Открыть файл
    */
    public void openFile(MainWindow frame, int index) {                                          
        JFileChooser fileopen = new JFileChooser(); 
        fileopen.setFileFilter(new myFileFilter(".kk", "2K Language project"));
        int ret = fileopen.showOpenDialog(frame);        
        fileopen.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (ret == JFileChooser.APPROVE_OPTION) {
            files[index] = fileopen.getSelectedFile();
            currentFile = files[index];
            mTextPane.setText(null);
            try {
                BufferedReader input = new BufferedReader(new FileReader(currentFile.getAbsolutePath()));
                String readStr;
                String fileStr = "";
                while ((readStr = input.readLine()) != null)
                    fileStr += readStr + "\n";
                mTextPane.setText(fileStr);
                input.close();
            } catch (IOException e) {}
        }        
    }
    
    /**
    * Сохранить файл
    */
    public void saveFile(MainWindow frame) {                                          
        try {
            if (!isEmpty())
                saveCurrentFile();
            else
                frame.saveAs();
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }                                         
    
    public void saveCurrentFile() throws IOException {
        String content = mTextPane.getText();
        FileWriter filesave = new FileWriter(currentFile);
        filesave.write(content);
        filesave.close();
    }
    
    public void closeFile(int index) {
        files[index] = null;
        currentFile = null;
        for (int i = index; i < files.length; i++) {
            files[index] = files[index + 1];
        }
    }
    
    public void setFile(int index) {
        if (index > -1)
            currentFile = files[index];
    }
    
    private File[] files = new File[100];
}
