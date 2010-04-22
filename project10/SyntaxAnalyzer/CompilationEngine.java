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
	
	// Starting to parse a class
	outStream.write("<class>\n");

	if(tokenizer.keyWord().equals("class")) {
		outStream.write("<keyword> " + tokenizer.keyWord() + " </keyword>\n");
		tokenizer.advance();
		outStream.write("<identifier> " + tokenizer.identifier() + " </identifier>\n");
		tokenizer.advance();
		outStream.write("<symbol> " + tokenizer.symbol() + " </symbol>\n");
		tokenizer.advance();
		if(tokenizer.keyWord().equals("static") || tokenizer.keyWord().equals("field")) {
			CompileClassVarDec();
		}else if(tokenizer.keyWord().equals("constructor") || tokenizer.keyWord().equals("function") || tokenizer.keyWord().equals("method")) {
			CompileSubroutine();
		}
		
	}
	
	// Finished with class
	outStream.write("</class>\n");
	
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
  public void CompileClassVarDec() throws IOException {
  
  }
  
  // Compiles a complete method, function, or constructor
  public void CompileSubroutine() throws IOException {
	outStream.write("<subroutineDec>\n");
	outStream.write("<keyword> " + tokenizer.keyWord() + " </keyword>\n");
	tokenizer.advance();
	OutputXML(tokenizer.tokenType());
	tokenizer.advance();
	outStream.write("<identifier> " + tokenizer.identifier() + " </identifier>\n");
	tokenizer.advance();
  	outStream.write("<symbol> " + tokenizer.symbol() + " </symbol>\n");
	tokenizer.advance();
	//Parameter list
	compileParameterList();
	//Subroutine body:
	outStream.write("<subroutineBody>\n");
	outStream.write("<symbol> " + tokenizer.symbol() + " </symbol>\n");
	tokenizer.advance();
	while (tokenizer.keyWord().equals("var")){
		compileVarDec();
	}
	//compileStatements();
	outStream.write("</subroutineBody>\n");
	outStream.write("</subroutineDec>\n");
  }
  
  // Compiles a parameter list
  public void compileParameterList() throws IOException {
	outStream.write("<parameterList>\n");
	
	Boolean cont = true;
	token_type = tokenizer.tokenType();
	
	// If parameter list is empty, handle and return
	if (token_type == Token.SYMBOL){
		if (tokenizer.symbol() == ')'){
			outStream.write("</parameterList>\n");
			OutputXML(token_type);
			tokenizer.advance();
			return;
		}
	}
	
	// Otherwise loop through and print out the parameter list
	while (cont){
    	//returns token's corresponding XML line
		OutputXML(token_type);
		
		tokenizer.advance();
		
		token_type = tokenizer.tokenType();
		if (token_type == Token.SYMBOL){
			if (tokenizer.symbol() == ')'){
				cont = false;
			}
		}
	}
		
	outStream.write("</parameterList>\n");
	
	// Handle closing parentheses
	OutputXML(tokenizer.tokenType());
	tokenizer.advance();
  }
  
  // Compiles a var declaration
  public void compileVarDec() throws IOException {
  	Boolean cont = true;
	
  	outStream.write("<varDec>\n");
	
	// Print out first variable declaration
	outStream.write("<keyword> " + tokenizer.keyWord() + " </keyword>\n");
	tokenizer.advance();
	OutputXML(tokenizer.tokenType());
	tokenizer.advance();
	outStream.write("<identifier> " + tokenizer.identifier() + " </identifier>\n");
	tokenizer.advance();
	
	// Check if there are more
	if (tokenizer.symbol() == ';'){
		cont = false;
	}
	
	// If there are more variable declarations continue looping
	while (cont){
		outStream.write("<symbol> " + tokenizer.symbol() + " </symbol>\n");
		tokenizer.advance();
		OutputXML(tokenizer.tokenType());
		tokenizer.advance();
		outStream.write("<keyword> " + tokenizer.keyWord() + " </keyword>\n");
		tokenizer.advance();
		outStream.write("<identifier> " + tokenizer.identifier() + " </identifier>\n");
		tokenizer.advance();
		
		if (tokenizer.symbol() == ';'){
			cont = false;
		}
	}
	
	// Handle semi-colon at end
	OutputXML(tokenizer.tokenType());
	tokenizer.advance();
	
	outStream.write("</varDec>\n");
  }
  
  // Compiles a sentence of statements
  public void compileStatements() throws IOException {
	outStream.write("<statements>\n");
	
	String kwd = tokenizer.keyWord();
	
	Boolean cont = true;
	while (cont){
		if (kwd.equals("let")){
			outStream.write("<letStatement>\n");
			compileLet();
			outStream.write("</letStatement>\n");
		} else if (kwd.equals("if")){
			outStream.write("<ifStatement>\n");
			compileIf();
			outStream.write("</ifStatement>\n");
		} else if (kwd.equals("while")){
			outStream.write("<whileStatement>\n");
			compileWhile();
			outStream.write("</whileStatement>\n");
		} else if (kwd.equals("do")){
			outStream.write("<doStatement>\n");
			compileDo();
			outStream.write("</doStatement>\n");
		} else if (kwd.equals("return")){
			outStream.write("<returnStatement>\n");
			compileReturn();
			outStream.write("</returnStatement>\n");
		} else {
			System.err.println("Error parsing statements.");
		}
		
		// Stopping condition if have reached a symbol, the closing }
		if (tokenizer.tokenType() == Token.SYMBOL) {
			cont = false;
		}
	}
  
	outStream.write("</statements>\n");
  }
  
  // Compiles a do statement
  public void compileDo() throws IOException {
	// Print out the first keyword
	OutputXML(tokenizer.tokenType());
	
	// Advance until hit the next keyword for now
	tokenizer.advance();
	while (tokenizer.tokenType() != Token.KEYWORD){
		tokenizer.advance();
	}
  }
  
  // Compiles a let statement
  public void compileLet() throws IOException {
	// Print out the first keyword
	OutputXML(tokenizer.tokenType());
	
	// Advance until hit the next keyword for now
	tokenizer.advance();
	while (tokenizer.tokenType() != Token.KEYWORD){
		tokenizer.advance();
	}
  }
  
  // Compiles a while statement
  public void compileWhile() throws IOException {
	// Print out the first keyword
	OutputXML(tokenizer.tokenType());
	
	// Advance until hit the next keyword for now
	tokenizer.advance();
	while (tokenizer.tokenType() != Token.KEYWORD){
		tokenizer.advance();
	}
  }
  
  // Compiles a return statement
  public void compileReturn() throws IOException {
	// Print out the first keyword
	OutputXML(tokenizer.tokenType());
	
	// Advance until hit the next keyword for now
	tokenizer.advance();
	while (tokenizer.tokenType() != Token.SYMBOL){
		tokenizer.advance();
	}
  }
  
  // Compiles an if statement
  public void compileIf() throws IOException {
	// Print out the first keyword
	OutputXML(tokenizer.tokenType());
	
	// Advance until hit the next keyword for now
	tokenizer.advance();
	while (tokenizer.tokenType() != Token.KEYWORD){
		tokenizer.advance();
	}
	
	// If else statement advance until next keyword for now
	if (tokenizer.keyWord().equals("else")){
		tokenizer.advance();
	  	while (tokenizer.tokenType() != Token.KEYWORD){
			tokenizer.advance();
		}
	}
  }
  
  // Compiles an expression
  public void CompileExpression() throws IOException {
	// Print out the first keyword
	OutputXML(tokenizer.tokenType());
	
	// Advance until hit the next keyword for now
	tokenizer.advance();
	while (tokenizer.tokenType() != Token.KEYWORD){
		tokenizer.advance();
	}
  }
  
  // Compiles a term
  public void CompileTerm() throws IOException {
	// Print out the first keyword
	OutputXML(tokenizer.tokenType());
	
	// Advance until hit the next keyword for now
	tokenizer.advance();
	while (tokenizer.tokenType() != Token.KEYWORD){
		tokenizer.advance();
	}
  }
  
  // Compiles a comma-separated list of expressions
  public void CompileExpressionList() throws IOException {
	
  }
  public void OutputXML(Token token_type) throws IOException {
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