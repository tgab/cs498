// Autogenerated Jamon implementation
// C:/cygwin/home/Thea/cs498/src/project07/./VMTranslator/templates/not.jamon

package VMTranslator.templates;


public class notImpl
  extends org.jamon.AbstractTemplateImpl
  implements VMTranslator.templates.not.Intf

{
  protected static VMTranslator.templates.not.ImplData __jamon_setOptionalArguments(VMTranslator.templates.not.ImplData p_implData)
  {
    return p_implData;
  }
  public notImpl(org.jamon.TemplateManager p_templateManager, VMTranslator.templates.not.ImplData p_implData)
  {
    super(p_templateManager, __jamon_setOptionalArguments(p_implData));
  }
  
  public void renderNoFlush(@SuppressWarnings({"unused","hiding"}) final java.io.Writer jamonWriter)
    throws java.io.IOException
  {
    // 1, 1
    jamonWriter.write("// not NOT y\r\n@SP\r\nA=M-1\r\nM=!M");
  }
  
  
}
