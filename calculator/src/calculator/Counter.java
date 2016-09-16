package calculator;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
class Counter extends WindowAdapter{ //继承创建 WindowEvent 侦听器并为所需事件重写该方法
	static JFrame f=new JFrame("计算器"); //JFrame是Swing顶层容器，包含一个JRootPane作为一个唯一子容器窗体
	static JTextField text1=new JTextField("");//JTextField轻量级组件，允许编辑单行文本。
	static String source=""; 
	static String cal="";
	static String object="";
	static boolean flag=false;
	static boolean flag1=true;
	static boolean flag2=false;
	public void init() {
		try  {
			Container c=f.getContentPane();  //返回此窗体的 contentPane 对象
			JPanel pan1=new JPanel(); //JPanel 为javax.swing包中的，为面板容器，可以加入到JFrame中 , 它自身是个容器，可以把其他compont加入到JPanel中,如JButton,JTextArea,JTextFiled等，另外也可以在它上面绘图
			JButton b1=new JButton("1");
			JButton b2=new JButton("2");
			JButton b3=new JButton("3");
			JButton b4=new JButton("4");
			JButton b5=new JButton("5");
			JButton b6=new JButton("6");
			JButton b7=new JButton("7");
			JButton b8=new JButton("8");
			JButton b9=new JButton("9"); 
			JButton b0=new JButton("0");
			JButton b11=new JButton("+");
			JButton b12=new JButton("-");
			JButton b13=new JButton("*");
			JButton b14=new JButton("/");
			JButton b15=new JButton(".");
			JButton b16=new JButton("=");
			JButton bclar=new JButton("清零");
			text1.setHorizontalAlignment(JTextField.RIGHT);//设置文本的水平对齐方式
			c.add(text1,"North");
			c.add(pan1); 
			A aa=new A();  //A是定义一个类实现行为接口，实现按钮事件
			Result re=new Result(); //实现运算结果事件
			Opertion op=new Opertion();//运算符号按钮事件
			Clar cl=new Clar(); //清零
			b1.addActionListener(aa);
			b2.addActionListener(aa); 
			b3.addActionListener(aa);
			b4.addActionListener(aa);
			b5.addActionListener(aa);
			b6.addActionListener(aa);
			b7.addActionListener(aa);
			b8.addActionListener(aa); 
			b9.addActionListener(aa);
			b0.addActionListener(aa);
			b11.addActionListener(op);
			b12.addActionListener(op);
			b13.addActionListener(op);  
			b14.addActionListener(op);
			b16.addActionListener(re);
			b15.addActionListener(aa); 
			bclar.addActionListener(cl);
			pan1.add(b1);
			pan1.add(b2);
			pan1.add(b3);
			pan1.add(b11);  
			pan1.add(b4);
			pan1.add(b5); 
			pan1.add(b6);
			pan1.add(b12); 
			pan1.add(b7);
			pan1.add(b8);   
			pan1.add(b9);
			pan1.add(b13);
			pan1.add(b0);
			pan1.add(b15);
			pan1.add(b16);
			pan1.add(b14);
			pan1.add(bclar);
			f.setSize(200,220);
			f.setVisible(true);
			}
		catch(Exception e)  {
			System.out.println(e.getMessage());
			}
		}
	class A implements ActionListener {
		public void actionPerformed(ActionEvent e)  {
			String a=text1.getText();
			String s=e.getActionCommand(); //返回与此动作相关的命令字符串
			if(a.equals(".")||a.equals("+")||a.equals("-")||a.equals("*")||a.equals("/"))
				text1.setText(s);
			else {
				if(flag2)     {
					text1.setText(s); 
					flag2=false;
					}     
				else
					text1.setText(a+s);
				}
			}
		}
	class Opertion implements ActionListener {
		public void actionPerformed(ActionEvent e)  { 
			cal=e.getActionCommand();
			if(flag1)
				source=text1.getText(); 
			text1.setText(cal);
			flag1=false;  
			flag=true;
			}
		}
	class Result implements ActionListener  { 
		public void actionPerformed(ActionEvent e)  {
			double num1;
			num1=Double.parseDouble(source); //将字符串转换成double类型数据
			object=text1.getText();
			double num2; 
			num2=Double.parseDouble(object);
			double result=0;   
			if(cal.equals("+"))    
				result=num1+num2;
			if(cal.equals("-")) 
				result=num1-num2;
			if(cal.equals("*")) 
				result=num1*num2;
			if(cal.equals("/")){
				if(num2 > -0.00001 && num2 < 0.00001){
					text1.setText("Divisor cannot be zero");  
				}
				else
					result=num1/num2; 
			}
			String s1=Double.toString(result); 
			text1.setText(s1);
			flag1=true; 
			flag2=true;
			}
		}
	class Clar implements ActionListener {
		public void actionPerformed(ActionEvent e)  {
			text1.setText("");
			}
		}
	public static void main(String[] args)  {
		Counter count=new Counter();
		count.init(); 
		}
	public void windowClosing(WindowEvent e){ 
		System.exit(1);
		}
	public void windowOpened(WindowEvent e){} //已打开窗口时调用。
	public void windowIconified(WindowEvent e){} //图标化窗口时调用。
	public void windowDeiconified(WindowEvent e){}//取消图标化窗口时调用。
	public void windowClosed(WindowEvent e){} //当窗口已被关闭时调用。
	public void windowActivated(WindowEvent e){}//激活窗口时调用。
	public void windowDeactivated(WindowEvent e){}//停用窗口时调用。
	}
