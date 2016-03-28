package vendsys;
import javax.swing.*;

public interface IProductInfo
{
	public int getID();
	public String getName();
	public String getDescription();
	public Icon getIcon();
	public double getPrice();
	public int getQuantity();
}
