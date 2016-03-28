package vendsys;

public interface IProductSelectorListener
{
	public void updateDisplay(IProductInfo[] piProducts);
	public void itemSelected(IProductInfo piProduct);
	public void itemDeselected(IProductInfo piProduct);
}
