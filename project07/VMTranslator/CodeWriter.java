// CodeWriter.java

package VMTranslator;

import java.io.*;
import VMTranslator.Parser;
import VMTranslator.templates.*;

public class CodeWriter {
  public OutputStreamWriter outStream;
  public Integer count;

  // Gets ready to write to output stream
  public CodeWriter(OutputStreamWriter stream) throws IOException {
    outStream = stream;
    count = 0;
  }

  // Informs the code writer that translation of a new VM file is started
  public void setFileName(String fileName) {

  }

  // Writes the assembly code translation of given command
  public void writeArithemetic(String command) throws Exception {
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
      if (segment.equals("constant")){
        new PushConstantTemplate().render(outStream, index);
      } else {

      }
    }
    else if (command == Parser.Command.C_POP){

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