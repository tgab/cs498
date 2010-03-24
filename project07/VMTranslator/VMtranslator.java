// VMtranslator.java
// by Thea Gab
// Compile: (from directory with VMTranslator folder)
//  javac VMTranslator/*.java
// Usage: (from directory with VMTranslator folder)
//  java VMTranslator/VMtranslator <source>

package VMTranslator;

import java.io.*;
import java.util.StringTokenizer;

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

      // Create file stream for output
      // TODO: add testing for if file exists already, ask user before overwriting
      //File out = new File(output);
      //while (out.exists()){
      //  System.out.println(out + " already exists.  overwrite? (y/n)"); 
      //}
      BufferedWriter asm = new BufferedWriter(new FileWriter(output));

      // Create file stream for input
      BufferedReader vm = null;
      try {
        vm = new BufferedReader(new FileReader(source));
      }
      catch (FileNotFoundException e) {
        System.err.println(source + ": does not exist or cannot be read");
        System.exit(1);
      }

      // Close file stream
      vm.close();

      asm.write("test");

      // Close file stream
      asm.close();

      System.out.println("Output written to " + output);
    }

  }
}

