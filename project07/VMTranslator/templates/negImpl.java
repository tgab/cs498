// Autogenerated Jamon implementation
// C:/cygwin/home/Thea/cs498/src/project07/./VMTranslator/templates/neg.jamon

package VMTranslator.templates;


public class negImpl
  extends org.jamon.AbstractTemplateImpl
  implements VMTranslator.templates.neg.Intf

{
  protected static VMTranslator.templates.neg.ImplData __jamon_setOptionalArguments(VMTranslator.templates.neg.ImplData p_implData)
  {
    return p_implData;
  }
  public negImpl(org.jamon.TemplateManager p_templateManager, VMTranslator.templates.neg.ImplData p_implData)
  {
    super(p_templateManager, __jamon_setOptionalArguments(p_implData));
  }
  
  public void renderNoFlush(@SuppressWarnings({"unused","hiding"}) final java.io.Writer jamonWriter)
    throws java.io.IOException
  {
    // 1, 1
    jamonWriter.write("// neg -y\r\n@SP\r\nA=M-1\r\nM=-M");
  }
  
  
}