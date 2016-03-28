package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import app.*;
import vendsys.*;

public class YourMoneyFrame extends JFrame
{
	private PayPanel m_ppPayPanel;
	
	private JButton m_jbQuarter;
	private JButton m_jbNickel;
	private JButton m_jbDime; 
	private JButton m_jbDollar;
	private JButton m_jbSuncard;
	
	private JWindow m_jwQuarter;
	private JWindow m_jwNickel;
	private JWindow m_jwDime;
	private JWindow m_jwDollar;
	private JWindow m_jwSuncard;
	
	private Object m_objAnimLock = new Object();
	
	public YourMoneyFrame(VendingMachineFrame vmfOwner) throws java.io.IOException
	{
		m_ppPayPanel = vmfOwner.getPayPanel();
		
		ImageIcon iiQuarter = Utilities.readIcon("images/QUARTER.JPG");
		ImageIcon iiNickel = Utilities.readIcon("images/NICKEL.JPG");
		ImageIcon iiDime = Utilities.readIcon("images/DIME.JPG");
		ImageIcon iiDollar = Utilities.readIcon("images/DOLLAR.JPG");
		ImageIcon iiSuncard = Utilities.readIcon("images/SUNCARD.JPG");

		m_jbQuarter = new JButton(iiQuarter);
		m_jbNickel = new JButton(iiNickel);
		m_jbDime = new JButton(iiDime);
		m_jbDollar = new JButton(iiDollar);
		m_jbSuncard = new JButton(iiSuncard);
		
		m_jwQuarter = new JWindow();
		m_jwQuarter.add(new JLabel(iiQuarter));
		m_jwQuarter.pack();
		m_jwQuarter.setAlwaysOnTop(true);
		
		m_jwNickel = new JWindow();
		m_jwNickel.add(new JLabel(iiNickel));
		m_jwNickel.pack();
		m_jwNickel.setAlwaysOnTop(true);
		
		m_jwDime = new JWindow();
		m_jwDime.add(new JLabel(iiDime));
		m_jwDime.pack();
		m_jwDime.setAlwaysOnTop(true);
		
		m_jwDollar = new JWindow();
		m_jwDollar.add(new JLabel(iiDollar));
		m_jwDollar.pack();
		m_jwDollar.setAlwaysOnTop(true);
		
		m_jwSuncard = new JWindow();
		m_jwSuncard.add(new JLabel(iiSuncard));
		m_jwSuncard.pack();
		m_jwSuncard.setAlwaysOnTop(true);
		
		JPanel jpCoins = new JPanel();
		jpCoins.setLayout(new FlowLayout());
		jpCoins.add(m_jbQuarter);
		jpCoins.add(m_jbDime);
		jpCoins.add(m_jbNickel);
		
		JPanel jpMoney = new JPanel();
		jpMoney.setLayout(new GridLayout(2,1));
		jpMoney.add(jpCoins);
		jpMoney.add(m_jbDollar);
		
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(jpMoney, BorderLayout.CENTER);
		this.getContentPane().add(m_jbSuncard, BorderLayout.EAST);
		
		pack();
		initEvents();
		
		this.setTitle("Your Money");
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	}
	
	private void initEvents()
	{
		m_jbQuarter.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Point pStart = m_jbQuarter.getLocationOnScreen();
				Point pEnd = m_ppPayPanel.getCoinAcceptorPos();
				drawAnimation(m_jwQuarter, pStart, pEnd);
				m_ppPayPanel.insertQuarter();
			}
		});
		
		m_jbDime.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Point pStart = m_jbDime.getLocationOnScreen();
				Point pEnd = m_ppPayPanel.getCoinAcceptorPos();
				drawAnimation(m_jwDime, pStart, pEnd);
				m_ppPayPanel.insertDime();
			}
		});
		
		m_jbNickel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Point pStart = m_jbNickel.getLocationOnScreen();
				Point pEnd = m_ppPayPanel.getCoinAcceptorPos();
				drawAnimation(m_jwNickel, pStart, pEnd);
				m_ppPayPanel.insertNickel();
			}
		});
		
		m_jbDollar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Point pStart = m_jbDollar.getLocationOnScreen();
				Point pEnd = m_ppPayPanel.getBillAcceptorPos();
				drawAnimation(m_jwDollar, pStart, pEnd);
				
				if (m_ppPayPanel.insertDollar() == false)
				{
					drawAnimation(m_jwDollar, pEnd, pStart);
				}
			}
		});
		
		m_jbSuncard.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Point pStart = m_jbSuncard.getLocationOnScreen();
				Point pEnd = m_ppPayPanel.getCardReaderPos();
				drawAnimation(m_jwSuncard, pStart, pEnd);
				m_ppPayPanel.insertSuncard();
			}
		});	
	}
	
	public void coinReturn(int iQuarters, int iDimes, int iNickels)
	{
		Point pStart = m_ppPayPanel.getCoinReturnPos();
		Point pEnd = m_jbQuarter.getLocationOnScreen();
		
		for (int i=0 ; i<iQuarters ; i++)
		{
			drawAnimation(m_jwQuarter, pStart, pEnd);
		}
		
		pEnd = m_jbDime.getLocationOnScreen();
		
		for (int i=0 ; i<iDimes ; i++)
		{
			drawAnimation(m_jwDime, pStart, pEnd);
		}
		
		pEnd = m_jbNickel.getLocationOnScreen();
		
		for (int i=0 ; i<iNickels ; i++)
		{
			drawAnimation(m_jwNickel, pStart, pEnd);
		}
	}
	
	private void drawAnimation(final JWindow jwIcon, final Point pStart, final Point pEnd)
	{
		Thread tAnimator = new Thread()
		{
			public void run()
			{
				synchronized(m_objAnimLock)
				{
					requestFocus();	
					setEnabled(false);
					
					int iNumPoints = 30;
					
					float fDeltaX = pEnd.x - pStart.x;
					float fDeltaY = pEnd.y - pStart.y;
					float fXIncrement = fDeltaX / iNumPoints;
					float fYIncrement = fDeltaY / iNumPoints;
					float fCurrentX = pStart.x;
					float fCurrentY = pStart.y;
					
					jwIcon.setLocation(pStart);
					jwIcon.setVisible(true);
					
					for (int i=0 ; i<iNumPoints ; i++)
					{
						jwIcon.setLocation((int) fCurrentX, (int) fCurrentY);
						fCurrentX += fXIncrement;
						fCurrentY += fYIncrement;
						
						try
						{
							Thread.sleep(5);
						}
						catch(InterruptedException e)
						{
							System.err.println(e);
						}
					}
					
					jwIcon.setVisible(false);
				
					setEnabled(true);
					requestFocus();	
				}
			}
		};
	
		tAnimator.start();
	}
}
