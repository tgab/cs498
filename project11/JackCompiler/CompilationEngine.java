// CompilationEngine.java

package JackCompiler;

import java.io.*;
import java.util.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import JackCompiler.JackTokenizer.Token;
import JackCompiler.SymbolTable;
import JackCompiler.SymbolTable.Kind;
import JackCompiler.VMWriter.Segment;
import JackCompiler.VMWriter.Command;
import JackCompiler.SymbolTable.Entry;

public class CompilationEngine {
  public OutputStreamWriter outStream;
  public VMWriter writer;
  public JackTokenizer tokenizer;
  public JackTokenizer.Token token_type;
  public char[] op = {'+','-','*','/','&','|','<','>','='};
  //public ArrayList<SymbolTable> symbolList;
  public SymbolTable table;
  public String className;
  public int labelCount = 0;

  public enum Cat {
	  VAR, ARG, STATIC, FIELD, CLASS, SUB, TYPE;
  }

  // Creates a new compilation engine
  public CompilationEngine(JackTokenizer token, OutputStreamWriter stream) throws IOException {	
	//symbolList = new ArrayList<SymbolTable>();
	table = new SymbolTable();
	writer = new VMWriter(stream);
    outStream = stream;
	tokenizer = token;
  }

  // Compiles a complete class
  public void CompileClass() throws IOException {
	// Starting to compile a class
	//outStream.write("<class>\n");

	if(tokenizer.keyWord().equals("class")) {
		//outStream.write("<keyword> " + tokenizer.keyWord() + " </keyword>\n");
		String keyword = tokenizer.keyWord();
		tokenizer.advance();
		//outStream.write("<identifier> " + tokenizer.identifier() + " CAT=" + Cat.CLASS + " USED=" + false + " </identifier>\n");
		//table.Define(tokenizer.identifier(), keyword, Kind.STATIC, true);
		className = tokenizer.identifier();
		
		tokenizer.advance();
		//outStream.write("<symbol> " + tokenizer.symbol() + " </symbol>\n");
		tokenizer.advance();
		while(tokenizer.keyWord().equals("static") || tokenizer.keyWord().equals("field")) {
			CompileClassVarDec();
		}
		while(tokenizer.hasMoreTokens() && (tokenizer.keyWord().equals("constructor") || tokenizer.keyWord().equals("function") || tokenizer.keyWord().equals("method"))) {
			CompileSubroutine();
		}
		
	}
	
	// Finished with class, print closing bracket and tag
	//OutputXML(tokenizer.tokenType());
	//outStream.write("</class>\n");

	// DEBUG
	printTable(table.classSymbols);
  }
  
  // Compiles a static declaration or a field declaration
  public void CompileClassVarDec() throws IOException {
	//SymbolTable classTable = symbolList.get(symbolList.size()-1);
	
	Boolean cont = true;
	Cat type = Cat.STATIC;
	
	//outStream.write("<classVarDec>\n");
	// Print out first keyword
	if (tokenizer.keyWord().equals("field")){
		type = Cat.FIELD;
	}
	
	//OutputXML(tokenizer.tokenType());
	tokenizer.advance();
	
	// Print out first variable declaration
	//OutputXML(tokenizer.tokenType(), Cat.TYPE, true);
	String keyword;
	if (tokenizer.tokenType() == Token.KEYWORD){
		keyword = tokenizer.keyWord();
	} else {
		keyword = tokenizer.identifier();
	}
	
	tokenizer.advance();
	//OutputXML(tokenizer.tokenType(), type, false);
	table.Define(tokenizer.identifier(), keyword, Kind.VAR, true);
	tokenizer.advance();
	// Check if there are more
	if (tokenizer.symbol() == ';'){
		cont = false;
	}
	
	// If there are more variable declarations continue looping
	while (cont){
		//OutputXML(tokenizer.tokenType(), type, false);
		
		if (tokenizer.tokenType() == Token.IDENTIFIER){
			table.Define(tokenizer.identifier(), keyword, Kind.VAR, true);
		}
		
		tokenizer.advance();

		if (tokenizer.tokenType() == Token.SYMBOL){
			if (tokenizer.symbol() == ';'){
				cont = false;
			}
		}
	}
	
	// Handle semi-colon at end
	//OutputXML(tokenizer.tokenType());
	tokenizer.advance();
	
	//outStream.write("</classVarDec>\n");
  }
  
  // Compiles a complete method, function, or constructor
  public void CompileSubroutine() throws IOException {
	table.startSubroutine();
	
	//outStream.write("<subroutineDec>\n");
	//outStream.write("<keyword> " + tokenizer.keyWord() + " </keyword>\n");
	String thing = tokenizer.keyWord();
	tokenizer.advance();
	if(tokenizer.tokenType() == Token.IDENTIFIER) {
		//OutputXML(tokenizer.tokenType(), Cat.TYPE, true);
		tokenizer.advance();
	}else{
		//OutputXML(tokenizer.tokenType());
		tokenizer.advance();
	}
	//OutputXML(tokenizer.tokenType(), Cat.SUB, false);
	if (thing.equals("method")){
		table.Define(tokenizer.identifier(), thing, Kind.ARG, false);
	}
	String functionName = className + "." + tokenizer.identifier();
	
	tokenizer.advance();
  	//outStream.write("<symbol> " + tokenizer.symbol() + " </symbol>\n");
	tokenizer.advance();
	
	//Parameter list
	compileParameterList();
	

	int count = 0;
	//Subroutine body:
	//outStream.write("<subroutineBody>\n");
	//outStream.write("<symbol> " + tokenizer.symbol() + " </symbol>\n");
	tokenizer.advance();
	while (tokenizer.keyWord().equals("var")){
		count = count + compileVarDec();
	}
	
	// Write function declaration with name and num local variables
	writer.writeFunction(functionName, count);
	
	
	compileStatements();
	
	// Print out closing bracket for subroutine body
	//OutputXML(tokenizer.tokenType());
	tokenizer.advance();
	//outStream.write("</subroutineBody>\n");
	//outStream.write("</subroutineDec>\n");
	
	// DEBUG
	printTable(table.symbols);
  }
  
  // Compiles a parameter list
  public int compileParameterList() throws IOException {
	
	//outStream.write("<parameterList>\n");
	
	Boolean cont = true;
	token_type = tokenizer.tokenType();
	
	int count = 0;
	
	// If parameter list is empty, handle and return
	if (token_type == Token.SYMBOL){
		if (tokenizer.symbol() == ')'){
			//outStream.write("</parameterList>\n");
			//OutputXML(token_type);
			tokenizer.advance();
			return count;
		}
	}
	
	// Otherwise loop through and print out the parameter list
	while (cont){
		//OutputXML(token_type);
		
		String keyword;
		if (tokenizer.tokenType() == Token.KEYWORD){
			keyword = tokenizer.keyWord();
		} else { //if (tokenizer.tokenType() == Token.IDENTIFIER){
			keyword = tokenizer.identifier();
		}
		
		tokenizer.advance();
		token_type = tokenizer.tokenType();
    	//returns token's corresponding XML line
		//OutputXML(token_type, Cat.ARG, false);
		table.Define(tokenizer.identifier(), keyword, Kind.ARG, false);
		count++;
		
		tokenizer.advance();
		
		token_type = tokenizer.tokenType();
		if (token_type == Token.SYMBOL){
			if (tokenizer.symbol() == ')'){
				cont = false;
			} else {
				//OutputXML(token_type);
				tokenizer.advance();
				token_type = tokenizer.tokenType();
			}
		}
	}
		
	//outStream.write("</parameterList>\n");
	
	// Handle closing parenthesis
	//OutputXML(tokenizer.tokenType());
	tokenizer.advance();
	
	return count;
  }
  
  // Compiles a var declaration
  public int compileVarDec() throws IOException {
	
  	Boolean cont = true;
	int count = 0;
	
  	//outStream.write("<varDec>\n");
	
	// Print out first variable declaration
	//outStream.write("<keyword> " + tokenizer.keyWord() + " </keyword>\n");
	tokenizer.advance();
	
	String type;
	// Get type
	if(tokenizer.tokenType() == Token.IDENTIFIER) {
		type = tokenizer.identifier();
	}else{
		type = tokenizer.keyWord();
	}
	//OutputXML(tokenizer.tokenType(), Cat.TYPE, true);
	tokenizer.advance();
	//OutputXML(tokenizer.tokenType(), Cat.VAR, false);
	table.Define(tokenizer.identifier(), type, Kind.VAR, false);
	count++;
	tokenizer.advance();
	
	// Check if there are more
	if (tokenizer.symbol() == ';'){
		cont = false;
	}
	
	// If there are more variable declarations continue looping
	while (cont){
		//outStream.write("<symbol> " + tokenizer.symbol() + " </symbol>\n");
		tokenizer.advance();
		//OutputXML(tokenizer.tokenType(), Cat.VAR, false);
		table.Define(tokenizer.identifier(), type, Kind.VAR, false);
		count++;
		tokenizer.advance();
		
		if (tokenizer.symbol() == ';'){
			cont = false;
		}
	}
	
	// Handle semi-colon at end
	//OutputXML(tokenizer.tokenType());
	tokenizer.advance();
	
	//outStream.write("</varDec>\n");
	
	return count;
	
  }
  
  // Compiles a sentence of statements
  public void compileStatements() throws IOException {
	
	outStream.write("// Compiling statements\n");
	//outStream.write("<statements>\n");
	
	String kwd = tokenizer.keyWord();
	
	Boolean cont = true;
	while (cont){
		if (kwd.equals("let")){
					outStream.write("// Let statement\n");
					//outStream.write("<letStatement>\n");
					compileLet();
					outStream.write("// End let statement\n");
					//outStream.write("</letStatement>\n");
				} else if (kwd.equals("if")){
					outStream.write("// If statement\n");
					//outStream.write("<ifStatement>\n");
					compileIf();
					outStream.write("// End if statement\n");
					//outStream.write("</ifStatement>\n");
				} else if (kwd.equals("while")){
					outStream.write("// While statement\n");
					//outStream.write("<whileStatement>\n");
					compileWhile();
					outStream.write("// End while statement\n");
					//outStream.write("</whileStatement>\n");
				} else if (kwd.equals("do")){
					outStream.write("// Do statement\n");
					//outStream.write("<doStatement>\n");
					compileDo();
					outStream.write("// End do statement\n");
					//outStream.write("</doStatement>\n");
				} else if (kwd.equals("return")){
					outStream.write("// Return statement\n");
					//outStream.write("<returnStatement>\n");
					compileReturn();
					outStream.write("// End return statement\n");
					//outStream.write("</returnStatement>\n");
				} else {
					System.err.println("Error parsing statements.");
				}
				
				// Stopping condition if have reached a symbol, the closing }
				if (tokenizer.tokenType() == Token.SYMBOL) {
					cont = false;
				} else {
					kwd = tokenizer.keyWord();
				}
			}
  
	//outStream.write("</statements>\n");
  }
  
  // Compiles a do statement
  public void compileDo() throws IOException {
	
	// Print out the do keyword
	//OutputXML(tokenizer.tokenType());
	tokenizer.advance();
	
	compileSubroutine();
	
	// Get the ending semi-colon
	//OutputXML(tokenizer.tokenType());
	tokenizer.advance();
  }
  
  public void compileSubroutine() throws IOException {
	String name = "";
	
	// Print subroutine name or class name
	//OutputXML(tokenizer.tokenType(), Cat.SUB, true);
	if (tokenizer.tokenType() == Token.IDENTIFIER){
		name = tokenizer.identifier();
	}
	
	tokenizer.advance();
	
	// If we have an expression list
	if (tokenizer.symbol() == '('){
		// Print opening parenthesis
		//OutputXML(tokenizer.tokenType());
		tokenizer.advance();

		// Compile expression list
		int count = CompileExpressionList();
		writer.writeCall(name, count);
		
	} else if (tokenizer.symbol() == '.'){
		// Print the . symbol
		//OutputXML(tokenizer.tokenType());
		tokenizer.advance();
		
		// Print the subroutine name
		//OutputXML(tokenizer.tokenType(), Cat.SUB, true);
		name = name + "." + tokenizer.identifier();
		
		tokenizer.advance();
		
		// Print the opening parenthesis
		//OutputXML(tokenizer.tokenType());
		tokenizer.advance();
		
		// Compile the expression list
		int count = CompileExpressionList();
		
		writer.writeCall(name, count);
		writer.writePop(Segment.TEMP, 0);
	}
  }
  
  // Compiles a let statement
  public void compileLet() throws IOException {
	Segment dest = Segment.NONE;
	int destIndex = 0;
	
	// Print out the let keyword
	//OutputXML(tokenizer.tokenType());
	tokenizer.advance();
	
	// Print out the variable name
	//OutputXML(tokenizer.tokenType(), Cat.VAR, true);
	
	// Check to see if variable is in current scope
	if (table.symbols.containsKey(tokenizer.identifier())){
		if (table.KindOf(tokenizer.identifier(), false) == Kind.ARG) {
			dest = Segment.ARG;
		} else if (table.KindOf(tokenizer.identifier(), false) == Kind.VAR) {
			dest = Segment.LOCAL;
		}
		destIndex = table.IndexOf(tokenizer.identifier(), false);
	} else if (table.classSymbols.containsKey(tokenizer.identifier())){
		if (table.KindOf(tokenizer.identifier(), true) == Kind.STATIC) {
			dest = Segment.STATIC;
		} else if (table.KindOf(tokenizer.identifier(), true) == Kind.FIELD) {
			dest = Segment.THIS;
		}
		destIndex = table.IndexOf(tokenizer.identifier(), true);
	}
	
	//writer.writePush(dest, destIndex);
	
	tokenizer.advance();
	
	if (tokenizer.symbol() == '['){
		// Print out opening bracket
		OutputXML(tokenizer.tokenType());
		tokenizer.advance();
		// Compile expression
		CompileExpression();
		// Print out closing bracket
		OutputXML(tokenizer.tokenType());
		tokenizer.advance();
	}
	
	// Print out equals sign
	//OutputXML(tokenizer.tokenType());
	tokenizer.advance();
	
	// Compile expression
	CompileExpression();
	
	// Print out semi-colon
	//OutputXML(tokenizer.tokenType());
	tokenizer.advance();
	
	writer.writePop(dest, destIndex);
  }
  
  // Compiles a while statement
  public void compileWhile() throws IOException {
	
	// Create a label a beginning of while
	String labelName1 = "whileStart" + labelCount;
	String labelName2 = "whileEnd" + labelCount;
	labelCount++;
	writer.writeLabel(labelName1);
	
	// Print out the first keyword
	//OutputXML(tokenizer.tokenType());
	tokenizer.advance();
	
	// Print out opening parenthesis
	//OutputXML(tokenizer.tokenType());
	tokenizer.advance();
	
	// Compile the expression
	CompileExpression();
	
	// Check the opening condition by negation the expression
	writer.writeArithmetic(Command.NOT);
	writer.writeIf(labelName2);
	
	// Print out the closing parenthesis
	//OutputXML(tokenizer.tokenType());
	tokenizer.advance();
	
	// Print out the opening bracket
	//OutputXML(tokenizer.tokenType());
	tokenizer.advance();
	
	// Compile statements
	compileStatements();
	
	// Print out closing bracket
	//OutputXML(tokenizer.tokenType());
	tokenizer.advance();
	
	// Write the goto statement so loop will continue
	writer.writeGoto(labelName1);
	
	// Write out the label for the end of the while loop
	writer.writeLabel(labelName2);
  }
  
  // Compiles a return statement
  public void compileReturn() throws IOException {
	
	// Print out the first keyword
	//OutputXML(tokenizer.tokenType());
	tokenizer.advance();
	
	if (tokenizer.tokenType() == Token.SYMBOL){
		if (tokenizer.symbol() == ';'){
			// If it is returning void push zero, then return
			writer.writePush(Segment.CONST, 0);
			writer.writeReturn();
			
			// Print out the semi-colon
			//OutputXML(tokenizer.tokenType());
			tokenizer.advance();
		}
	} else {
		CompileExpression();
		
		writer.writeReturn();
		
		// Print out the semi-colon
		//OutputXML(tokenizer.tokenType());
		tokenizer.advance();
	}
  }
  
  // Compiles an if statement
  public void compileIf() throws IOException {
	
	// Define label variables for if
	String labelName1 = "ifEnd" + labelCount;
	String labelName2 = "elseEnd" + labelCount;
	labelCount++;
	
	// Print out the first keyword
	//OutputXML(tokenizer.tokenType());
	tokenizer.advance();
	
	// Print out opening parenthesis
	//OutputXML(tokenizer.tokenType());
	tokenizer.advance();
	
	// Compile the expression
	CompileExpression();
	
	// Negate the expression and check the condition
	writer.writeArithmetic(Command.NOT);
	writer.writeIf(labelName1);
	
	// Print out the closing parenthesis
	//OutputXML(tokenizer.tokenType());
	tokenizer.advance();
	
	// Print out the opening bracket
	//OutputXML(tokenizer.tokenType());
	tokenizer.advance();
	
	// Compile statements
	compileStatements();
	
	// Write statement to jump to end if statement held
	writer.writeGoto(labelName2);
	
	// Write the first label after the first block of code
	writer.writeLabel(labelName1);
	
	// Print out closing bracket
	//OutputXML(tokenizer.tokenType());
	tokenizer.advance();
	
	// If else statement advance until next keyword for now
	if (tokenizer.tokenType() == Token.KEYWORD && tokenizer.keyWord().equals("else")){
		// Print out the else
		//OutputXML(tokenizer.tokenType());
		tokenizer.advance();
		
		// Print out the opening bracket
		//OutputXML(tokenizer.tokenType());
		tokenizer.advance();
		
		// Compile statements
		compileStatements();
		
		// Print out closing bracket
		//OutputXML(tokenizer.tokenType());
		tokenizer.advance();
	}
	
	// Write the second label after the second block of code
	writer.writeLabel(labelName2);
	
  }
  
  // Compiles an expression
  public void CompileExpression() throws IOException {
	
	//outStream.write("<expression>\n");
	
	// Print out the first term
	CompileTerm();
	
	// Check type of next symbol
	char sym = tokenizer.symbol();
	
	while (sym == '+' || sym == '-' || sym == '*' || sym == '/' || sym == '&' || sym == '|' || sym == '<' || sym == '>' || sym == '='){
		// Print out operation
		//OutputXML(tokenizer.tokenType());
		tokenizer.advance();
		
		// Print out term
		CompileTerm();
		
		if (sym == '+') {
			writer.writeArithmetic(Command.ADD);
		} else if (sym == '-') {
			writer.writeArithmetic(Command.SUB);
		} else if (sym == '*') {
			writer.writeCall("Math.multiply", 2);
		} else if (sym == '/') {
			writer.writeCall("Math.divide", 2);
		} else if (sym == '&') {
			writer.writeArithmetic(Command.AND);
		} else if (sym == '|') {
			writer.writeArithmetic(Command.OR);
		} else if (sym == '<') {
			writer.writeArithmetic(Command.LT);
		} else if (sym == '>') {
			writer.writeArithmetic(Command.GT);
		} else if (sym == '=') {
			writer.writeArithmetic(Command.EQ);
		}
		
		// Check next symbol
		sym = tokenizer.symbol();
	}

	//outStream.write("</expression>\n");
  }
  
  // Compiles a term
  public void CompileTerm() throws IOException {
	
	//outStream.write("<term>\n");
	
	if(tokenizer.tokenType() == Token.SYMBOL && tokenizer.symbol() == '(') {
		//outStream.write("<symbol> " + "(" + " </symbol>\n");
		tokenizer.advance();
		CompileExpression();
		//outStream.write("<symbol> " + ")" + " </symbol>\n");
		tokenizer.advance();		
	} else if(tokenizer.tokenType() == Token.SYMBOL && (tokenizer.symbol() == '-' || tokenizer.symbol() == '~'	)) {
		char sym = tokenizer.symbol();
		//OutputXML(tokenizer.tokenType());
		tokenizer.advance();
		CompileTerm();
		if (sym == '-') {
			writer.writeArithmetic(Command.NEG);
		} else if (sym == '~') {
			writer.writeArithmetic(Command.NOT);
		}
	}else {
		String name = "";
		
		if(tokenizer.tokenType() == Token.IDENTIFIER) {
			//OutputXML(tokenizer.tokenType(), Cat.VAR, true);
			name = tokenizer.identifier();
			tokenizer.advance();
		}else if (tokenizer.tokenType() == Token.STRING_CONST) {
			OutputXML(tokenizer.tokenType());
			tokenizer.advance();
		} else if (tokenizer.tokenType() == Token.INT_CONST) {
			//OutputXML(tokenizer.tokenType());
			writer.writePush(Segment.CONST, tokenizer.intVal());
			tokenizer.advance();
		} else if (tokenizer.tokenType() == Token.KEYWORD) {
			String keyword = tokenizer.keyWord();
			
			// Handle keyword constants
			if (keyword.equals("true")) {
				writer.writePush(Segment.CONST, 1);
				writer.writeArithmetic(Command.NEG);
				
			}
			
			if (keyword.equals("false") || keyword.equals("null")) {
				writer.writePush(Segment.CONST, 0);
			}
			
			//OutputXML(tokenizer.tokenType());
			tokenizer.advance();
		} else {
			OutputXML(tokenizer.tokenType());
			tokenizer.advance();
		}
		
		
		if(tokenizer.tokenType() == Token.SYMBOL && tokenizer.symbol() == '[') {
			outStream.write("<symbol> " + tokenizer.symbol() + " </symbol>\n");
			tokenizer.advance();
			CompileExpression();
			outStream.write("<symbol> " + tokenizer.symbol() + " </symbol>\n");
			tokenizer.advance();
		}else if(tokenizer.tokenType() == Token.SYMBOL && tokenizer.symbol() == '('){
			outStream.write("<symbol> " + tokenizer.symbol() + " </symbol>\n");
			tokenizer.advance();
			CompileExpressionList();
			outStream.write("<symbol> " + tokenizer.symbol() + " </symbol>\n");
			tokenizer.advance();
		}else if(tokenizer.tokenType() == Token.SYMBOL && tokenizer.symbol() == '.') {
			//outStream.write("<symbol> " + tokenizer.symbol() + " </symbol>\n");
			tokenizer.advance();
			//OutputXML(tokenizer.tokenType(), Cat.SUB, true);
			name = name + "." + tokenizer.identifier();
			tokenizer.advance();
			//outStream.write("<symbol> " + tokenizer.symbol() + " </symbol>\n");
			tokenizer.advance();
			int count = CompileExpressionList();
			
			// Call the subroutine
			writer.writeCall(name, count);
			
		} else {
			Segment dest = Segment.NONE;
			int destIndex = 0;
			// Check to see if variable is in current scope
			if (table.symbols.containsKey(name)){
				if (table.KindOf(name, false) == Kind.ARG) {
					dest = Segment.ARG;
				} else if (table.KindOf(name, false) == Kind.VAR) {
					dest = Segment.LOCAL;
				}
				destIndex = table.IndexOf(name, false);
				writer.writePush(dest, destIndex);
			} else if (table.classSymbols.containsKey(name)){
				if (table.KindOf(name, true) == Kind.STATIC) {
					dest = Segment.STATIC;
				} else if (table.KindOf(name, true) == Kind.FIELD) {
					dest = Segment.THIS;
				}
				destIndex = table.IndexOf(name, true);
				writer.writePush(dest, destIndex);
			}
	
			
			//outStream.write("here\n");
		}
	}
	//outStream.write("</term>\n");
  }
  
  // Compiles a comma-separated list of expressions
  public int CompileExpressionList() throws IOException {
	int count = 0;
	
	outStream.write("// Compiling expression list\n");
	//outStream.write("<expressionList>\n");
	
	Boolean cont = true;
	token_type = tokenizer.tokenType();
	
	// If parameter list is empty, handle and return
	if (token_type == Token.SYMBOL){
		if (tokenizer.symbol() == ')'){
			//outStream.write("</expressionList>\n");
			//OutputXML(token_type);
			tokenizer.advance();
			return count;
		}
	}
	
	// Otherwise loop through and print out the parameter list
	while (cont){
    	//returns token's corresponding XML line
		CompileExpression();
		count++;
		
		token_type = tokenizer.tokenType();
		if (token_type == Token.SYMBOL){
			if (tokenizer.symbol() == ')'){
				cont = false;
			} else if (tokenizer.symbol() == ','){
				//OutputXML(tokenizer.tokenType());
				tokenizer.advance();
			}
		}
	}
		
	//outStream.write("</expressionList>\n");
	
	// Handle closing parenthesis
	//OutputXML(tokenizer.tokenType());
	tokenizer.advance();
	
	return count;
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
  
  public void OutputXML(Token token_type, Cat c, Boolean used) throws IOException {
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
				outStream.write("<identifier> " + tokenizer.identifier() + " CAT=" + c + " USED=" + used + " </identifier>\n");
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

  public void printTable(HashMap<String, Entry> map) {
	System.out.println("Printing table hopefully printing...");
	Iterator iterator = map.keySet().iterator();  
	
	while (iterator.hasNext()) {  
	   String key = iterator.next().toString();  
	   Entry value = map.get(key);  

	   System.out.println(key + " " + value.type + " " + value.kind + " " + value.index);
	}
	
	System.out.println();
}
}