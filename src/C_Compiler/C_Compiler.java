
package C_Compiler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class C_Compiler {

    public static void main(String[] args) throws FileNotFoundException 
    {
        
        C_Scanner myScanner = new C_Scanner();
        File myFile = new File("C:\\Users\\pola_\\Downloads\\test3.txt");
        
        ArrayList lines = myScanner.scan(myFile);
        
               
        
        System.out.println("---------------End of Scanner-----------");
        
        C_Parser myParser = new C_Parser();
        myParser.parse(myScanner.output,lines);
    }
   
}
