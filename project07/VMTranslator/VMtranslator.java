// VMtranslator.java
// by Thea Gab
// Compile: (from directory with VMTranslator folder)
//  javac VMTranslator/*.java
// Usage: (from directory with VMTranslator folder)
//  java VMTranslator/VMtranslator <source>

package VMTranslator;

import java.io.*;
import java.util.StringTokenizer;
import VMTranslator.CodeWriter;

public class VMtranslator {

  public static void main(String[] args) throws IOException{
    // Check input arguments
    if (args.length != 1) {
      System.err.println("Usage: java VMtranslator <source>");
      System.exit(1);
    }

    // Save input file/directory name
    String source = args[0];

    if (source.endsWith(".vm") == true) {

      // Parse out filename for asm file
      String output = source.substring(0, source.length()-3) + ".asm";
      String name = source.substring(0, source.length()-3);
      String [] parts = name.split("/");
      String filename = parts[parts.length-1];
      System.out.println(filename);

      // Create file stream for output
      // TODO: add testing for if file exists already, ask user before overwriting
      //File out = new File(output);
      //while (out.exists()){
      //  System.out.println(out + " already exists.  overwrite? (y/n)"); 
      //}
      OutputStreamWriter asm = new OutputStreamWriter(new FileOutputStream(output));

      // Create file stream for input
      BufferedReader vm = null;
      try {
        vm = new BufferedReader(new FileReader(source));
      }
      catch (FileNotFoundException e) {
        System.err.println(source + ": does not exist or cannot be read");
        System.exit(1);
      }



      Parser parser = null;

      try {
        parser = new Parser(vm);
      }
      catch (IOException e) {
        System.err.println(source + ": error while reading file");
      }

      // Close file stream
      vm.close();

      // Create code writer
      CodeWriter writer = new CodeWriter(asm);
      writer.setFileName(filename);

      // Loop through commands and handle each one
      while (parser.hasMoreCommands()) {
        parser.handleCommand(writer);
        parser.advance();
      }

      // Handle last command
      parser.handleCommand(writer);

      // Close file stream
      asm.close();

      System.out.println("Output written to " + output);
    }

  }
}

