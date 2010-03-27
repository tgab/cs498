// Autogenerated Jamon implementation
// C:/cygwin/home/Thea/cs498/src/project07/./VMTranslator/templates/PushTempTemplate.jamon

package VMTranslator.templates;


public class PushTempTemplateImpl
  extends org.jamon.AbstractTemplateImpl
  implements VMTranslator.templates.PushTempTemplate.Intf

{
  private final String segment;
  private final Integer index;
  protected static VMTranslator.templates.PushTempTemplate.ImplData __jamon_setOptionalArguments(VMTranslator.templates.PushTempTemplate.ImplData p_implData)
  {
    return p_implData;
  }
  public PushTempTemplateImpl(org.jamon.TemplateManager p_templateManager, VMTranslator.templates.PushTempTemplate.ImplData p_implData)
  {
    super(p_templateManager, __jamon_setOptionalArguments(p_implData));
    segment = p_implData.getSegment();
    index = p_implData.getIndex();
  }
  
  public void renderNoFlush(@SuppressWarnings({"unused","hiding"}) final java.io.Writer jamonWriter)
    throws java.io.IOException
  {
    // 6, 1
    jamonWriter.write("// push ");
    // 6, 9
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(segment), jamonWriter);
    // 6, 22
    jamonWriter.write(" ");
    // 6, 23
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(index), jamonWriter);
    // 6, 34
    jamonWriter.write("\r\n@");
    // 7, 2
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(segment), jamonWriter);
    // 7, 15
    jamonWriter.write("\r\nD=A\r\n@");
    // 9, 2
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(index), jamonWriter);
    // 9, 13
    jamonWriter.write("\r\nD=D+A\r\nA=D\r\nD=M\r\n@SP\r\nM=M+1\r\nA=M-1\r\nM=D\r\n");
  }
  
  
}
