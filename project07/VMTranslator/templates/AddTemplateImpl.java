// Autogenerated Jamon implementation
// C:/cygwin/home/Thea/cs498/src/project07/./VMTranslator/templates/AddTemplate.jamon

package VMTranslator.templates;


public class AddTemplateImpl
  extends org.jamon.AbstractTemplateImpl
  implements VMTranslator.templates.AddTemplate.Intf

{
  protected static VMTranslator.templates.AddTemplate.ImplData __jamon_setOptionalArguments(VMTranslator.templates.AddTemplate.ImplData p_implData)
  {
    return p_implData;
  }
  public AddTemplateImpl(org.jamon.TemplateManager p_templateManager, VMTranslator.templates.AddTemplate.ImplData p_implData)
  {
    super(p_templateManager, __jamon_setOptionalArguments(p_implData));
  }
  
  public void renderNoFlush(@SuppressWarnings({"unused","hiding"}) final java.io.Writer jamonWriter)
    throws java.io.IOException
  {
    // 1, 1
    jamonWriter.write("// add: x + y\r\n@SP\r\nM=M-1\r\nA=M\r\nD=M\r\n@SP\r\nA=M-1\r\nM=M+D");
  }
  
  
}
