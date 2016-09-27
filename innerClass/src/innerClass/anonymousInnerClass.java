package innerClass;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;


import javax.swing.JOptionPane;
import javax.swing.Timer;

public class anonymousInnerClass {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		TalkingClock clock=new TalkingClock(1000, true);
		clock.start();
		JOptionPane.showMessageDialog(null, "Quit program?");
		System.exit(0);
		
	}
}
	class TalkingClocks{
		public void start(int interval, final boolean beep) {
			
		ActionListener listener =new ActionListener()
		{
			public void actionPerformed(ActionEvent event){
			Date now=new Date();
			System.out.println("At the tone,the time is " + now);
			if(beep) Toolkit.getDefaultToolkit().beep();
		}
	};
	Timer t=new Timer(interval,listener);
	t.start();
		}
	}
