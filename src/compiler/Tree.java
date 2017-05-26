package Compiler;

import ide245.Token;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class Tree {
    
    public Tree(List<Token> list) {
        Node.tokens = list;
    }
    
    public void parse() {
        try {
            root = new BlockNode(); 
            root.isRoot = true;
            root.sw = new FileWriter(root.fileName,true);
            root.sw.write("#include <conio.h>\r\n" + "#include \"iostream\"\r\n" + "using namespace std;\r\n\r\n" + "int main()" + "\r\n");
            root.sw.close();
            root.parse();
            root.print();
        } catch(Exception e) {
            System.out.print(e.getMessage());
        }
    }
    
    private BlockNode root;
    
}