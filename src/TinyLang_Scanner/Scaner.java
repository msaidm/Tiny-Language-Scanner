
package TinyLang_Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.Character ;
import java.lang.Object ;

public class Scaner {
 
    
    
    public static void main(String[] args) 
    {
        
        char[] symbols = {'+', '-', '*', '/', '=', '<', '(', ')', ';', ':'};
        String[] resWord = {"if", "then", "else", "end", "repeat", "until", "read", "write"};
        Scanner sc = new Scanner(System.in);
        String tempDigits = "";
        String tempWord = "";
        String comment = "";
        int it = 0;
        boolean flag =false ; 
        try {
            BufferedReader abc = new BufferedReader(new FileReader("Input.txt"));
            List<String> lines = new ArrayList<String>();
            String line;
            while ((line = abc.readLine()) != null) {
                lines.add(line);
            }
            String[] data = lines.toArray(new String[lines.size()]);
            for (int i = 0; i < data.length; i++) {

                char[] x = new char[data[i].length()];
                x = data[i].toCharArray();

                for (int k = 0; k < x.length; k++) {
                    //Checking for Comments
                    if (x[k] == '{') {  
                        int y = data[i].indexOf('}');
                        comment = data[i].substring(k, y + 1);
                        System.out.println(comment + "   " + "Comment");
                        k = y;
                    } 

                    //Checking for Symboles 
                    else if (!Character.isWhitespace(x[k]) && !Character.isLetterOrDigit(x[k])) {
                        if (x[k] == ':' && x[k + 1] == '=') {
                            System.out.println(":=" + "   " + "Symbol");
                            k++;
                            continue;
                        } 
                        while(it<symbols.length)
                        {
                            if(x[k]==symbols[it])
                            {
                                
                                System.out.println(x[k] + "   " + "Symbol");
                                it=0 ; 
                                flag = true  ; 
                                break ; 
                            }
                            else { 
                                it++ ; 
                            }
                            
                        }
                        if(!flag)
                        {
                            System.out.println(x[k] + "   " + "error");
                           //it=0 ;  
                        }
                         flag = false ;
                             it= 0 ; 
                         
                    }
                    //Checking for Digits
                    else if (Character.isDigit(x[k])) {
                        tempDigits = tempDigits + x[k];
                        if (k < x.length - 1) {
                            if (!Character.isDigit(x[k + 1]) || Character.isWhitespace(x[k + 1])) {
                                
                                if(Character.isLetter(x[k + 1]) )
                                {
                                    while(!Character.isWhitespace(x[k + 1]))
                                    {
                                    tempDigits = tempDigits + x[k+1];                                           
                                    k++ ; 
                                    }
                                    System.out.println(tempDigits + "   " + "lexical error");
                                    tempDigits = "";
                                }
                                else{
                                System.out.println(tempDigits + "   " + "Number");
                                tempDigits = "";
                                }
                            }
                         
                        }
                        else  {
                            System.out.println(tempDigits + "   " + "Number");
                            tempDigits = "";
                        }
                    } 
                    //Checling for Reserved Word and Ideintifers
                    else if (Character.isLetter(x[k])) {

                        tempWord = tempWord + x[k];
                        
                        if (k < x.length - 1) {
                            if (!Character.isLetter(x[k + 1]) || Character.isWhitespace(x[k + 1])) {
                                if(Character.isDigit(x[k + 1]))
                                {
                                    
                                    while(!Character.isWhitespace(x[k + 1]))
                                    {       
                                      tempWord = tempWord + x[k+1];  
                                      k++ ; 
                                    }
                                    System.out.println(tempWord + "   " + "lexical error");
                                tempWord = "";
                                }
                                else{
                                while (it < resWord.length) {
                                    if (tempWord.equals(resWord[it])) {
                                        System.out.println(tempWord + "   " + "Reserved Word");
                                        tempWord = "";
                                        it = 0;
                                        break;
                                    } else {
                                        it++;
                                    }
                                }
                                }
                                if (!Character.isLetter(x[k + 1]) && Character.isLetter(x[k]) && !tempWord.equals("")) {
                                    System.out.println(tempWord + "   " + "identifer");
                                    tempWord = "";
                                    it = 0;
                                }
                                

                            }

                        }
                    }
                    
                    
                    
                }
            }
            abc.close();
        } catch (IOException ex) {
            Logger.getLogger(Scaner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
