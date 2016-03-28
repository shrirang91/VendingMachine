package gui;

import javax.swing.*;
import java.awt.*;
import vendsys.*;

public class OutputDisplayPanel extends JPanel implements IOutputDisplayListener
{
	private OutputDisplay m_odOutputDisplay;
	private JTextArea jtaOutput;
		
	public OutputDisplayPanel(OutputDisplay odOutputDisplay)
	{
		jtaOutput = new JTextArea();
		jtaOutput.setFont(new Font("Courier New", 0, 12));
		jtaOutput.setBackground(new Color(255, 255, 225));
		jtaOutput.setText("Welcome to Virtual Vending Machine!");
		jtaOutput.setLineWrap(true);
		jtaOutput.setWrapStyleWord(true);
		jtaOutput.setRows(7);
		jtaOutput.setEditable(false);
		JScrollPane jsp = new JScrollPane(jtaOutput, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		this.setLayout(new BorderLayout());
		this.add(jsp, BorderLayout.CENTER);
	
		m_odOutputDisplay = odOutputDisplay;
		m_odOutputDisplay.setOutputDisplayListener(this);
	}
	
	public void updateDisplay(String sData)
	{
		jtaOutput.setText(sData);
		jtaOutput.setCaretPosition(0);
	}
}
