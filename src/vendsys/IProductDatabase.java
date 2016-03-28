package vendsys;

public interface IProductDatabase
{
	public IProductInfo[] getProducts();
	public void decrementQuantity(IProductInfo piProduct);
}
