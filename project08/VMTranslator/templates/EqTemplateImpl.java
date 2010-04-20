// Autogenerated Jamon implementation
// C:/cygwin/home/Thea/cs498/src/project08/./VMTranslator/templates/EqTemplate.jamon

package VMTranslator.templates;


public class EqTemplateImpl
  extends org.jamon.AbstractTemplateImpl
  implements VMTranslator.templates.EqTemplate.Intf

{
  private final Integer count;
  protected static VMTranslator.templates.EqTemplate.ImplData __jamon_setOptionalArguments(VMTranslator.templates.EqTemplate.ImplData p_implData)
  {
    return p_implData;
  }
  public EqTemplateImpl(org.jamon.TemplateManager p_templateManager, VMTranslator.templates.EqTemplate.ImplData p_implData)
  {
    super(p_templateManager, __jamon_setOptionalArguments(p_implData));
    count = p_implData.getCount();
  }
  
  public void renderNoFlush(@SuppressWarnings({"unused","hiding"}) final java.io.Writer jamonWriter)
    throws java.io.IOException
  {
    // 5, 1
    jamonWriter.write("// x = y ?\r\n@SP\r\nM=M-1\r\nA=M\r\nD=M\r\nA=A-1\r\nD=M-D\r\n\r\n@TRUE");
    // 13, 6
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(count), jamonWriter);
    // 13, 17
    jamonWriter.write("\r\nD;JEQ\r\nD=0\r\n@END_EQ");
    // 16, 8
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(count), jamonWriter);
    // 16, 19
    jamonWriter.write("\r\n0;JMP\r\n\r\n(TRUE");
    // 19, 6
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(count), jamonWriter);
    // 19, 17
    jamonWriter.write(")\r\nD=-1\r\n\r\n(END_EQ");
    // 22, 8
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(count), jamonWriter);
    // 22, 19
    jamonWriter.write(")\r\n@SP\r\nA=M-1\r\nM=D\r\n");
  }
  
  
}