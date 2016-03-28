package gui;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.io.*;
import app.*;
import vendsys.*;

public class DispenserPanel extends JPanel implements IProductDispenserListener
{
	private ProductDispenser m_pdDispenser;
	private JLabel m_jlEnjoyProduct;
	private HashMap<Integer, ImageIcon> m_hmProducts;
	
	public DispenserPanel(ProductDispenser pdDispenser) throws IOException
	{
		m_pdDispenser = pdDispenser;
		pdDispenser.setProductDispenserListener(this);
		
		ImageIcon iiSpriteProduct = Utilities.readIcon("images/SPRITE_PRODUCT.JPG");
		ImageIcon iiPepsiProduct = Utilities.readIcon("images/PEPSI_PRODUCT.JPG");
		ImageIcon iiAppleJuiceProduct = Utilities.readIcon("images/APPLE_JUICE_PRODUCT.JPG");
		ImageIcon iiDoritosProduct = Utilities.readIcon("images/DORITOS_PRODUCT.JPG");
		ImageIcon iiGranolaProduct = Utilities.readIcon("images/GRANOLA_PRODUCT.JPG");
				
		m_hmProducts = new HashMap<Integer, ImageIcon>(7);
		m_hmProducts.put(0, iiSpriteProduct);
		m_hmProducts.put(1, iiPepsiProduct);
		m_hmProducts.put(2, iiAppleJuiceProduct);
		m_hmProducts.put(3, iiDoritosProduct);
		m_hmProducts.put(4, iiGranolaProduct);
		
		m_jlEnjoyProduct = new JLabel("Enjoy!");
		m_jlEnjoyProduct.setVisible(false);
		this.add(m_jlEnjoyProduct);
	}
	
	public void dispenseProduct(IProductInfo piProduct)
	{
		ImageIcon iiProduct = m_hmProducts.get((Integer) piProduct.getID());
		m_jlEnjoyProduct.setIcon(iiProduct);
		
		Thread tFlasher = new Thread()
		{
			public void run()
			{
				m_jlEnjoyProduct.setVisible(true);

				try
				{
					Thread.sleep(2000);
				}
				catch(InterruptedException e)
				{
					System.err.println(e);
				}
			
				m_jlEnjoyProduct.setVisible(false);
			}
		};
		
		tFlasher.start();
	}
}
