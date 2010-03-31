// Autogenerated Jamon proxy
// C:/cygwin/home/Thea/cs498/src/project08/./VMTranslator/templates/LtTemplate.jamon

package VMTranslator.templates;


@org.jamon.annotations.Template(
  signature = "9E37B8A032780204A8D13D4FA095454B",
  requiredArguments = {
    @org.jamon.annotations.Argument(name = "count", type = "Integer")})
public class LtTemplate
  extends org.jamon.AbstractTemplateProxy
{
  
  public LtTemplate(org.jamon.TemplateManager p_manager)
  {
     super(p_manager);
  }
  
  public LtTemplate()
  {
     super("/VMTranslator/templates/LtTemplate");
  }
  
  protected interface Intf
    extends org.jamon.AbstractTemplateProxy.Intf{
    
    void renderNoFlush(final java.io.Writer jamonWriter) throws java.io.IOException;
    
  }
  public static class ImplData
    extends org.jamon.AbstractTemplateProxy.ImplData
  {
    // 2, 3
    public void setCount(Integer count)
    {
      // 2, 3
      m_count = count;
    }
    public Integer getCount()
    {
      return m_count;
    }
    private Integer m_count;
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
    return new LtTemplateImpl(getTemplateManager(), getImplData());
  }
  public org.jamon.Renderer makeRenderer(final Integer count)
  {
    return new org.jamon.AbstractRenderer() {
      @Override
      public void renderTo(final java.io.Writer jamonWriter)
        throws java.io.IOException
      {
        render(jamonWriter, count);
      }
    };
  }
  
  public void render(final java.io.Writer jamonWriter, final Integer count)
    throws java.io.IOException
  {
    renderNoFlush(jamonWriter, count);
    jamonWriter.flush();
  }
  public void renderNoFlush(final java.io.Writer jamonWriter, final Integer count)
    throws java.io.IOException
  {
    ImplData implData = getImplData();
    implData.setCount(count);
    Intf instance = (Intf) getTemplateManager().constructImpl(this);
    instance.renderNoFlush(jamonWriter);
    reset();
  }
  
  
}
