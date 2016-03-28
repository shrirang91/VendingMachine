package vendsys;
import java.util.ArrayList;

public class CashRegister
{
	private int m_iNumQuarters;
	private int m_iNumDimes;
	private int m_iNumNickels;
	private int m_iNumDollars;
	private ArrayList<SuncardReceipt> m_alReceipts;
	
	public CashRegister()
	{
		m_iNumQuarters = 20;
		m_iNumNickels = 20;
		m_iNumDimes = 20;
		m_alReceipts = new ArrayList<SuncardReceipt>();
	}
	
	public double maximumChange()
	{
		double m_dNickelsAndDimes = 0.10*m_iNumDimes + 0.05*m_iNumNickels;
		
		if (m_iNumNickels == 0)
			return 0;
		else if (m_dNickelsAndDimes < 0.20)
			return m_dNickelsAndDimes;
		else
			return 0.25*m_iNumQuarters + m_dNickelsAndDimes;
	}
	
	public void addDollars(int count)
	{
		m_iNumDollars += count;
	}
	
	public void addQuarters(int count)
	{
		m_iNumQuarters += count;
	}
	
	public void addDimes(int count)
	{
		m_iNumDimes += count;
	}
	
	public void addNickels(int count)
	{
		m_iNumNickels += count;
	}
	
	public void addReceipt(SuncardReceipt receipt)
	{
		m_alReceipts.add(receipt);
	}
	
	public CoinCount takeOutChange(double dAmount)
	{
		if (dAmount > maximumChange())
		{
			throw new IllegalStateException("Attempted to take out more change than is available.");
		}
		else
		{
			double dAmtSoFar = 0.0;
			int iQCnt = 0;
			int iDCnt = 0;
			int iNCnt = 0;
			
			for (int i=0 ; i<m_iNumQuarters ; i++)
			{
				if (dAmtSoFar + 0.25 > dAmount)
					break;
								
				dAmtSoFar += 0.25;
				iQCnt += 1;
			}
			
			for (int i=0 ; i<m_iNumDimes ; i++)
			{
				if (dAmtSoFar + 0.10 > dAmount)
					break;
				
				dAmtSoFar += 0.10;
				iDCnt += 1;
			}
			
			for (int i=0 ; i<m_iNumNickels ; i++)
			{
				if (dAmtSoFar + 0.05 > dAmount)
					break;
				
				dAmtSoFar += 0.05;
				iNCnt += 1;
			}
			
			CoinCount ccToReturn = new CoinCount();
			ccToReturn.NUM_QUARTERS = iQCnt;
			ccToReturn.NUM_DIMES = iDCnt;
			ccToReturn.NUM_NICKELS = iNCnt;
			
			m_iNumQuarters -= iQCnt;
			m_iNumDimes -= iDCnt;
			m_iNumNickels -= iNCnt;
			
			return ccToReturn;
		}
	}
}

class CoinCount
{
	public int NUM_QUARTERS = 0;
	public int NUM_DIMES = 0;
	public int NUM_NICKELS = 0;
}
