// Parser.java

package VMTranslator;

import java.io.*;
import java.util.ArrayList;

public class Parser {

  public ArrayList<String> commands;
  public int counter;
  public String currentCommand;
  public int binaryCount;
  public int variableCount;
  public Boolean Debug = false;

  public enum Command {
    C_ARITHEMETIC, C_PUSH, C_POP, C_LABEL, C_GOTO, C_IF, C_FUNCTION, C_RETURN, C_CALL
  }

  // Parses the input stream
  public Parser(BufferedReader stream) throws IOException {
    commands = new ArrayList<String>();
    String line = stream.readLine();
    //Boolean check = commands.add(line);
    while (line != null){
      // Get rid of comments
      String[] split = line.split("//");
      line = split[0];

	// Get rid of whitespace
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

      // Add line to array
      Boolean check = commands.add(line);
      if (check == false){
        System.err.println("Error adding line to commands list");
      }

      // Read next line
      line = stream.readLine();
    }
    counter = 0;
    binaryCount = 0;
    variableCount = 16;
    currentCommand = commands.get(counter);
  }

  // Are there more comamnds in input?
  public Boolean hasMoreCommands() {
    if (counter < commands.size()-1) {
      return true;
    } else {
      return false;
    }
  }

  // Reads next command and makes it current command
  public void advance() {
    counter++;
    currentCommand = commands.get(counter);
  }

  // Returns the type of the current VM command
  public Command commandType() {
    // Checks command type
    // TODO: change to regular expression
    if (currentCommand.startsWith("//") || currentCommand.length() == 0)
      return null;
    else if (currentCommand.startsWith("push"))
      return Command.C_PUSH;
    else if (currentCommand.startsWith("pop"))
      return Command.C_POP;
    else if (currentCommand.startsWith("label"))
      return Command.C_LABEL;
    else if (currentCommand.startsWith("goto"))
      return Command.C_GOTO;
    else if (currentCommand.startsWith("if-goto"))
      return Command.C_IF;
    else if (currentCommand.startsWith("function"))
      return Command.C_FUNCTION;
    else if (currentCommand.startsWith("call"))
      return Command.C_CALL;
    else if (currentCommand.startsWith("return"))
      return Command.C_RETURN;
    else
      return Command.C_ARITHEMETIC;
  }

  // Returns the first argument of the current command
  public String arg1() {
    String [] args = currentCommand.split(" ");
    return args[1];
  }

  // Returns the second argument of the current command
  public Integer arg2() {
    String [] args = currentCommand.split(" ");
    return Integer.parseInt(args[2]);
  }
}