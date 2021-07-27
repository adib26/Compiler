package C_Compiler;
import java.util.ArrayList;
import java.util.LinkedList;

public class C_Parser {
    String token;
    int linesz;
    int last;
    LinkedList<String>input = new LinkedList<String>();
    LinkedList<Integer>line = new LinkedList<Integer>();
    boolean flag = false;
    public void parse(ArrayList<String> inputt,ArrayList<Integer> lines){

        this.input.addAll(inputt);
        this.input.addLast("end");
        this.line.addAll(lines);
        this.line.addLast(-1);
        
        token = this.input.getFirst();
        linesz = this.line.getFirst();
        System.out.println("\nParser Output\n");
        main_func();

        

    }

    private void error(String message) {
        System.out.println(message);
        //throw new RuntimeException(message);
    }    
    
    private void nextToken()
    {
        //at the end of input we return an epsilon token

        if (token == "end"){
        
        }
        else{
            input.pop();
            
            token = input.getFirst();
        
        }
        if(line.getFirst() == -1){
        linesz = last;
                
        }else{     
        line.pop(); 
        last = line.getFirst(); 
        linesz = line.getFirst();
           
        }
        
        
    }    
    
    private void skipline(){
        while(token != "semicolon" && token != "return" && token != "end"){
            nextToken();
        }
        
    
    }
        
    
    private void main_func(){
    
    if(m_type()){
    
        if(match("main",1)){
        
            nextToken();
            if(match("(", 1)){
            
                nextToken();
                if(match(")",1)){
                
                    nextToken();
                    if(match("{", 1)){
                    
                        nextToken();
                        stmt();
                        
                        if(match("return",1)){
                        
                            nextToken();
                            if(match("Number",2)){
                                nextToken();
                                if(match("semicolon",1)){
                                
                                nextToken();
                                if(match("}",1)){
                                
                                    System.out.println("right main");
                                    return;
                                }
                                
                                }                                
                            
                            }else{
                                if(match("semicolon",1)){
                                
                                nextToken();
                                if(match("}",1)){
                                
                                    System.out.println("right main");
                                    return;
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
    
    private boolean m_type(){
    
    if(token == "datatype" | token == "void"){
    nextToken();
    return true;
    }
    return false;
    }
    
    private void stmt(){
    
    // stmt -> declarition stmt | assignment stmt | if_stmt stmt | null
        
         if(token == "datatype"){
                
                if(declarition()){
                    System.out.println("right declarition in line " + linesz);
                    nextToken();
                    stmt();
                }else{
                    skipline();
                    nextToken();
                    stmt();
                    flag = false;
                }
                
            }else{
            
            if( token == "Identifier" ){
                
                
                if( assignment() ){
                    System.out.println("right assignment in line" + linesz);
                    nextToken();
                    stmt();
                }else{
                    skipline();
                    nextToken();
                    stmt();
                    flag = false;
                }
                
                
            }else{
            
                if(token == "if"){
                
                  
                if( if_stmt() ){

                    System.out.println("right if in line " + linesz);
                    nextToken();
                    stmt();
                }else{
                    skipline();
                    nextToken();
                    stmt();
                    flag = false;
                    
                }
                
                }else{
                
                return;
                
                }
                
            
            }    
            
            }
    
    
    }
    
    
    
    private boolean match(String c,int x){
         
        if( String.valueOf(token)  == c ){
        return true;
        }else{
        if(x == 1 ){
        error("unexpected  "+token+" in line number "+linesz+" Expected "+c);        
        }
        
        }
        
        return false;
    }
    
    
    
    // declarition functions.......
    
    private boolean declarition(){
    // declarition -> datatype var ;
    nextToken();
    
    var();
    
    if(match("semicolon",1))
    {
        return true;
    }
    return false;
    }
    
    private void var(){
    // var-> identifier | identifier , var
    
    if(match("Identifier",1)){
        nextToken();
    
        if(match("seperator",2)){
        // identifier , var
            nextToken();
            var();
        }
        
    }
    
    }    

    
    
    // assignment functions......
    
    private boolean assignment(){
    
    // assignment -> identifier = part ;
    nextToken();
    if(match("=",1)){
    
        nextToken();
        part();
            
        if(match("semicolon",1)){
        return true;
        }
         
    }
        return false;
    }
    
    private void part(){
    
        // part ->  term op | (term op)op
    if(match("(",2)){
        
            nextToken();
            if(term()){
            nextToken();
           
            if(op()){
                part();
            }
            
            if(match(")",1)){
            
                nextToken();
                if(op()){
                part();
                }
                
            }

            }else{
                flag = true;   
                error("unexpected  "+token+" in line number "+linesz);             
            }
        }else{
        
        if(term()){
        nextToken();
        if(op()){
        part();
        }
        
        }else{
                flag = true;   
                error("unexpected  "+token+" in line number "+linesz + " Expected Variable or constant"); 
                
        }
    
    }
        
        
    }

    private boolean term(){
    
       // term -> var | const | character
       if(match("Identifier",2) || match("Number",2)){
       return true;
       }else{
       if(character()){
       return true;
       }
       
       }
       
        return false;
    }
    
    private boolean op(){

        if(match("Operator",2)){
    nextToken();
    return true;
    }
        return false;
    }
    
    private boolean character(){
    
        // character -> ' char '
    if(match(token.toString(),2)){
    nextToken();
        if(match("Identifier",2)){
        nextToken();
        if(match(token.toString(),2)){
        return true;
        }
        }
    
    }
        return false;
    }
    
    
    // if functions
    
    private boolean if_stmt(){
    
        // if_stmt -> if (condition) { statement } else_part
        nextToken();
        if(match("(",1) ){
        
        nextToken();
        if(condition()){
        if(flag != true){
        
            
            if(match(")",1)){
            
                nextToken();
                if(match("{",1)){
                statement();
                if(match("}",1)){
                else_part();
                return true;
                }
                
                }
                
            
            }                    
        
        }
        }

        
        
        
        }
        
        
        return false;
    }
    
    private boolean condition(){
    
    // condition -> part comparison part
        part();
        if(flag != true){
        if(comparison()){
            
            nextToken();
            part();
            if(flag != true)
            return true;
           
            }else{
            flag = true;   
            error("unexpected  "+token+" in line number "+linesz);             
            }
        
    }
        return false;
    }
    
    private boolean comparison(){
        if(match( "=",2) || match(">",2) || match("<",2)  || match("!",2) ){
            
            String ss = input.getFirst();
            input.pop();
            
            String nn = input.getFirst();
            
            if (nn == "=") {
                line.pop(); 
                last = line.getFirst(); 
                linesz = line.getFirst();
                return true;
           }else{
            input.addFirst(nn);
            }

        return true;
        }
    return false;
    }

    private void statement(){
    
        //statement -> assignment | if_stmt | assignment statement | null
        
        nextToken();
        if(match("if",2)){
            
            
                        if(if_stmt()){
                    System.out.println("right if in line "+linesz);
                    
                    
                }else{
                        skipline();
                        }
                        statement();
        }else{
            
            if(match("Identifier",2)){
            
                
                if( assignment() ){
                    System.out.println("right assignment in line "+linesz);
                }else{
                skipline();
                }
                statement();
            }else{
            
                return;
            }
        
        }
        
    
    }
    
    private void else_part(){
    
    // else_part -> else { statement }
        String d = input.pop();
        token = input.getFirst();
    if(match("else",2)){
    
        nextToken();
        if(match("{",2) ){
        
            statement();
            
            if(match("}",2)){
            input.addFirst(d);
            nextToken();
            return;
            }
            
        }
    
    }
    
    input.addFirst(d);
    }
    
}