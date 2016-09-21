package innerClass;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.Timer;

public class innerClassTest {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TalkingClock clock=new TalkingClock(1000,true);
		clock.start();
		JOptionPane.showMessageDialog(null, "Quit program?");
		System.exit(0);
	}
}
	class TalkingClock{
		public TalkingClock(int interval, boolean beep) {
			
			this.interval = interval;
			this.beep = beep;
		}
		
		private int interval;
		private boolean beep;
		
	
	
	public void start()
	{
		ActionListener listener =new TimePrinter();
		Timer t=new Timer(interval,listener);
		t.start();
		
	}
	private class TimePrinter implements ActionListener{
		public void actionPerformed(ActionEvent event){
			Date now=new Date();
			System.out.println("At the tone,the time is " + now);
			if(beep) Toolkit.getDefaultToolkit().beep();
		}
	}
	
	}

