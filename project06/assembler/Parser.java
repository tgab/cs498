// Parser.java
// Reads an assembly language command, parses it, and provides
// convenient access to the command's components (fields and
// symbols).  In addition, removes all white space and comments.

import java.io.*;
import java.util.ArrayList;

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
    if (counter < commands.size()-1) {
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
    if (currentCommand.startsWith("//"))
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
      return null;
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

    String comp = null;
    int index1 = currentCommand.indexOf("=");
    if (index1 != -1){
      comp = currentCommand.substring(index1, currentCommand.length());
      int index2 = comp.indexOf(";");
      if (index2 != -1){
        comp = currentCommand.substring(0, index2);
      }
    }
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
      return null;
    else {
      String jump = currentCommand.substring(index, currentCommand.length());
      return jump;
    }
  }

}