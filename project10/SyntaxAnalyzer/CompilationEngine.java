// CompilationEngine.java

package SyntaxAnalyzer;

import java.io.*;

public class CompilationEngine {
  public OutputStreamWriter outStream;
  public JackTokenizer tokenizer;
  public JackTokenizer.Token token;

  // Creates a new compilation engine
  public CompilationEngine(JackTokenizer token, OutputStreamWriter stream) throws IOException {
    outStream = stream;
	tokenizer = token;
  }

  // Compiles a complete class
  public void CompileClass() {
    // Loop through tokens and handle each one
    while (tokenizer.hasMoreTokens()) {
      token = tokenizer.tokenType();
      tokenizer.advance();
    }

    // Handle last token
    token = tokenizer.tokenType();
  
  }
  
  // Compiles a static declaration or a field declaration
  public void CompileClassVarDec() {
  
  }
  
  // Compiles a complete method, function, or constructor
  public void CompileSubroutine() {
  
  }
  
  // Compiles a parameter list
  public void compileParameterList() {
  
  }
  
  // Compiles a var declaration
  public void compileVarDec() {
  
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

}