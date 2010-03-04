// Parser.java
// Reads an assembly language command, parses it, and provides
// convenient access to the command's components (fields and
// symbols).  In addition, removes all white space and comments.

import java.io.*;

public class Parser {
  public String currentCommand;
  public enum Command {
    A_COMMAND, C_COMMAND, L_COMMAND
  }

  // constructor- opens the input file/stream and gets ready to
  // parse it
  public Parser(BufferedReader stream) {

  }

  // checks if there are more commands in the input
  public Boolean hasMoreCommands(){
    return true;
  }

  // reads the next command from the input and makes it the
  // current command
  public void advance() {

  }

  // returns the type of the current command
  public Command commandType() {
    return Command.L_COMMAND;
  }

  // returns symbol or decimal of current command for A and
  // L commands
  public String symbol() {
    return null;
  }

  // returns dest from the C command
  public String dest(){
    return null;
  }

  // returns comp from the C command
  public String comp(){
    return null;
  }

  // returns jump from the C command
  public String jump(){
    return null;
  }

}