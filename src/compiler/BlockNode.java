
package Compiler;

import ide245.Token;
import ide245.TokenType;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author КК
 */
public class BlockNode extends Node {
    
    public BlockNode() {
        children = new ArrayList<Node>();
    }
    
    public void addChild(Node node) {
        children.add(node);
        node.parse();
    }
    
    @Override
    public void print() {
        try {
            sw = new FileWriter("temp.txt",true);
            sw.write("{" + "\r\n");
            sw.close();
            System.out.println("{");
            for (int i = 0; i < children.size(); i++)
                children.get(i).print();
            sw = new FileWriter(fileName,true);
            if (isRoot)
                sw.write(" cout << endl;\r\n getch();" + "\r\n}\r\n");
            else 
                sw.write("}" + "\r\n");
            sw.close();
            System.out.println("}");
        } catch (IOException ex) {
            Logger.getLogger(BlockNode.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @Override
    public void parse() {
        if (get(0).equals("{"))
            remove(0);
        // else шельме кашельме шайтана-ма
        
        while (!get(0).equals("}")) {
            Token t = Node.tokens.get(0);
            String token = t.getString();
            Node node;
            if (t.type.equals(TokenType.TYPE)) {
                node = new VarNode();
            } else if (t.type.equals(TokenType.IDENTIFIER)) {
                node = new ValNode();
            } else if (token.equals("for")) {
                node = new CycleNode();
            } else if (token.equals("if")) {
                node =  new IfNode();
            } else if (token.equals("print") || token.equals("read")) {
                node = new IONode();
            } else if (t.type.equals(TokenType.COMMENT)) {
                remove(0);
                continue;
            } else 
                node = new Node();
            node.parse();
            children.add(node);
        }
        
        if (get(0).equals("}"))
            remove(0);
        // else шельме кашельме шайтана-ма
        
    }
    
    private List<Node> children;
    protected Boolean isRoot = false;
}
