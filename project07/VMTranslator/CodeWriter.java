// CodeWriter.java

package VMTranslator;

import java.io.*;
import VMTranslator.Parser;

public class CodeWriter {

  // Gets ready to write to output stream
  public CodeWriter(BufferedWriter stream) throws IOException {

  }

  // Informs the code writer that translation of a new VM file is started
  public void setFileName(String fileName) {

  }

  // Writes the assembly code translation of given command
  public void writeArithemetic(String command) {

  }

  // Writes the assembly code translation of given command, when command is C_PUSH or C_POP
  public void WritePushPop(Parser.Command command, String segment, Integer index) {

  }

  // Closes output file
  public void Close() {

  }

}