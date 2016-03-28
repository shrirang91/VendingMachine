package vendsys;

public interface IPayMachineListener
{
	public void returnChange(int iNumQuarters, int iNumDimes, int iNumNickels);
	public void balanceUpdated(double dBalance);
}
