// Autogenerated Jamon implementation
// C:/cygwin/home/Thea/cs498/src/project08/./VMTranslator/templates/InitTemplate.jamon

package VMTranslator.templates;


public class InitTemplateImpl
  extends org.jamon.AbstractTemplateImpl
  implements VMTranslator.templates.InitTemplate.Intf

{
  protected static VMTranslator.templates.InitTemplate.ImplData __jamon_setOptionalArguments(VMTranslator.templates.InitTemplate.ImplData p_implData)
  {
    return p_implData;
  }
  public InitTemplateImpl(org.jamon.TemplateManager p_templateManager, VMTranslator.templates.InitTemplate.ImplData p_implData)
  {
    super(p_templateManager, __jamon_setOptionalArguments(p_implData));
  }
  
  public void renderNoFlush(@SuppressWarnings({"unused","hiding"}) final java.io.Writer jamonWriter)
    throws java.io.IOException
  {
    // 1, 1
    jamonWriter.write("// init\r\n@256\r\nD=A\r\n@SP\r\nM=D\r\n// call Sys.init\r\n@0\r\nD=A\r\n@SP\r\nM=M+1\r\nA=M-1\r\nM=D\r\n// save LCL\r\n@LCL\r\nD=M\r\n@SP\r\nM=M+1\r\nA=M-1\r\nM=D\r\n// save ARG\r\n@ARG\r\nD=M\r\n@SP\r\nM=M+1\r\nA=M-1\r\nM=D\r\n// save THIS\r\n@THIS\r\nD=M\r\n@SP\r\nM=M+1\r\nA=M-1\r\nM=D\r\n// save THAT\r\n@THAT\r\nD=M\r\n@SP\r\nM=M+1\r\nA=M-1\r\nM=D\r\n// reposition ARG and LCL\r\n@SP\r\nD=M\r\n@LCL\r\nM=D\r\n@0\r\nD=D-A\r\n@5\r\nD=D-A\r\n@ARG\r\nM=D\r\n// transfer control\r\n@Sys.init\r\n0;JMP\r\n\r\n\r\n");
  }
  
  
}
