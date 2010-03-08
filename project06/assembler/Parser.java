// Parser.java
// Reads an assembly language command, parses it, and provides
// convenient access to the command's components (fields and
// symbols).  In addition, removes all white space and comments.

package assembler;

import java.io.*;
import java.util.ArrayList;
import assembler.Code;
import assembler.SymbolTable;

public class Parser {
  public ArrayList<String> commands;
  public int counter;
  public String currentCommand;
  public int binaryCount;
  public int variableCount;
  public Boolean Debug = false;

  public enum Command {
    A_COMMAND, C_COMMAND, L_COMMAND
  }

  // constructor- opens the input file/stream and gets ready to
  // parse it
  public Parser(BufferedReader stream) throws IOException {
    commands = new ArrayList<String>();
    String line = stream.readLine();
    //Boolean check = commands.add(line);
    while (line != null){
      // Add line to array

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
    comp = currentCommand.substring(index1+1, currentCommand.length());
    int index2 = comp.indexOf(";");
    if (index2 != -1){
      comp = currentCommand.substring(0, index2);
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
      return "null";
    else {
      String jump = currentCommand.substring(index+1, currentCommand.length());
      return jump;
    }
  }

  public void firstPass(SymbolTable symbolTable){
    String command = currentCommand;
    Command type = commandType();

    //System.out.println("First pass: " + command);

    ///Currently ignores first line!!!!
    while (hasMoreCommands()){
      advance();
      command = currentCommand;
      type = commandType();
      
      if (Debug) System.out.println("First pass: " + command);

      if (type == Command.L_COMMAND){
        String symbol = symbol();

        String z = "0000000000000000";
        String bin = Integer.toBinaryString(new Integer(binaryCount));
        String address = z.substring(0, 16-bin.length()) + bin;

        symbolTable.addEntry(symbol, address);

      } else if (type == Command.A_COMMAND){
        // I chose to ignore the A_COMMANDS on the first pass and handle them on second pass
        binaryCount++;
      } else if (type == Command.C_COMMAND){
        binaryCount++;
      }
    }

    reset();
  }

  public void reset(){
    counter = 0;
    binaryCount = 0;
    variableCount = 16;
    currentCommand = commands.get(counter);
  }

  public String parseCommand(SymbolTable symbolTable){
    String command = null;
    if (counter == commands.size()) {
      System.err.println("Out of bounds, beyond commands");
      System.exit(1);
    }

    Command type = commandType();

    // DEBUG
    if (Debug) System.out.println("Current command: " + currentCommand);
    if (Debug) System.out.println("Type: " + type);

    if (type == Command.C_COMMAND) {
      Code code = new Code();

      // Get the binary for comp and check for errors
      if (Debug) System.out.println("Comp: " + comp());
      String comp = code.comp(comp());
      if (comp == null) {
        System.err.println("Line " + counter + ": comp symbol not recognized");
        System.exit(1);
      }

      // Get the binary for dest and check for errors
      if (Debug) System.out.println("Dest: " + dest());
      String dest = code.dest(dest());
      if (dest == null) {
        System.err.println("Line " + counter + ": dest symbol not recognized");
        System.exit(1);
      }

      // Get the binary for jump and check for errors
      String jump = code.jump(jump());
      if (Debug) System.out.println("Jump: " + jump());
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
      // Check to see if symbol is in table, otherwise assume it is a number
      if (symbolTable.contains(symbol)){
        command = symbolTable.getAddress(symbol);
      } else {
        try {
          // Try to parse integer if symbol isn't in table
          String z = "0000000000000000";
          String bin = Integer.toBinaryString(Integer.parseInt(symbol));
          command = z.substring(0, 16-bin.length()) + bin;

        } catch (NumberFormatException e) {

          // If it is not an integer then add the symbol to table with address
          // of new variable
          String z = "0000000000000000";
          String bin = Integer.toBinaryString(variableCount);
          String address = z.substring(0, 16-bin.length()) + bin;
          symbolTable.addEntry(symbol, address);

          // Increase variable count so next variable will be written to next
          // available space
          variableCount++;

          // The current command will be the address assigned to the symbol
          command = address;
        }
      }
    } else if (type == Command.L_COMMAND) {
      //ignore L_COMMANDS on second pass
    }
    // Return the command
    return command;
  }

}