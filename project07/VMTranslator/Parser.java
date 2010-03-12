// Parser.java

package VMTranslator;

import java.io.*;

public class Parser {

  public enum Command {
    C_ARITHEMETIC, C_PUSH, C_POP, C_LABEL, C_GOTO, C_IF, C_FUNCTION, C_RETURN, C_CALL
  }

  // Parses the input stream
  public Parser(BufferedReader stream) throws IOException {
  }

  // Are there more comamnds in input?
  public Boolean hasMoreCommands() {
    return true;
  }

  // Reads next command and makes it current command
  public void advance() {

  }

  // Returns the type of the current VM command
  public Command commandType() {
    return Command.C_CALL;
  }

  // Returns the first argument of the current command
  public String arg1() {
    return null;
  }

  // Returns the second argument of the current command
  public Integer arg2() {
    return 0;
  }
}