package C_Compiler;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

public class C_Scanner {
    
    ArrayList<String>output = new ArrayList<String>();
    ArrayList<Integer>lines = new ArrayList<Integer>();
    int linenum =1;
    public ArrayList<String> getOutput() {
        return output;
    }
    public ArrayList scan(File file) throws FileNotFoundException
    {
        System.out.println("Scanner Output\n");
        try (Scanner input = new Scanner(file)) {
            while(input.hasNext())
            {
                
                String line = input.nextLine();
                analyze(line);          
                linenum += 1;
            }
        }
        return lines;
    }
    
    public void analyze(String line)
    {
        String[] keywords = {"if", "else","main","return"};
        String[] Datatypes = {"int", "float", "char"};
        String[] symbols = { "(", ")", "=", "<", ">", "<=", ">=", "==", "!=", "{", "}", ",", ";"};
        String[] operators = {"*", "/", "+", "-"};
        String[] sentences = line.split(" ");
        boolean exponent = false;
        for(int i=0;i<sentences.length;i++){
            String sentence = sentences[i];
            String word = "";
           
            
            //Numbers
            for(int j=0;j<sentence.length();j++){
                word += String.valueOf(sentence.charAt(j));
                if(Character.isDigit(word.charAt(0))){
                    if(j+1<sentence.length()){
                        if(sentence.charAt(j+1)=='.'){
                            if(j+1<sentence.length()){
                                if(Character.isDigit(sentence.charAt(j+2))){
                                    word += String.valueOf(sentence.charAt(j+1));
                                    j=j+1;
                                }
                            }
                        }
                    }
                    
          
                    
                    if(j+1==sentence.length()){
                        System.out.println("Number: "+word);
                        output.add("Number");
                        lines.add(linenum);
                        word="";
                    }
                    if(j+1<sentence.length()){
                        if(Character.isLetter(sentence.charAt(j+1)) && exponent == true){
                            System.out.println("Number: "+word);
                            output.add("Number");
                            lines.add(linenum);
                            word="";
                        }
                        if(Character.isLetterOrDigit(sentence.charAt(j+1))==false){
                            System.out.println("Number: "+word);
                            output.add("Number");
                            lines.add(linenum);
                            word="";
                        }
                    }
                }
                
                // key words
                if(word.length()>0){
                    if(Character.isLetter(word.charAt(0))){
                        if(j+1<sentence.length()){
                            if((Character.isLetterOrDigit(sentence.charAt(j+1)))==false){
                                for(String keyword: keywords){
                                    if(word.equals(keyword)){
                                        System.out.println("Keyword : "+word);
                                        output.add(keyword);
                                        lines.add(linenum);
                                        word="";
                                    }
                                    else if(word.length()>0){
                                        System.out.println("Identifier: "+word);

                                        if(word.equals("void"))
                                        output.add("void");
                                        else                                         
                                        if(word.equals("main"))
                                        output.add("main");
                                        else    
                                            if(word.equals("else"))
                                            output.add("else");   
                                            else
                                        output.add("Identifier");
                                        lines.add(linenum);
                                        word="";
                                    }
                                }
                            }
                        }
                        if(j+1==sentence.length()){
                            for(String keyword: keywords){
                                    if(word.equals(keyword)){
                                        System.out.println("Keyword : "+word);
                                        output.add(keyword);
                                        lines.add(linenum);
                                        word="";
                                    }
                                }
                            }
                        
                        if(j+1==sentence.length()){
                            for(String datatype: Datatypes){
                                    if(word.equals(datatype)){
                                        System.out.println("datatype : "+word);
                                        output.add("datatype");
                                        lines.add(linenum);
                                        word="";
                                    }
                                }
                            if(word.length()>0){
                            System.out.println("Identifier: "+word);
                            
                            if(word.equals("main"))
                                output.add("main");
                            else 
                                output.add("Identifier");
                            lines.add(linenum);
                            word="";
                            }
                        }
                    }
                }
                
                
                
                for(String singlequote: symbols){
                                if(word.equals("'")){
                                    System.out.println("singlequote: "+word);
                                    output.add(word);
                                    lines.add(linenum);
                                    word="";
                                }
                            }
                
                if(word.length()>0){
                    if(Character.isLetterOrDigit(word.charAt(0))==false){
                        if(j<sentence.length()){
                            
                              for(String lessthan: symbols){
                                if(word.equals("<")){
                                    System.out.println("Less than: "+word);
                                    output.add("<");
                                    lines.add(linenum);
                                    word="";
                                }}

                            for(String 	greaterthan: symbols){
                                if(word.equals(">")){
                                    System.out.println("Greater than: "+word);
                                    output.add(">");
                                    lines.add(linenum);
                                    word="";
                                }}
                            
                        for(String equal: symbols){
                            if(word.equals("=")){
                                System.out.println("Equal: "+word);
                                output.add("=");
                                lines.add(linenum);
                                word="";
                                }
                            }


                            for(String notequal: symbols){
                                if(word.equals("!")){
                                    System.out.println("Notequal: "+word);
                                    output.add("!");
                                    lines.add(linenum);
                                    word="";
                                }

                            for(String 	leftcurlybrackets : symbols){
                                if(word.equals("{")){
                                    System.out.println("Left Curly Brackets : "+word);
                                    output.add("{");
                                    lines.add(linenum);
                                    word="";
                                }}

                            for(String 	rightcurlybrackets : symbols){
                                if(word.equals("}")){
                                    System.out.println("Right Curly Brackets : "+word);
                                    output.add("}");
                                    lines.add(linenum);
                                    word="";
                                }}

                            for(String 	leftbrackets : symbols){
                                if(word.equals("(")){
                                    System.out.println("Left parenthesis : "+word);
                                    output.add("(");
                                    lines.add(linenum);
                                    word="";
                                }}

                            for(String 	rightbrackets : symbols){
                                if(word.equals(")")){
                                    System.out.println("Right parenthesis : "+word);
                                    output.add(")");
                                    lines.add(linenum);
                                    word="";
                                }}

                        }
                        if(word.length()==1){
                        for(String operator: operators){
                            if(word.equals(operator)){
                            System.out.println("Operator: "+word);
                            output.add("Operator");
                            lines.add(linenum);
                            word="";
                                }
                            }
                        }
                        if(word.length()==1){
                        for(String sep: symbols){
                            if(word.equals(sep)){
                            System.out.println("seperator: "+word);
                            output.add("seperator");
                            lines.add(linenum);
                            word="";
                                }
                            if(word.equals(";")){
                            System.out.println("semicolon: "+word);
                            output.add("semicolon");
                            lines.add(linenum);
                            word="";
                                }
                            
                            }
                        }
                        else if(j+1==sentence.length()){
                            for(String symbol: symbols){
                                if(word.equals(symbol)){
                                    System.out.println("Sympol: "+word);
                                    output.add(symbol);
                                    lines.add(linenum);
                                    word="";
                                }
                            }
                        }
                    }
                }
            }
            
            
            
        }
    }
}
}