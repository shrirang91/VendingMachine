package vendsys;

public class ProductSelector
{
	VirtualVendingMachine m_vvmMachine = null;
	IProductSelectorListener m_pslListener = null;
	IProductInfo m_piSelectedItem = null;
	
	public ProductSelector(VirtualVendingMachine vvmMachine)
	{
		m_vvmMachine = vvmMachine;
	}
	
	public void displayProducts()
	{
		if (m_pslListener != null)
		{
			m_pslListener.updateDisplay(m_vvmMachine.getProducts());	
		}
	}
	
	public void setProductSelectorListener(IProductSelectorListener pslListener)
	{
		m_pslListener = pslListener;
		displayProducts();
	}
	
	public void showProductDesc(IProductInfo piProduct)
	{
		m_vvmMachine.showProductDesc(piProduct);
	}
	
	public void buyItem(IProductInfo piProduct)
	{
		m_vvmMachine.buyItem(piProduct);
	}
	
	public void setSelectedItem(IProductInfo piProduct)
	{
		m_piSelectedItem = piProduct;
	
		if (m_pslListener != null)
		{
			m_pslListener.itemSelected(piProduct);
		}
		else
		{
			m_pslListener.itemDeselected(piProduct);
		}
	}
}
