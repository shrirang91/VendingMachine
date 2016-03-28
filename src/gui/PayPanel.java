package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;

import app.*;
import vendsys.*;

public class PayPanel extends JPanel implements IPayMachineListener
{
	private PayMachine m_pmPayMachine;
	private YourMoneyFrame m_ymfYourMoney;
	
	private JLabel jlCoinAcceptor;
	private JLabel jlBillAcceptor;
	private JLabel jlCardReader;
	private JLabel jlCoinReturn;
	private JButton jbCancel;
	private JTextField jtfBalance;
		
	public PayPanel(PayMachine ppPayMachine) throws java.io.IOException
	{
		m_pmPayMachine = ppPayMachine;
				
		ImageIcon iiCoinAcceptor = Utilities.readIcon("images/COIN_ACCEPTOR.JPG");
		ImageIcon iiBillAcceptor = Utilities.readIcon("images/BILL_ACCEPTOR.JPG");
		ImageIcon iiCardReader = Utilities.readIcon("images/CARD_READER.JPG");
		ImageIcon iiCoinReturn = Utilities.readIcon("images/COIN_RETURN.JPG");
			
		jlCoinAcceptor = new JLabel(iiCoinAcceptor, JLabel.CENTER);
		jlBillAcceptor = new JLabel(iiBillAcceptor, JLabel.CENTER);
		jlCardReader = new JLabel(iiCardReader, JLabel.CENTER);
		jlCoinReturn = new JLabel(iiCoinReturn, JLabel.CENTER);
		
		jbCancel = new JButton("Cancel / Coin Return");
		jbCancel.setAlignmentX(JComponent.RIGHT_ALIGNMENT);
		jbCancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				cancelPurchase();
			}
		});
		
		jtfBalance = new JTextField();
		jtfBalance.setFont(new Font("sans serif", Font.BOLD, 14));
		jtfBalance.setBackground(Color.BLACK);
		jtfBalance.setForeground(Color.GREEN);
		jtfBalance.setHorizontalAlignment(JTextField.RIGHT);
		jtfBalance.setEditable(false);
				
		JPanel jpBalanceAndReturn = new JPanel();
		JPanel jpAcceptors = new JPanel();
		
		jpBalanceAndReturn.setLayout(new BoxLayout(jpBalanceAndReturn, BoxLayout.Y_AXIS));
		jpAcceptors.setLayout(new GridLayout(2,2));
		this.setLayout(new BorderLayout());
		
		jpBalanceAndReturn.add(jtfBalance);
		jpBalanceAndReturn.add(jbCancel);
		jpAcceptors.add(jlCoinAcceptor);
		jpAcceptors.add(jlBillAcceptor);
		jpAcceptors.add(jlCardReader);
		jpAcceptors.add(jlCoinReturn);
				
		this.add(jpBalanceAndReturn, BorderLayout.NORTH);
		this.add(jpAcceptors, BorderLayout.CENTER);
		
		this.setBackground(Color.DARK_GRAY);
		jpBalanceAndReturn.setBackground(Color.DARK_GRAY);
		jpAcceptors.setBackground(Color.DARK_GRAY);
	
		m_pmPayMachine.setPayMachineListener(this);
	}
	
	public Point getCoinAcceptorPos()
	{
		return jlCoinAcceptor.getLocationOnScreen();
	}
	
	public Point getBillAcceptorPos()
	{
		return jlBillAcceptor.getLocationOnScreen();
	}
	
	public Point getCardReaderPos()
	{
		return jlCardReader.getLocationOnScreen();
	}
	
	public Point getCoinReturnPos()
	{
		return jlCoinReturn.getLocationOnScreen();
	}
	
	public void setYourMoneyFrame(YourMoneyFrame ymf)
	{
		m_ymfYourMoney = ymf;
	}
	
	public void cancelPurchase()
	{
		m_pmPayMachine.returnChange();
	}
	
	public void insertQuarter()
	{
		m_pmPayMachine.insertQuarter();
	}
	
	public void insertNickel()
	{
		m_pmPayMachine.insertNickel();
	}
	
	public void insertDime()
	{
		m_pmPayMachine.insertDime();
	}
	
	public boolean insertDollar()
	{
		return m_pmPayMachine.insertDollar();
	}
	
	public void insertSuncard()
	{
	}
	
	public void balanceUpdated(double dBalance)
	{
		jtfBalance.setText(NumberFormat.getCurrencyInstance().format(dBalance));
	}
	
	public void returnChange(int iQuarters, int iDimes, int iNickels)
	{
		if (m_ymfYourMoney != null)
		{
			m_ymfYourMoney.coinReturn(iQuarters, iDimes, iNickels);
		}
	}
}
