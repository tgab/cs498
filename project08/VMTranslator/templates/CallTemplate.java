// Autogenerated Jamon proxy
// C:/cygwin/home/Thea/cs498/src/project08/./VMTranslator/templates/CallTemplate.jamon

package VMTranslator.templates;


@org.jamon.annotations.Template(
  signature = "47361B57532A4E25F86AD6C4A4200689",
  requiredArguments = {
    @org.jamon.annotations.Argument(name = "f", type = "String"),
    @org.jamon.annotations.Argument(name = "n", type = "int")})
public class CallTemplate
  extends org.jamon.AbstractTemplateProxy
{
  
  public CallTemplate(org.jamon.TemplateManager p_manager)
  {
     super(p_manager);
  }
  
  public CallTemplate()
  {
     super("/VMTranslator/templates/CallTemplate");
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
    public void setN(int n)
    {
      // 3, 3
      m_n = n;
    }
    public int getN()
    {
      return m_n;
    }
    private int m_n;
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
    return new CallTemplateImpl(getTemplateManager(), getImplData());
  }
  public org.jamon.Renderer makeRenderer(final String f, final int n)
  {
    return new org.jamon.AbstractRenderer() {
      @Override
      public void renderTo(final java.io.Writer jamonWriter)
        throws java.io.IOException
      {
        render(jamonWriter, f, n);
      }
    };
  }
  
  public void render(final java.io.Writer jamonWriter, final String f, final int n)
    throws java.io.IOException
  {
    renderNoFlush(jamonWriter, f, n);
    jamonWriter.flush();
  }
  public void renderNoFlush(final java.io.Writer jamonWriter, final String f, final int n)
    throws java.io.IOException
  {
    ImplData implData = getImplData();
    implData.setF(f);
    implData.setN(n);
    Intf instance = (Intf) getTemplateManager().constructImpl(this);
    instance.renderNoFlush(jamonWriter);
    reset();
  }
  
  
}
