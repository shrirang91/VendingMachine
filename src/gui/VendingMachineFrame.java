package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import vendsys.*;
import app.*;

public class VendingMachineFrame extends JFrame
{
	private VirtualVendingMachine m_vvmMachine;
	private PayPanel m_ppPayPanel;
	private SelectorPanel m_spSelectorPanel;
	private OutputDisplayPanel m_odpOutputDisplayPanel;
	private DispenserPanel m_dpDispenserPanel;
	private YourMoneyFrame m_ymfYourMoney;
	
	public VendingMachineFrame(VirtualVendingMachine vvmMachine) throws java.io.IOException
	{
		m_vvmMachine = vvmMachine;
		m_ppPayPanel = new PayPanel(m_vvmMachine.getPayMachine());
		m_spSelectorPanel = new SelectorPanel(m_vvmMachine.getProductSelector());
		m_odpOutputDisplayPanel = new OutputDisplayPanel(m_vvmMachine.getOutputDisplay());
		m_dpDispenserPanel = new DispenserPanel(m_vvmMachine.getProductDispenser());
		
		JPanel jpEast = new JPanel();
		JPanel jpEastCenter = new JPanel();
		JPanel jpCenter = new JPanel();
		Container cpPane = this.getContentPane();
		
		cpPane.setLayout(new BorderLayout());
		jpEast.setLayout(new BorderLayout());
		jpEastCenter.setLayout(new BorderLayout());
		
		jpEastCenter.add(m_spSelectorPanel, BorderLayout.WEST);
		jpEastCenter.add(m_ppPayPanel, BorderLayout.CENTER);
		jpEast.add(jpEastCenter, BorderLayout.CENTER);
		jpEast.add(m_odpOutputDisplayPanel, BorderLayout.NORTH);
		cpPane.add(jpEast, BorderLayout.EAST);
	
		ImageIcon iiVM = Utilities.readIcon("images/VENDING_MACHINE.JPG");
		JLabel jlVM = new JLabel(iiVM, JLabel.CENTER);
		jlVM.setAlignmentX(JComponent.CENTER_ALIGNMENT);
				
		jpCenter.setLayout(new BoxLayout(jpCenter, BoxLayout.Y_AXIS));
		jpCenter.add(Box.createVerticalStrut(10));
		jpCenter.add(jlVM);
		jpCenter.add(m_dpDispenserPanel);
		
		cpPane.add(jpCenter, BorderLayout.CENTER);
		
		setSize(650, 512);
		setTitle("Virtual Vending Machine");
		
		m_ymfYourMoney = new YourMoneyFrame(this);
		m_ymfYourMoney.setLocationRelativeTo(this);
		m_ymfYourMoney.setDefaultCloseOperation(EXIT_ON_CLOSE);
		m_ppPayPanel.setYourMoneyFrame(m_ymfYourMoney);
	}
	
	public void setVisible(boolean b)
	{
		super.setVisible(b);
		m_ymfYourMoney.setVisible(b);
	}
	
	public PayPanel getPayPanel()
	{
		return m_ppPayPanel;
	}
	
	public SelectorPanel getSelectorPanel()
	{
		return m_spSelectorPanel;
	}
	
	public OutputDisplayPanel getOutputDisplayPanel()
	{
		return m_odpOutputDisplayPanel;
	}
	
	public DispenserPanel getDispenserPanel()
	{
		return m_dpDispenserPanel;
	}
}
