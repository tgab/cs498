// Autogenerated Jamon proxy
// C:/cygwin/home/Thea/cs498/src/project07/./VMTranslator/templates/PushTempTemplate.jamon

package VMTranslator.templates;


@org.jamon.annotations.Template(
  signature = "214436044E8ABCFC2A1BA23CD1AF873B",
  requiredArguments = {
    @org.jamon.annotations.Argument(name = "segment", type = "String"),
    @org.jamon.annotations.Argument(name = "index", type = "Integer")})
public class PushTempTemplate
  extends org.jamon.AbstractTemplateProxy
{
  
  public PushTempTemplate(org.jamon.TemplateManager p_manager)
  {
     super(p_manager);
  }
  
  public PushTempTemplate()
  {
     super("/VMTranslator/templates/PushTempTemplate");
  }
  
  protected interface Intf
    extends org.jamon.AbstractTemplateProxy.Intf{
    
    void renderNoFlush(final java.io.Writer jamonWriter) throws java.io.IOException;
    
  }
  public static class ImplData
    extends org.jamon.AbstractTemplateProxy.ImplData
  {
    // 2, 3
    public void setSegment(String segment)
    {
      // 2, 3
      m_segment = segment;
    }
    public String getSegment()
    {
      return m_segment;
    }
    private String m_segment;
    // 3, 3
    public void setIndex(Integer index)
    {
      // 3, 3
      m_index = index;
    }
    public Integer getIndex()
    {
      return m_index;
    }
    private Integer m_index;
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
    return new PushTempTemplateImpl(getTemplateManager(), getImplData());
  }
  public org.jamon.Renderer makeRenderer(final String segment, final Integer index)
  {
    return new org.jamon.AbstractRenderer() {
      @Override
      public void renderTo(final java.io.Writer jamonWriter)
        throws java.io.IOException
      {
        render(jamonWriter, segment, index);
      }
    };
  }
  
  public void render(final java.io.Writer jamonWriter, final String segment, final Integer index)
    throws java.io.IOException
  {
    renderNoFlush(jamonWriter, segment, index);
    jamonWriter.flush();
  }
  public void renderNoFlush(final java.io.Writer jamonWriter, final String segment, final Integer index)
    throws java.io.IOException
  {
    ImplData implData = getImplData();
    implData.setSegment(segment);
    implData.setIndex(index);
    Intf instance = (Intf) getTemplateManager().constructImpl(this);
    instance.renderNoFlush(jamonWriter);
    reset();
  }
  
  
}
