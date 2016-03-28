package vendsys;

public class OutputDisplay
{
	private String m_sCurrentOutput = "";
	private IOutputDisplayListener m_odlListener;
	
	public void displayString(String sData)
	{
		m_sCurrentOutput = sData;
		
		if (m_odlListener != null)
		{
			m_odlListener.updateDisplay(sData);
		}
	}
	
	public void setOutputDisplayListener(IOutputDisplayListener odlListener)
	{
		m_odlListener = odlListener;
	}
}
