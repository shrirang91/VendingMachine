package extern;
import vendsys.*;
import app.*;
import javax.swing.*;
import java.io.*;

public class MyProductDatabase implements IProductDatabase
{
	private Product[] pProducts;

	public MyProductDatabase() throws IOException
	{
		ImageIcon iiSpriteIcon = Utilities.readIcon("images/SPRITE_ICON.JPG");
		ImageIcon iiPepsiIcon = Utilities.readIcon("images/PEPSI_ICON.JPG");
		ImageIcon iiAppleJuiceIcon = Utilities.readIcon("images/APPLE_JUICE_ICON.jpg");
		ImageIcon iiDoritosChipsIcon = Utilities.readIcon("images/DORITOS_CHIPS_ICON.jpg");
		ImageIcon iiGranolaIcon = Utilities.readIcon("images/GRANOLA_ICON.jpg");

		String sSpriteDesc = Utilities.readFile("text/SPRITE_DESC.TXT");
		String sPepsiDesc = Utilities.readFile("text/PEPSI_DESC.TXT");
		String sAppleJuiceDesc = Utilities.readFile("text/APPLE_JUICE_DESC.TXT");
		String sDoritosChipsDesc = Utilities.readFile("text/DORITOS_CHIPS_DESC.TXT");
		String sGranolaDesc = Utilities.readFile("text/GRANOLA_DESC.TXT");
		
		Product pSprite = new Product(0, "Sprite", sSpriteDesc, iiSpriteIcon, 0.95, 3);
		Product pPepsi = new Product(1, "Pepsi", sPepsiDesc, iiPepsiIcon, 0.95, 3);
		Product pAppleJuice = new Product(2, "Motts Apple Juice", sAppleJuiceDesc, iiAppleJuiceIcon, 1.95, 3);
		Product pDoritosChips = new Product(3, "Doritos Chips", sDoritosChipsDesc, iiDoritosChipsIcon, 1.15, 0);
		Product pGranola = new Product(4, "Nature Valley Granola", sGranolaDesc, iiGranolaIcon, 0.60, 3);

		pProducts = new Product[] { pSprite, pPepsi, pAppleJuice, pDoritosChips, pGranola };
	}

	public IProductInfo[] getProducts()
	{
		return pProducts;
	}

	public void decrementQuantity(IProductInfo piProduct)
	{
		int iProdID = piProduct.getID();
		
		if (iProdID < 0 || iProdID >= pProducts.length)
		{
			throw new IllegalArgumentException("Product ID: " + iProdID + " is not valid");
		}
		
		pProducts[iProdID].setQuantity(pProducts[iProdID].getQuantity() - 1);
	}

	public IProductInfo getProduct(int iProductID)
	{
		return pProducts[iProductID];
	}

	public int getNumProducts()
	{
		return pProducts.length;
	}
}
