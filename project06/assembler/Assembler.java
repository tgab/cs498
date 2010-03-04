// Assembler.java
// by Thea Gab
// Compile: javac Assembler.java
// Usage: java Assembler.java <filename.asm> <filename.hack>

import java.io.*;
import java.util.StringTokenizer;

public class Assembler {

  public static void main(String[] args) throws IOException{
    // Check input arguments
    // Check that file is proper file type
    if (args.length != 1 || args[0].endsWith(".asm") != true) {
      System.err.println("Usage: java Assembler <Prog.asm>");
      System.exit(1);
    }

    String inputFile = args[0];

    // Create file stream for input
    BufferedReader asm = null;
    try {
      asm = new BufferedReader(new FileReader(inputFile));

    }
    catch (FileNotFoundException e) {
      System.err.println(inputFile + ": does not exist or cannot be read");
      System.exit(1);
    }

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

    // Parse out filename for hack file
    StringTokenizer inputTokens = new StringTokenizer(inputFile, "/");
    String fileName = null;
    while (inputTokens.hasMoreElements()) {
      fileName = inputTokens.nextToken();
    }
    fileName = fileName.substring(0, fileName.length()-4) + ".hack";
    System.out.println(fileName);

    // Create file stream for output (***add testing for file existence, writeability, etc)
    Writer hack = new BufferedWriter(new FileWriter(fileName));

    // Write test output to hack
    hack.write("test");

    // Close file stream
    hack.close();
  }
}