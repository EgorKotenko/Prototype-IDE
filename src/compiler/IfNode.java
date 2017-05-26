
package Compiler;

import java.io.FileWriter;

/**
 *
 * @author КК
 */
public class IfNode extends Node {
    
    @Override
    public void print() {
        try { 
            sw = new FileWriter("temp.txt",true);
            sw.write(value + "\r\n");
            sw.close();
        } catch(Exception e) {
            System.out.print(e.getMessage());
        }
        System.out.println(value);
        ifBlock.print();
        if (elseBlock != null) {
            try { 
            sw = new FileWriter("temp.txt",true);
            sw.write("else ");
            sw.close();
        } catch(Exception e) {
            System.out.print(e.getMessage());
        }
            System.out.print("else ");
            elseBlock.print();
        }
    }
    
    @Override
    public void parse()
    {
        while (!get(0).equals(")")) {
            value += " " + get(0);
            remove(0);
        }
        value += " " + get(0);
        remove(0);
        
        if (get(0).equals("{")) {
            ifBlock = new BlockNode();
            ifBlock.parse();
        }
        
        if (get(0).equals("else")) {
            elseBlock = new BlockNode();
            elseBlock.parse();
        }
        
    }
    
    private Node ifBlock;
    private Node elseBlock;
    
}
