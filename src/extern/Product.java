package extern;
import javax.swing.*;
import vendsys.IProductInfo;

public class Product implements IProductInfo
{
	private int m_iID;
	private String m_sName;
	private String m_sDescription;
	private Icon m_iIcon;
	private double m_dPrice;
	private int m_iQuantity;
	
	public Product(int iID, String sName, String sDescription, Icon iIcon, double dPrice, int iQuantity)
	{
		m_iID = iID;
		m_sName = sName;
		m_sDescription = sDescription;
		m_iIcon = iIcon;
		m_dPrice = dPrice;
		m_iQuantity = iQuantity;
	}
	
	public int getID()
	{
		return m_iID;
	}
	
	public void setID(int iID)
	{
		m_iID = iID;
	}
	
	public String getName()
	{
		return m_sName;
	}
	
	public void setName(String sName)
	{
		m_sName = sName;
	}
	
	public String getDescription()
	{
		return m_sDescription;
	}
	
	public void setDescription(String sDescription)
	{
		m_sDescription = sDescription;
	}
	
	public Icon getIcon()
	{
		return m_iIcon;
	}
	
	public void setIcon(Icon iIcon)
	{
		m_iIcon = iIcon;
	}
	
	public double getPrice()
	{
		return m_dPrice;
	}
	
	public void setPrice(double dPrice)
	{
		m_dPrice = dPrice;
	}
	
	public int getQuantity()
	{
		return m_iQuantity;
	}

	public void setQuantity(int iQuantity)
	{
		m_iQuantity = iQuantity;
	}
}
