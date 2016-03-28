package vendsys;

public class VirtualVendingMachine
{
	private IProductDatabase m_pdProducts;
	private ProductSelector m_psProductSelector;
	private OutputDisplay m_odOutputDisplay;
	private PayMachine m_pmPayMachine;
	private ProductDispenser m_pdDispenser;
	
	private boolean m_bOutOfOrder = false;
	
	public VirtualVendingMachine(IProductDatabase pdProducts)
	{
		m_pdProducts = pdProducts;
		m_psProductSelector = new ProductSelector(this);
		m_odOutputDisplay = new OutputDisplay();
		m_pmPayMachine = new PayMachine(this, 3.00);
		m_pdDispenser = new ProductDispenser();
	}

	public IProductInfo[] getProducts()
	{
		return m_pdProducts.getProducts();
	}
	
	public ProductSelector getProductSelector()
	{
		return m_psProductSelector;
	}
	
	public OutputDisplay getOutputDisplay()
	{
		return m_odOutputDisplay;
	}
	
	public PayMachine getPayMachine()
	{
		return m_pmPayMachine;
	}
	
	public ProductDispenser getProductDispenser()
	{
		return m_pdDispenser;
	}
	
			
	public void showProductDesc(IProductInfo piProduct)
	{
		if (m_bOutOfOrder == false)
			m_odOutputDisplay.displayString(piProduct.getDescription());
	}
	
	public void buyItem(IProductInfo piProduct)
	{
		if (m_pmPayMachine.getBalance() >= piProduct.getPrice() &&
				piProduct.getQuantity() > 0)
		{
			m_odOutputDisplay.displayString("Enjoy your " + piProduct.getName());
	
			m_pdProducts.decrementQuantity(piProduct);
			m_pdDispenser.dispenseProduct(piProduct);
			m_pmPayMachine.deductBalance(piProduct.getPrice());
			m_pmPayMachine.returnChange();
			m_psProductSelector.displayProducts();	
		}
	}
	
	public void goOutOfOrder()
	{
		m_odOutputDisplay.displayString("OUT OF ORDER");
		m_bOutOfOrder = true;
	}
}
