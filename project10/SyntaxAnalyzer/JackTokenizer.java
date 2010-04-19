// JackTokenizer.java

package SyntaxAnalyzer;

import java.io.*;
import java.util.ArrayList;

public class JackTokenizer {

  public ArrayList<String> tokens;
  public int counter;
  public String currentToken;
  public int binaryCount;
  public int variableCount;
  public Boolean Debug = false;
  public Boolean inComment = false;
  public Boolean firstComment = false;

  public enum Token {
    KEYWORD, SYMBOL, IDENTIFIER, INT_CONST, STRING_CONST;
  }
  
  public enum Keyword {
	CLASS, METHOD, FUNCTION, CONSTRUCTOR, INT, BOOLEAN, CHAR, VOID, VAR, STATIC, FIELD, LET, DO, IF, ELSE, WHILE, RETURN, TRUE, FALSE, NULL, THIS;
  }

  // Parses the input stream
  public JackTokenizer(BufferedReader stream) throws IOException {
    tokens = new ArrayList<String>();
    String line = stream.readLine();
    while (line != null){
      // Get rid of comments
      String[] split = line.split("//");
      line = split[0];
	  
	  if (line.contains("/*")){
	    firstComment = true;
	    inComment = true;
		String[] split1 = line.split("/*");
		line = split1[0];
	  }
	  
	  if (line.contains("*/") && inComment){
	    inComment = false;
		String[] split2 = line.split("*/");
		line = split2[1];
	  }
	  
	  if (!inComment | firstComment){
	    firstComment = false;
		
        // Get rid of whitespace at beginning and end
        try {
          while (Character.isWhitespace(line.charAt(0))){
            line = line.substring(1, line.length());
          }
          while (Character.isWhitespace(line.charAt(line.length()-1))){
            line = line.substring(0, line.length()-1);
          }
        } catch (IndexOutOfBoundsException e){
		    
        }

        if (Debug) System.out.println("Line: " + line);

        // Add token to array
        Boolean check = tokens.add(line);
        if (check == false){
          System.err.println("Error adding line to tokens list");
        }
      }

      // Read next line
      line = stream.readLine();
    }
    counter = 0;
    binaryCount = 0;
    variableCount = 16;
    currentToken = tokens.get(counter);
  }

  // Are there more comamnds in input?
  public Boolean hasMoreTokens() {
    if (counter < tokens.size()-1) {
      return true;
    } else {
      return false;
    }
  }

  // Reads next command and makes it current command
  public void advance() {
    counter++;
    currentToken = tokens.get(counter);
  }

  // Returns the type of the current VM command
  public Token tokenType() {
    // Checks token type
    if (currentToken.startsWith("//") || currentToken.length() == 0)
      return null;
    else
	  return Token.KEYWORD;
  }
  
  // Returns the keyword which is the current token
  public String keyWord() {
	if (tokenType() != Token.KEYWORD) {
	  System.err.println("Error retrieving keyword");
	  return null;
	}
    return currentToken;
	//return Keyword.CLASS;
  }

  // Returns the character which is the current token
  public char symbol() {
	if (tokenType() != Token.SYMBOL) {
	  System.err.println("Error retrieving symbol");
	  return '0';
	}
	
	//return char(currentToken);
	return 'c';
  }

  // Returns the identifier which is the current token
  public String identifier() {
    if (tokenType() != Token.IDENTIFIER){
	  System.err.println("Error retrieving identifier");
	  return null;
	}
	
    return currentToken;
  }
  
  public Integer intVal() {
    if (tokenType() != Token.INT_CONST){
	  System.err.println("Error retrieving integer constant");
	  return null;
	}
	int num = Integer.parseInt( currentToken );
    return num;
  }
  
  public String stringVal() {
    if (tokenType() != Token.STRING_CONST){
	  System.err.println("Error retrieving string constant");
	  return null;
	}
	
    return currentToken;
  }
}