// VMWriter.java

package JackCompiler;
import java.io.OutputStream;



public class VMWriter {
	public enum Segment {
		CONST, ARG, LOCAL, STATIC, THIS, THAT, POINTER, TEMP
	}
	
	public enum Command {
		ADD, SUB, NEG, EQ, GT, LT, AND, OR, NOT
	}

	// Creates a new file and prepares it for writing
	public VMWriter () {
	
	}
	
	// Writes a VM push command
	public void writePush (Segment s, int index) {
	
	}
	
	// Writes a VM pop command
	public void writePop (Segment s, int index) {
	
	}
	
	// Writes a VM arithmetic command
	public void writeArithmetic (Command c) {
	
	}
	
	// Writes a VM label command
	public void writeLabel (String label) {
		
	}
	
	// Writes a VM goto command
	public void writeGoto (String label) {
	
	}
	
	// Writes a VM if-goto command
	public void writeIf (String label) {
	
	}
	
	// Writes a VM call command
	public void writeCall (String name, int nArgs) {
	
	}
	
	// Writes a VM function command
	public void writeFunction (String name, int nLocals) {
	
	}
	
	// Writes a VM return command
	public void writeReturn () {
	
	}
	
	// Closes the output file
	public void close() {
	
	}
}