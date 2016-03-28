package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

import javax.swing.border.*;
import vendsys.*;

public class SelectorPanel extends JPanel implements IProductSelectorListener
{
	private ProductSelector m_psSelector;
	private SelectorButton[] m_sbSelButtons;
	
	private static final int NUM_BUTTONS = 5;
	
	public SelectorPanel(ProductSelector spSelector)
	{
		m_sbSelButtons = new SelectorButton[5];
		this.setLayout(new GridLayout(NUM_BUTTONS, 1));
		
		for (int i=0 ; i<5 ; i++)
		{
			m_sbSelButtons[i] = new SelectorButton(this);
			this.add(m_sbSelButtons[i]);
		}

		m_psSelector = spSelector;
		m_psSelector.setProductSelectorListener(this);
	}
	
	public void updateDisplay(IProductInfo[] piProducts)
	{
		for (int i=0 ; i<5 ; i++)
		{
			if (i < piProducts.length)
			{
				m_sbSelButtons[i].setProduct(piProducts[i]);
			}
			else
			{
				m_sbSelButtons[i].setProduct(null);
			}
		}
	}

	public void itemSelected(IProductInfo piProduct)
	{
		for (SelectorButton b : m_sbSelButtons)
		{
			if (b.getProduct().equals(piProduct))
			{
				b.setSelected(true);
				break;
			}
		}
	}
	
	public void itemDeselected(IProductInfo piProduct)
	{	
		for (SelectorButton b : m_sbSelButtons)
		{
			if (b.getProduct().equals(piProduct))
			{
				b.setSelected(false);
				break;
			}
		}
	}
	
	public void buyItem(IProductInfo piProduct)
	{
		m_psSelector.buyItem(piProduct);
	}
	
	public void showProductInfo(IProductInfo piProduct)
	{
		m_psSelector.showProductDesc(piProduct);
	}
}

class SelectorButton extends JPanel
{
	private SelectorPanel m_spParent;
	
	private JPanel m_jpBuyInfo;
	private JButton m_jbBuy;
	private JButton m_jbInfo;
	
	private JLabel m_jlIcon;

	private static final Color AVAILABLE_COLOR = Color.DARK_GRAY;
	private static final Color OUT_OF_STOCK_COLOR = Color.RED;
	private static final Color UNINITIALIZED_COLOR = Color.GRAY;
	private static final Color SELECTED_COLOR = new Color(0, 200, 0);

	private IProductInfo m_piProduct;
	private boolean m_bIsSelected;
	
	public SelectorButton(SelectorPanel spParent)
	{
		m_spParent = spParent;
				
		m_jpBuyInfo = new JPanel();
		m_jbBuy = new JButton("Buy");
		m_jbInfo = new JButton("Info");
		m_jpBuyInfo.setLayout(new FlowLayout());
		m_jpBuyInfo.add(m_jbBuy);
		m_jpBuyInfo.add(m_jbInfo);
			
		m_jlIcon = new JLabel("", JLabel.LEFT);
		m_jlIcon.setForeground(Color.WHITE);
		
		m_bIsSelected= false;
		
		this.setLayout(new BorderLayout());
		this.add(m_jlIcon, BorderLayout.CENTER);
		this.add(m_jpBuyInfo, BorderLayout.SOUTH);
		this.setBorder(new EtchedBorder(Color.WHITE, Color.BLACK));
		
		m_jbBuy.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				m_spParent.buyItem(m_piProduct);
			}
		});
		
		m_jbInfo.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				m_spParent.showProductInfo(m_piProduct);
			}
		});
		
		this.setProduct(null);
	}

	public void setProduct(IProductInfo piProduct)
	{
		m_piProduct = piProduct;
		
		if (piProduct == null)
		{
			m_jlIcon.setIcon(null);
			m_jlIcon.setText("");
			m_jbBuy.setEnabled(false);
			m_jbInfo.setEnabled(false);
			m_jpBuyInfo.setBackground(UNINITIALIZED_COLOR);
			this.setBackground(UNINITIALIZED_COLOR);
		}
		else
		{
			String strPrice = DecimalFormat.getCurrencyInstance().format(piProduct.getPrice());
			
			m_jlIcon.setIcon(piProduct.getIcon());
			m_jlIcon.setText(strPrice);
			
			if (piProduct.getQuantity() > 0)
			{
				m_jbBuy.setEnabled(true);
				m_jbInfo.setEnabled(true);
				m_jpBuyInfo.setBackground(AVAILABLE_COLOR);
				this.setBackground(AVAILABLE_COLOR);
			}
			else
			{
				m_jbBuy.setEnabled(false);
				m_jbInfo.setEnabled(true);
				m_jpBuyInfo.setBackground(OUT_OF_STOCK_COLOR);
				this.setBackground(OUT_OF_STOCK_COLOR);
			}
		}
	}
	
	public IProductInfo getProduct()
	{
		return m_piProduct;
	}
	
	public void setSelected(boolean bSelected)
	{
		if (bSelected == true)
		{
			m_jpBuyInfo.setBackground(SELECTED_COLOR);
			this.setBackground(SELECTED_COLOR);
		}
		else
		{
			setProduct(m_piProduct);
		}
	}
}
