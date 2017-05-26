/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Артем
 */
public class Executer {

    /**
     *
     * @param name - имя файла (temp.cpp)
     */
    public static void execute(String name) {
        try {
            String[] command = {
                "cmd",};
            Process p = Runtime.getRuntime().exec(command);
            new Thread(new SyncPipe(p.getErrorStream(), System.err)).start();
            new Thread(new SyncPipe(p.getInputStream(), System.out)).start();
            PrintWriter stdin = new PrintWriter(p.getOutputStream());
            stdin.println("taskkill /IM mProgram.exe");
            stdin.println("rename temp.txt temp.cpp");
            stdin.println("g++ -o mProgram temp.cpp");
            stdin.println("start mProgram.exe");
            stdin.println("remove temp.cpp");
            // write any other commands you want here
            stdin.close();
            int returnCode = p.waitFor();
            System.out.println("Return code = " + returnCode);
        } catch (InterruptedException ex) {
            Logger.getLogger(Executer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Executer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
