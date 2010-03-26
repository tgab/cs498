// VMtranslator.java
// by Thea Gab
// Compile: (from directory with VMTranslator folder)
//  javac VMTranslator/*.java
// Usage: (from directory with VMTranslator folder)
//  java VMTranslator/VMtranslator <source>
// Other Requirements:
//  must have template/ directory in the VMTranslator directory
//  can compile with command (from directory containing VMTranslator folder):
//  java org.jamon.compiler.TemplateProcessor --destDir=. VMTranslator/templates/*.jamon

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
      
      // Create file stream for output
      // TODO: add testing for if file exists already, ask user before overwriting
      //File out = new File(output);
      //while (out.exists()){
      //  System.out.println(out + " already exists.  overwrite? (y/n)"); 
      //}

      OutputStreamWriter asm = new OutputStreamWriter(new FileOutputStream(output));

      // Create code writer
      CodeWriter writer = new CodeWriter(asm);

      try {
        VMtranslator trans = new VMtranslator();
        trans.translate(source, filename, writer);
      } catch (IOException e){
        System.out.println("Error trying to read file");
        System.exit(1);
      }

      // Close file stream
      asm.close();

      System.out.println("Output written to " + output);


    }
    else {
      // Make sure directory name does not end with /
      if (! source.endsWith("/")){
        source = source + "/";
      }

      // Check if directory exists
      File dir = new File(source);
      if (!dir.exists()){
        System.err.println(source + ": does not exist");
        System.exit(1);
      }

      // Check if directory can be opened and list of files obtained
      String[] files = dir.list();
      if (files == null){
        System.err.println(source + ": is not a directory");
        System.exit(1);
      }


      // Parse out filename for asm file
      int last = source.lastIndexOf("/");
      String output = source.substring(0, source.length()-1) + ".asm";
      System.out.println(output);

      OutputStreamWriter asm = new OutputStreamWriter(new FileOutputStream(output));

      // Create code writer
      CodeWriter writer = new CodeWriter(asm);


      // Loop through all the files in the specified directory
      for (int i=0; i < files.length; i++){
        String filename = files[i];

        // If the file is a .vm file, translate it
        if (filename.endsWith(".vm")){
          try {
            VMtranslator trans = new VMtranslator();
            trans.translate(source + filename, filename, writer);
          } catch (IOException e){
            System.out.println("Error trying to read file");
            System.exit(1);
          }
        }
      }

      // Close file stream
      asm.close();

      System.out.println("Output written to " + output);

    } //END else
  } //END MAIN

  public void translate(String source, String filename, CodeWriter writer) throws IOException{

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

      // Set file name of code writer
      writer.setFileName(filename);

      // Loop through commands and handle each one
      while (parser.hasMoreCommands()) {
        parser.handleCommand(writer);
        parser.advance();
      }

      // Handle last command
      parser.handleCommand(writer);
  }
}

