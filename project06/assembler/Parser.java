// Parser.java
// Reads an assembly language command, parses it, and provides
// convenient access to the command's components (fields and
// symbols).  In addition, removes all white space and comments.

package assembler;

import java.io.*;
import java.util.ArrayList;
import assembler.Code;

public class Parser {
  public ArrayList<String> commands;
  public int counter;
  public String currentCommand;
  public enum Command {
    A_COMMAND, C_COMMAND, L_COMMAND
  }

  // constructor- opens the input file/stream and gets ready to
  // parse it
  public Parser(BufferedReader stream) throws IOException {
    commands = new ArrayList<String>();
    String line = stream.readLine();
    Boolean check = commands.add(line);
    while (line != null){
      // Read next line
      line = stream.readLine();
      check = commands.add(line);
      if (check == false){
        System.err.println("Error adding line to commands list");
      }
    }
    counter = 0;
    currentCommand = commands.get(counter);
  }

  // checks if there are more commands in the input
  public Boolean hasMoreCommands(){
    if (counter < commands.size()-2) {
      return true;
    } else {
      return false;
    }
  }

  // reads the next command from the input and makes it the
  // current command
  public void advance() {
    counter++;
    currentCommand = commands.get(counter);
  }

  // returns the type of the current command
  public Command commandType() {
    // Checks command type (*** change to regular expressions)
    if (currentCommand.startsWith("//") || currentCommand.length() == 0)
      return null;
    else if (currentCommand.startsWith("@"))
      return Command.A_COMMAND;
    else if (currentCommand.startsWith("("))
      return Command.L_COMMAND;
    else
      return Command.C_COMMAND;
  }

  // returns symbol or decimal of current command for A and
  // L commands
  public String symbol() {
    Command type = commandType();

    // If command is A type return everything after the @
    if (type == Command.A_COMMAND){
      return currentCommand.substring(1, currentCommand.length());
    }
    // If command is L type return everything between the () 
    else if (type == Command.L_COMMAND){
      return currentCommand.substring(1, currentCommand.length() - 1);
    } 
    // Otherwise print an error, should be L or A command
    else {
      System.err.println("Illegal parsing function for " + type);
      System.exit(1);
    }

    return null;
  }

  // returns dest from the C command
  public String dest(){
    Command type = commandType();
    if (type != Command.C_COMMAND){
      System.err.println("Illegal parsing function for " + type);
      System.exit(1);
    }
    
    int index = currentCommand.indexOf("=");
    if (index == -1)
      return "null";
    else {
      String dest = currentCommand.substring(0, index);
      return dest;
    }
  }

  // returns comp from the C command
  public String comp(){
    Command type = commandType();
    if (type != Command.C_COMMAND){
      System.err.println("Illegal parsing function for " + type);
      System.exit(1);
    }

    String comp = "null";
    int index1 = currentCommand.indexOf("=");
    //if (index1 != -1){
      comp = currentCommand.substring(index1+1, currentCommand.length());
      int index2 = comp.indexOf(";");
      if (index2 != -1){
        comp = currentCommand.substring(0, index2);
      }
    //}
    return comp;
  }

  // returns jump from the C command
  public String jump(){
    Command type = commandType();
    if (type != Command.C_COMMAND){
      System.err.println("Illegal parsing function for " + type);
      System.exit(1);
    }

    int index = currentCommand.indexOf(";");
    if (index == -1)
      return "null";
    else {
      String jump = currentCommand.substring(index+1, currentCommand.length());
      return jump;
    }
  }

  public String parseCommand(){
    String command = null;
    if (counter == commands.size()-1) {
      System.err.println("Out of bounds, beyond commands");
      System.exit(1);
    }

    Command type = commandType();
    System.out.println(currentCommand);
    System.out.println(type);

    if (type == Command.C_COMMAND) {
      Code code = new Code();

      // Get the binary for comp and check for errors
      System.out.println("Comp: " + comp());
      String comp = code.comp(comp());
      if (comp == null) {
        System.err.println("Line " + counter + ": comp symbol not recognized");
        System.exit(1);
      }

      // Get the binary for dest and check for errors
      System.out.println("Dest: " + dest());
      String dest = code.dest(dest());
      if (dest == null) {
        System.err.println("Line " + counter + ": dest symbol not recognized");
        System.exit(1);
      }

      // Get the binary for jump and check for errors
      String jump = code.jump(jump());
      System.out.println("Jump: " + jump());
      if (jump == null) {
        System.err.println("Line " + counter + ": jump symbol not recognized");
        System.exit(1);
      }

      // If there are no errors, put together the command
      command = "111" + comp + dest + jump;
    } else if (type == Command.A_COMMAND) {
      String symbol = symbol();
      if (symbol == null) {
        System.err.println("Line " + counter + ": symbol not recognized");
        System.exit(1);
      }
      String z = "0000000000000000";
      String bin = Integer.toBinaryString(Integer.parseInt(symbol));
      command = z.substring(0, 16-bin.length()) + bin;
      //command = "0" + Integer.toBinaryString(Integer.parseInt(symbol));
    } else if (type == Command.L_COMMAND) {

    }
    // Return the command
    return command;
  }

}