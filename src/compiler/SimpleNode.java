
package Compiler;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author КК
 */
public class SimpleNode extends Node {
    
    @Override
    public void print() {
        try {
            sw = new FileWriter("temp.txt",true);
            sw.write(value + "\r\n");
            sw.close();            
            System.out.println(value);
        } catch (IOException ex) {
            Logger.getLogger(SimpleNode.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void parse() {
        while (!get(0).equals(";")) {
            value += " " + get(0);
            remove(0);
        }
        value += get(0);        
        remove(0);
        value = value.replace("print", "cout <<");
        value = value.replace("read", "cin >>");
        value = value.replace("real", "double");       
    }
    
}
