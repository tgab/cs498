// Autogenerated Jamon proxy
// C:/cygwin/home/Thea/cs498/src/project07/./VMTranslator/templates/not.jamon

package VMTranslator.templates;


@org.jamon.annotations.Template(
  signature = "B8C1FD7E374752F9C0E4C4E200B7BDF1")
public class not
  extends org.jamon.AbstractTemplateProxy
{
  
  public not(org.jamon.TemplateManager p_manager)
  {
     super(p_manager);
  }
  
  public not()
  {
     super("/VMTranslator/templates/not");
  }
  
  protected interface Intf
    extends org.jamon.AbstractTemplateProxy.Intf{
    
    void renderNoFlush(final java.io.Writer jamonWriter) throws java.io.IOException;
    
  }
  public static class ImplData
    extends org.jamon.AbstractTemplateProxy.ImplData
  {
  }
  @Override
  protected ImplData makeImplData()
  {
    return new ImplData();
  }
  @Override @SuppressWarnings("unchecked") public ImplData getImplData()
  {
    return (ImplData) super.getImplData();
  }
  
  
  @Override
  public org.jamon.AbstractTemplateImpl constructImpl(Class<? extends org.jamon.AbstractTemplateImpl> p_class){
    try
    {
      return p_class
        .getConstructor(new Class [] { org.jamon.TemplateManager.class, ImplData.class })
        .newInstance(new Object [] { getTemplateManager(), getImplData()});
    }
    catch (RuntimeException e)
    {
      throw e;
    }
    catch (Exception e)
    {
      throw new RuntimeException(e);
    }
  }
  
  @Override
  protected org.jamon.AbstractTemplateImpl constructImpl(){
    return new notImpl(getTemplateManager(), getImplData());
  }
  public org.jamon.Renderer makeRenderer()
  {
    return new org.jamon.AbstractRenderer() {
      @Override
      public void renderTo(final java.io.Writer jamonWriter)
        throws java.io.IOException
      {
        render(jamonWriter);
      }
    };
  }
  
  public void render(final java.io.Writer jamonWriter)
    throws java.io.IOException
  {
    renderNoFlush(jamonWriter);
    jamonWriter.flush();
  }
  public void renderNoFlush(final java.io.Writer jamonWriter)
    throws java.io.IOException
  {
    Intf instance = (Intf) getTemplateManager().constructImpl(this);
    instance.renderNoFlush(jamonWriter);
    reset();
  }
  
  
}