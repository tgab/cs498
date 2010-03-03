// Assembler.java
// by Thea Gab
// Compile: javac Assembler.java
// Usage: java Assembler.java <filename.asm> <filename.hack>

import java.io.*;

public class Assembler {

  public static void main(String[] args) throws IOException{
    // Check input arguments
    if (args.length != 2) {
      System.err.println("Usage: java Assembler <file.asm> <file.hack>");
      System.exit(1);
    }

    // Create file stream for input
    BufferedReader asm = new BufferedReader(new FileReader(args[0]));

    // Get first line of file
    String line = asm.readLine();
    while (line != null){
      // Print line
      System.out.println(line);

      // Read next line
      line = asm.readLine();
    }

    // Close file stream
    asm.close();

    // Create file stream for output (***add testing for file existence, writeability, etc)
    Writer hack = new BufferedWriter(new FileWriter(args[1]));

    // Write test output to hack
    hack.write("test");

    // Close file stream
    hack.close();
  }
}