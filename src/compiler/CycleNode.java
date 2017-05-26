/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Compiler;

import java.io.FileWriter;

/**
 *
 * @author m10kaa
 */
public class CycleNode extends Node {
    
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
        body.print();
    }
    
    @Override
    public void parse() {
         while (!get(0).equals(")")) {
            value += " " + get(0);
            remove(0);
        }
        value += " " + get(0);
        remove(0);
        
        if (get(0).equals("{")) {
            body = new BlockNode();
            body.parse();
        }
    }
    
    private Node body;
}
