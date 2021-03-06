// VMWriter.java

package JackCompiler;
import java.io.OutputStream;



import java.io.*;

public class VMWriter {
	public OutputStreamWriter outStream;
	
	public enum Segment {
		CONST, ARG, LOCAL, STATIC, THIS, THAT, POINTER, TEMP, NONE
	}
	
	public enum Command {
		ADD, SUB, NEG, EQ, GT, LT, AND, OR, NOT
	}

	// Creates a new file and prepares it for writing
	public VMWriter (OutputStreamWriter stream) throws IOException {
		outStream = stream;
	}
	
	// Writes a VM push command
	public void writePush (Segment s, int index) throws IOException {
		switch (s){
			case CONST: outStream.write("push constant " + index + "\n"); break;
			case ARG: outStream.write("push argument " + index + "\n"); break;
			case LOCAL: outStream.write("push local " + index + "\n"); break;
			case STATIC: outStream.write("push static " + index + "\n"); break;
			case THIS: outStream.write("push this " + index + "\n"); break;
			case THAT: outStream.write("push that " + index + "\n"); break;
			case POINTER: outStream.write("push pointer " + index + "\n"); break;
			case TEMP: outStream.write("push temp " + index + "\n"); break;
			default: outStream.write("push " + index + "\n"); break;
		}
	}
	
	// Writes a VM pop command
	public void writePop (Segment s, int index) throws IOException {
		switch (s) {
			case CONST: outStream.write("pop constant " + index + "\n"); break;
			case ARG: outStream.write("pop argument " + index + "\n"); break;
			case LOCAL: outStream.write("pop local " + index + "\n"); break;
			case STATIC: outStream.write("pop static " + index + "\n"); break;
			case THIS: outStream.write("pop this " + index + "\n"); break;
			case THAT: outStream.write("pop that " + index + "\n"); break;
			case POINTER: outStream.write("pop pointer " + index + "\n"); break;
			case TEMP: outStream.write("pop temp " + index + "\n"); break;
		}
	}
	
	// Writes a VM arithmetic command
	public void writeArithmetic (Command c) throws IOException {
		switch (c) {
			case ADD: outStream.write("add\n"); break;
			case SUB: outStream.write("sub\n"); break;
			case NEG: outStream.write("neg\n"); break;
			case EQ: outStream.write("eq\n"); break;
			case GT: outStream.write("gt\n"); break;
			case LT: outStream.write("lt\n"); break;
			case AND: outStream.write("and\n"); break;
			case OR: outStream.write("or\n"); break;
			case NOT: outStream.write("not\n"); break;
		}
	}
	
	// Writes a VM label command
	public void writeLabel (String label) throws IOException {
		outStream.write("label " + label + "\n");
	}
	
	// Writes a VM goto command
	public void writeGoto (String label) throws IOException {
		outStream.write("goto " + label + "\n");
	}
	
	// Writes a VM if-goto command
	public void writeIf (String label) throws IOException {
		outStream.write("if-goto " + label + "\n");
	}
	
	// Writes a VM call command
	public void writeCall (String name, int nArgs) throws IOException {
		outStream.write("call " + name + " " + nArgs + "\n");
	}
	
	// Writes a VM function command
	public void writeFunction (String name, int nLocals) throws IOException {
		outStream.write("function " + name + " " + nLocals + "\n");
	}
	
	// Writes a VM return command
	public void writeReturn () throws IOException {
		outStream.write("return\n");
	}
	
	// Closes the output file
	public void close() throws IOException {
	
	}
}