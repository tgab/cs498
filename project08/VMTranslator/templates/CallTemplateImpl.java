// Autogenerated Jamon implementation
// C:/cygwin/home/Thea/cs498/src/project08/./VMTranslator/templates/CallTemplate.jamon

package VMTranslator.templates;


public class CallTemplateImpl
  extends org.jamon.AbstractTemplateImpl
  implements VMTranslator.templates.CallTemplate.Intf

{
  private final String f;
  private final int n;
  private final int i;
  protected static VMTranslator.templates.CallTemplate.ImplData __jamon_setOptionalArguments(VMTranslator.templates.CallTemplate.ImplData p_implData)
  {
    return p_implData;
  }
  public CallTemplateImpl(org.jamon.TemplateManager p_templateManager, VMTranslator.templates.CallTemplate.ImplData p_implData)
  {
    super(p_templateManager, __jamon_setOptionalArguments(p_implData));
    f = p_implData.getF();
    n = p_implData.getN();
    i = p_implData.getI();
  }
  
  public void renderNoFlush(@SuppressWarnings({"unused","hiding"}) final java.io.Writer jamonWriter)
    throws java.io.IOException
  {
    // 7, 1
    jamonWriter.write("// call ");
    // 7, 9
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(f), jamonWriter);
    // 7, 16
    jamonWriter.write(" ");
    // 7, 17
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(n), jamonWriter);
    // 7, 24
    jamonWriter.write("\r\n@");
    // 8, 2
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(f), jamonWriter);
    // 8, 9
    jamonWriter.write(".return");
    // 8, 16
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(i), jamonWriter);
    // 8, 23
    jamonWriter.write("\r\nD=A\r\n@SP\r\nM=M+1\r\nA=M-1\r\nM=D\r\n// save LCL\r\n@LCL\r\nD=M\r\n@SP\r\nM=M+1\r\nA=M-1\r\nM=D\r\n// save ARG\r\n@ARG\r\nD=M\r\n@SP\r\nM=M+1\r\nA=M-1\r\nM=D\r\n// save THIS\r\n@THIS\r\nD=M\r\n@SP\r\nM=M+1\r\nA=M-1\r\nM=D\r\n// save THAT\r\n@THAT\r\nD=M\r\n@SP\r\nM=M+1\r\nA=M-1\r\nM=D\r\n// reposition ARG and LCL\r\n@SP\r\nD=M\r\n@LCL\r\nM=D\r\n@");
    // 47, 2
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(n), jamonWriter);
    // 47, 9
    jamonWriter.write("\r\nD=D-A\r\n@5\r\nD=D-A\r\n@ARG\r\nM=D\r\n// transfer control\r\n@");
    // 54, 2
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(f), jamonWriter);
    // 54, 9
    jamonWriter.write("\r\n0;JMP\r\n(");
    // 56, 2
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(f), jamonWriter);
    // 56, 9
    jamonWriter.write(".return");
    // 56, 16
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(i), jamonWriter);
    // 56, 23
    jamonWriter.write(")\r\n");
  }
  
  
}