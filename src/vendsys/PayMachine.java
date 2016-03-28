package vendsys;

public class PayMachine
{
	private VirtualVendingMachine m_vvmOwner;
	private IPayMachineListener m_pmlListener;	
	private final double m_dMaximumBalance;
	private CashRegister m_crRegister;
	private double m_dBalance = 0.00;
	
	private boolean m_bOutOfOrder = false;
	
	public PayMachine(VirtualVendingMachine vvmOwner, double dMaximumBalance)
	{
		if (dMaximumBalance <= 0)
		{
			throw new IllegalArgumentException("Invalid maximum balance for PayMachine.");
		}
		
		m_vvmOwner = vvmOwner;
		m_dMaximumBalance = dMaximumBalance;
		m_crRegister = new CashRegister();
		
		if (m_dMaximumBalance > m_crRegister.maximumChange())
		{
			throw new IllegalStateException("Specified maximum balance greater than cash register's maximum change.");
		}
	}

	public void setPayMachineListener(IPayMachineListener pmlListener)
	{
		m_pmlListener = pmlListener;
		
		if (pmlListener != null)
		{
			pmlListener.balanceUpdated(m_dBalance);
		}
	}

	public double getBalance()
	{
		return m_dBalance;
	}
	
	public void insertQuarter()
	{
		if (m_dBalance <= m_dMaximumBalance - 0.25 && m_bOutOfOrder == false)
		{
			m_dBalance += 0.25;
			m_crRegister.addQuarters(1);
		}
		else if (m_pmlListener != null)
		{
			m_pmlListener.returnChange(1, 0, 0);
			m_pmlListener.balanceUpdated(m_dBalance);
			return;
		}
		
		if (m_pmlListener != null)
		{
			m_pmlListener.balanceUpdated(m_dBalance);
		}
	}
	
	public void insertDime()
	{
		if (m_dBalance <= m_dMaximumBalance - 0.10 && m_bOutOfOrder == false)
		{
			m_dBalance += 0.10;
			m_crRegister.addDimes(1);
		}
		else if (m_pmlListener != null)
		{
			m_pmlListener.returnChange(0, 1, 0);
			m_pmlListener.balanceUpdated(m_dBalance);
			return;
		}
		
		if (m_pmlListener != null)
		{
			m_pmlListener.balanceUpdated(m_dBalance);
		}
	}
	
	public void insertNickel()
	{
		if (m_dBalance <= m_dMaximumBalance - 0.05 && m_bOutOfOrder == false)
		{
			m_dBalance += 0.05;
			m_crRegister.addNickels(1);
		}
		else if (m_pmlListener != null)
		{
			m_pmlListener.returnChange(0, 0, 1);
			m_pmlListener.balanceUpdated(m_dBalance);
			return;
		}
		
		if (m_pmlListener != null)
		{
			m_pmlListener.balanceUpdated(m_dBalance);
		}
	}
	
	public boolean insertDollar()
	{
		double dNewBalance = m_dBalance + 1.00;
		
		if (dNewBalance <= m_dMaximumBalance && m_bOutOfOrder == false)
		{
			if (m_crRegister.maximumChange() >= dNewBalance)
			{
				m_dBalance = dNewBalance;
				m_crRegister.addDollars(1);
				m_pmlListener.balanceUpdated(m_dBalance);
				return true;
			}
		}
		
		return false;
	}
	
	public void insertSuncard(int iAccount)
	{
		
	}
	
	public void returnChange()
	{
		if (m_dBalance < 0)
		{
			throw new IllegalStateException("Balance is negative, cannot return change.");
		}
		
		CoinCount ccChange = m_crRegister.takeOutChange(m_dBalance);
		m_dBalance = 0.0;
		
		if (m_pmlListener != null)
		{
			m_pmlListener.returnChange(ccChange.NUM_QUARTERS,
										ccChange.NUM_DIMES,
										ccChange.NUM_NICKELS);
			
			m_pmlListener.balanceUpdated(m_dBalance);
		}
		
		if (m_dMaximumBalance > m_crRegister.maximumChange())
		{
			m_bOutOfOrder = true;
			m_vvmOwner.goOutOfOrder();
		}
	}
	
	public void deductBalance(double dAmount)
	{
		m_dBalance -= dAmount;
		
		if (m_pmlListener != null)
		{
			m_pmlListener.balanceUpdated(m_dBalance);
		}
	}
	
	public void clearBalance()
	{
		m_dBalance = 0.00;
		
		if (m_pmlListener != null)
		{
			m_pmlListener.balanceUpdated(0.00);
		}
	}
}
