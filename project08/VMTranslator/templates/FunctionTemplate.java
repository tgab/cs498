// Autogenerated Jamon proxy
// C:/cygwin/home/Thea/cs498/src/project08/./VMTranslator/templates/FunctionTemplate.jamon

package VMTranslator.templates;


@org.jamon.annotations.Template(
  signature = "4D6B479FCD1933AAE0F8DE6823FD4E23",
  requiredArguments = {
    @org.jamon.annotations.Argument(name = "f", type = "String"),
    @org.jamon.annotations.Argument(name = "k", type = "int")})
public class FunctionTemplate
  extends org.jamon.AbstractTemplateProxy
{
  
  public FunctionTemplate(org.jamon.TemplateManager p_manager)
  {
     super(p_manager);
  }
  
  public FunctionTemplate()
  {
     super("/VMTranslator/templates/FunctionTemplate");
  }
  
  protected interface Intf
    extends org.jamon.AbstractTemplateProxy.Intf{
    
    void renderNoFlush(final java.io.Writer jamonWriter) throws java.io.IOException;
    
  }
  public static class ImplData
    extends org.jamon.AbstractTemplateProxy.ImplData
  {
    // 2, 3
    public void setF(String f)
    {
      // 2, 3
      m_f = f;
    }
    public String getF()
    {
      return m_f;
    }
    private String m_f;
    // 3, 3
    public void setK(int k)
    {
      // 3, 3
      m_k = k;
    }
    public int getK()
    {
      return m_k;
    }
    private int m_k;
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
    return new FunctionTemplateImpl(getTemplateManager(), getImplData());
  }
  public org.jamon.Renderer makeRenderer(final String f, final int k)
  {
    return new org.jamon.AbstractRenderer() {
      @Override
      public void renderTo(final java.io.Writer jamonWriter)
        throws java.io.IOException
      {
        render(jamonWriter, f, k);
      }
    };
  }
  
  public void render(final java.io.Writer jamonWriter, final String f, final int k)
    throws java.io.IOException
  {
    renderNoFlush(jamonWriter, f, k);
    jamonWriter.flush();
  }
  public void renderNoFlush(final java.io.Writer jamonWriter, final String f, final int k)
    throws java.io.IOException
  {
    ImplData implData = getImplData();
    implData.setF(f);
    implData.setK(k);
    Intf instance = (Intf) getTemplateManager().constructImpl(this);
    instance.renderNoFlush(jamonWriter);
    reset();
  }
  
  
}
