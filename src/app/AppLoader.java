package app;
import gui.*;
import vendsys.*;
import extern.*;
import javax.swing.JFrame;

public class AppLoader
{
	public static void main(String args[])
	{
		MyProductDatabase mpdProducts = null;
		VirtualVendingMachine vvmMachine = null;
		VendingMachineFrame vmfMachineFrame = null;
			
		try
		{
			mpdProducts = new MyProductDatabase();
			vvmMachine = new VirtualVendingMachine(mpdProducts);
			vmfMachineFrame = new VendingMachineFrame(vvmMachine);
		}
		catch (Exception ex)
		{
			System.out.println(ex);
			System.exit(0);
		}

		vmfMachineFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		vmfMachineFrame.setLocationRelativeTo(null);
		vmfMachineFrame.setVisible(true);
	}	
}
