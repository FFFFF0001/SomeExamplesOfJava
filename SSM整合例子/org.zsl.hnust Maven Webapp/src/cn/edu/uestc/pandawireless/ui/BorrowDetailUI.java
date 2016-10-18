/*
 * BorrowDetailUI.java
 *
 * Created on 2006年5月25日, 下午10:50
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package cn.edu.uestc.pandawireless.ui;
import cn.edu.uestc.pandawireless.model.*;
import javax.microedition.lcdui.*;
import java.util.Date;
import javax.microedition.io.PushRegistry;
/**
 *
 * @author 熊猫晓希
 */
public class BorrowDetailUI extends Form implements CommandListener{
    private UIControler uicontroler;
    private HttpHandler httphandler;
    private static final Command backCommand = new Command("返回",Command.BACK, 2);
    private static final Command selectCommand = new Command("注册",Command.OK, 1);
    public static final String className = "cn.edu.uestc.pandawireless.LibMidlet";
    private DateField datefield;
    private Date set = null;
    private String bookname = null;
    
    /** Creates a new instance of BorrowDetailUI */
    public BorrowDetailUI(UIControler uicontroler,String[] data,HttpHandler httphandler) {
        super(Title.borrowDetailTitle);
        this.uicontroler = uicontroler;
        this.httphandler = httphandler;
        this.uicontroler.borrowui = this;
        this.bookname = data[0];
        this.append("书名:"+data[0]+"\n");
        this.append("索书号:"+data[1]+"\n");
        this.append("应还日期:"+data[2]+"\n");
        this.append("\n\n"+"请输入日期(程序将会在那时提醒你还书)");
        datefield = new DateField("",DateField.DATE_TIME);
        datefield.setDate(new Date());
        this.append(datefield);
        this.addCommand(backCommand);
        this.addCommand(selectCommand);
        this.setCommandListener(this);
    }
    public void commandAction(Command cmd,Displayable displayable){
        if(cmd == backCommand){
            uicontroler.libmidlet.setCurrent(httphandler.lsUI);
        }else if(cmd == selectCommand)
        {
            
            set = datefield.getDate();
            System.out.println("set:"+set);
            String s = AlarmModel.getString(set);
            System.out.println("set:"+s);
            new Thread(new Runnable(){
                public void run(){
                try {
                //将闹钟设置时间存储到RMS中
                uicontroler.libmidlet.model.writeString(set,bookname);
                //注册时钟
                PushRegistry.registerAlarm(className,set.getTime());
                Alert alert = new Alert("完成提示");
                alert.setTimeout(2000);
                alert.setString("您已经成功设置了闹钟");
                alert.setType(AlertType.CONFIRMATION);
                uicontroler.libmidlet.setCurrent(alert,uicontroler.borrowui);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
            }).start();
        }
    }
//    class AlarmSetter implements Runnable{
//        public void run(){
//            try {
//                //将闹钟设置时间存储到RMS中
//                model.writeDate(set);
//                //注册时钟
//                PushRegistry.registerAlarm(className,set.getTime());
//                Alert alert = new Alert("完成提示");
//                alert.setTimeout(2000);
//                alert.setString("您已经成功设置了闹钟");
//                alert.setType(AlertType.CONFIRMATION);
//                uicontroler.libmidlet.setCurrent(alert, );
//            } catch(Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
