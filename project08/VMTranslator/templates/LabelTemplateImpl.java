// Autogenerated Jamon implementation
// C:/cygwin/home/Thea/cs498/src/project08/./VMTranslator/templates/LabelTemplate.jamon

package VMTranslator.templates;


public class LabelTemplateImpl
  extends org.jamon.AbstractTemplateImpl
  implements VMTranslator.templates.LabelTemplate.Intf

{
  private final String b;
  protected static VMTranslator.templates.LabelTemplate.ImplData __jamon_setOptionalArguments(VMTranslator.templates.LabelTemplate.ImplData p_implData)
  {
    return p_implData;
  }
  public LabelTemplateImpl(org.jamon.TemplateManager p_templateManager, VMTranslator.templates.LabelTemplate.ImplData p_implData)
  {
    super(p_templateManager, __jamon_setOptionalArguments(p_implData));
    b = p_implData.getB();
  }
  
  public void renderNoFlush(@SuppressWarnings({"unused","hiding"}) final java.io.Writer jamonWriter)
    throws java.io.IOException
  {
    // 5, 1
    jamonWriter.write("// label ");
    // 5, 10
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(b), jamonWriter);
    // 5, 17
    jamonWriter.write("\r\n(");
    // 6, 2
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(b), jamonWriter);
    // 6, 9
    jamonWriter.write(")\r\n");
  }
  
  
}
