// CompilationEngine.java

package SyntaxAnalyzer;

import java.io.*;
import SyntaxAnalyzer.JackTokenizer.Token;

public class CompilationEngine {
  public OutputStreamWriter outStream;
  public JackTokenizer tokenizer;
  public JackTokenizer.Token token_type;

  // Creates a new compilation engine
  public CompilationEngine(JackTokenizer token, OutputStreamWriter stream) throws IOException {
    outStream = stream;
	tokenizer = token;
  }

  // Compiles a complete class
  public void CompileClass() throws IOException {
	
	 System.out.println("Tokenizer is: " + tokenizer);
	 if(tokenizer.equals("class")) {										//this never executes - the comparison doesn't work properly											
	 	outStream.write("<lala> " + tokenizer + " </keyword>\n");
		System.out.println("<keyword> " + tokenizer + " </keyword>");
		tokenizer.advance();
		outStream.write("<identifier> " + tokenizer + " </identifier>\n");
		tokenizer.advance();
		outStream.write("<symbol> " + tokenizer + " </symbol>\n");
		tokenizer.advance();
		if(tokenizer.equals("static") || tokenizer.equals("field")) {
			CompileClassVarDec();
		}else if(tokenizer.equals("constructor") || tokenizer.equals("function") || tokenizer.equals("method")) {
			CompileSubroutine();
		}
		
	 }
	//print default <tokens>									//IDK why this is here....
	outStream.write("<tokens>\n");
	// Loop through tokens and handle each one
    while (tokenizer.hasMoreTokens()) {
	  //assigns token's type
      token_type = tokenizer.tokenType();

    	//returns token's corresponding XML line
		OutputXML(token_type);
		
      tokenizer.advance();
    }

    // Handle last token
    token_type = tokenizer.tokenType();
	OutputXML(token_type);
	
	outStream.write("</tokens>\n");
  
  }
  
  // Compiles a static declaration or a field declaration
  public void CompileClassVarDec() {
  
  }
  
  // Compiles a complete method, function, or constructor
  public void CompileSubroutine() {
	outStream.write("<subroutineDec>\n");
	outStream.write("<keyword> " + tokenizer + " </keyword>\n");
	tokenizer.advance();
	OutputXML(token_type);
	tokenizer.advance();
	outStream.write("<identifier> " + tokenizer + " </identifier>\n");
	tokenizer.advance();
  	outStream.write("<symbol> " + tokenizer + " </symbol>\n");
	tokenizer.advance();
	//Parameter list
	compileParameterList();
	outStream.write("<symbol> " + tokenizer + " </symbol>\n");
	//Subroutine body:
	outStream.write("<subroutineBody>\n");
	tokenizer.advance();
	outStream.write("<symbol> " + tokenizer + " </symbol>\n");
	tokenizer.advance();
	compileVarDec();
  }
  
  // Compiles a parameter list
  public void compileParameterList() {
	outStream.write("<parameterList>\n");
	while (tokenizer.hasMoreTokens()) {
	  //assigns token's type
      token_type = tokenizer.tokenType();

    	//returns token's corresponding XML line
		OutputXML(token_type);
		
      tokenizer.advance();
    }
	// Handle last token
    token_type = tokenizer.tokenType();
	OutputXML(token_type);
	
	outStream.write("</parameterList>\n");
	//Advance to the ')' symbol
	tokenizer.advance();
	System.out.println(tokenizer + " should be ')', otherwise check up on compileParameterList");
  }
  
  // Compiles a var declaration
  public void compileVarDec() {
  	outStream.write("<varDec>\n");
	outStream.write("<keyword> " + tokenizer + " </keyword>\n");
	tokenizer.advance();
	outStream.write("<keyword> " + tokenizer + " </keyword>\n");
	tokenizer.advance();
	outStream.write("<identifier> " + tokenizer + " </identifier>\n");
  }
  
  // Compiles a sentence of statements
  public void compileStatements() {
  
  }
  
  // Compiles a do statement
  public void compileDo() {
  
  }
  
  // Compiles a let statement
  public void compileLet() {
  
  }
  
  // Compiles a while statement
  public void compileWhile() {
  
  }
  
  // Compiles a return statement
  public void compileReturn() {
  
  }
  
  // Compiles an if statement
  public void compileIf() {
  
  }
  
  // Compiles an expression
  public void CompileExpression() {
  
  }
  
  // Compiles a term
  public void CompileTerm() {
  
  }
  
  // Compiles a comma-separated list of expressions
  public void CompileExpressionList() {
  
  }
  public void OutputXML(Token token_type) {
		if(token_type == Token.KEYWORD) {
			try{
				outStream.write("<keyword> " + tokenizer.keyWord() + " </keyword>\n");
			}catch(IOException x){
				//TODO: print error?
			}
		}
		if(token_type == Token.SYMBOL) {
			String x = Character.toString(tokenizer.symbol());
			if(x.equals("<")) {
				x = "&lt;";
			}
			if(x.equals(">")) {
				x = "&gt;";
			}
			if(x.equals("&")) {
				x = "&amp;";
			}
			try{
				outStream.write("<symbol> " + x + " </symbol>\n");
			}catch(IOException z){
				System.err.println("error outputing XML");
			}
		}
		if(token_type == Token.IDENTIFIER) {
			try{
				outStream.write("<identifier> " + tokenizer.identifier() + " </identifier>\n");
			}catch(IOException h){
				System.err.println("error outputing XML");
			}
		}
		if(token_type == Token.INT_CONST) {	
			try{
				outStream.write("<integerConstant> " + tokenizer.intVal() + " </integerConstant>\n");
			}catch(IOException k) {
				System.err.println("error outputing XML");
			}
		}
		if(token_type == Token.STRING_CONST) {
			try{
				outStream.write("<stringConstant> " + tokenizer.stringVal() + " </stringConstant>\n");
			}catch(IOException m){
				System.err.println("error outputing XML");
			}
		}
  }
}