package vendsys;

public class ProductDispenser
{
	private IProductDispenserListener m_pdlListener;
	
	public void dispenseProduct(IProductInfo piProduct)
	{
		if (m_pdlListener != null)
		{
			m_pdlListener.dispenseProduct(piProduct);
		}
	}
	
	public void setProductDispenserListener(IProductDispenserListener pdlListener)
	{
		m_pdlListener = pdlListener;
	}
}
