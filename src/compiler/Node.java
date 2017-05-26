
package Compiler;

import ide245.Token;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author КК
 */
public class Node {
    
    public Node() {
        value = "";
    }
    
    public void parse() {
        
    }
    
    public void print() {
        
    }
    
    @Override
    public String toString() {
        return value;
    }
    
    public void remove(int i) {
        Node.tokens.remove(i);
    }
    
    public String get(int i) {
        return Node.tokens.get(0).getString();
    }
    
    protected String fileName = "temp.txt";
    protected FileWriter sw;
    protected String value;
    protected static List<Token> tokens;
}
