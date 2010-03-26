// CodeWriter.java

package VMTranslator;

import java.io.*;
import VMTranslator.Parser;
import VMTranslator.templates.*;

public class CodeWriter {
  public OutputStreamWriter outStream;
  public Integer count;
  public String file;

  // Gets ready to write to output stream
  public CodeWriter(OutputStreamWriter stream) throws IOException {
    outStream = stream;
    count = 0;
  }

  // Informs the code writer that translation of a new VM file is started
  public void setFileName(String fileName) {
    file = fileName;
  }

  // Writes the assembly code translation of given command
  public void writeArithemetic(String command) throws Exception {
    // Find correct template for given arithemetic command
    if (command.equals("add")){
      new AddTemplate().render(outStream);
    }
    else if (command.equals("sub")){
      new SubTemplate().render(outStream);
    }
    else if (command.equals("neg")){
      new NegTemplate().render(outStream);
    }
    else if (command.equals("eq")){
      new EqTemplate().render(outStream, count);
      count++;
    }
    else if (command.equals("gt")){
      new GtTemplate().render(outStream, count);
      count++;
    }
    else if (command.equals("lt")){
      new LtTemplate().render(outStream, count);
      count++;
    }
    else if (command.equals("and")){
      new AndTemplate().render(outStream);
    }
    else if (command.equals("or")){
      new OrTemplate().render(outStream);
    }
    else if (command.equals("not")){
      new NotTemplate().render(outStream);
    }
    else {
      System.err.println("Invalid command");
      System.exit(1);
    }
  }

  // Writes the assembly code translation of given command, when command is C_PUSH or C_POP
  public void writePushPop(Parser.Command command, String segment, Integer index) throws Exception {
    if (command == Parser.Command.C_PUSH){
      // Find correct template for given push command and pass in parameters
      if (segment.equals("constant")){
        new PushConstantTemplate().render(outStream, index);
      }
      else if (segment.equals("local")){
        new PushTemplate().render(outStream, "LCL", index);
      }
      else if (segment.equals("argument")){
        new PushTemplate().render(outStream, "ARG", index);
      }
      else if (segment.equals("this")){
        new PushTemplate().render(outStream, "THIS", index);
      }
      else if (segment.equals("that")){
        new PushTemplate().render(outStream, "THAT", index);
      }
      else if (segment.equals("temp")){
        new PushTempTemplate().render(outStream, "5", index);
      }
      else if (segment.equals("pointer")){
        new PushTempTemplate().render(outStream, "3", index);
      }
      else if (segment.equals("static")){
        if (file.equals(null)){
          System.err.println("Must set file name");
          System.exit(1);
        }
        new PushStaticTemplate().render(outStream, file, index);
      }
    }
    else if (command == Parser.Command.C_POP){
      // Find correct template for given pop command and pass in parameters
      if (segment.equals("local")){
        new PopTemplate().render(outStream, "LCL", index);
      }
      else if (segment.equals("argument")){
        new PopTemplate().render(outStream, "ARG", index);
      }
      else if (segment.equals("this")){
        new PopTemplate().render(outStream, "THIS", index);
      }
      else if (segment.equals("that")){
        new PopTemplate().render(outStream, "THAT", index);
      }
      else if (segment.equals("temp")){
        new PopTempTemplate().render(outStream, "5", index);
      }
      else if (segment.equals("pointer")){
        new PopTempTemplate().render(outStream, "3", index);
      }
      else if (segment.equals("static")){
        if (file.equals(null)){
          System.err.println("Must set file name");
          System.exit(1);
        }
        new PopStaticTemplate().render(outStream, file, index);
      }
    }
    else {
      System.err.println("Error: invalid push or pop");
      System.exit(1);
    }
  }

  // Closes output file
  public void Close() {

  }

}