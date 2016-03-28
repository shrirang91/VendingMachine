package vendsys;

public class SuncardReceipt
{
	private int m_iAccount;
	private double m_dAmountDeducted;
	
	public SuncardReceipt(int iAccount, double dAmountDeducted)
	{
		m_iAccount = iAccount;
		m_dAmountDeducted = dAmountDeducted;
	}
	
	public int getAccount()
	{
		return m_iAccount;
	}
	
	public double getAmountDeducted()
	{
		return m_dAmountDeducted;
	}
}
